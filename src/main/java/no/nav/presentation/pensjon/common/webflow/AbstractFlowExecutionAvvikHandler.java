package no.nav.presentation.pensjon.common.webflow;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.engine.RequestControlContext;
import org.springframework.webflow.execution.FlowExecutionException;

import no.nav.domain.pensjon.common.navansatt.NAVAnsatt;
import no.stelvio.domain.person.Pid;
import no.stelvio.presentation.error.ErrorPageExceptionHandler;
import no.stelvio.presentation.security.page.PageAccessDeniedException;
import no.stelvio.presentation.security.page.PageAuthenticationRequiredException;

public abstract class AbstractFlowExecutionAvvikHandler extends ErrorPageExceptionHandler {

    private static final Log LOGGER = LogFactory.getLog(AbstractFlowExecutionAvvikHandler.class);
    private String errorPage;

    @Override
    public void handle(FlowExecutionException exception, RequestControlContext context) {
        super.setErrorPage(errorPage);

        try {
            if (shouldHandleAsAvvik(exception)) {
                context.getExternalContext().getGlobalSessionMap().put("no.stelvio.presentation.error.ErrorPageExceptionHandler.STATE_EXCEPTION", exception);

                if (context.getFlowExecutionContext().getKey() == null) {
                    context.assignFlowExecutionKey();
                }

                Map<String, String> sakData = extractSakData(context);
                Map<String, String> serverData = extractServerData(context);
                LocalAttributeMap flowScopeAttributes = handleAvvik(exception, sakData, serverData);
                context.getExternalContext().requestFlowDefinitionRedirect(errorPage, flowScopeAttributes);
                LOGGER.error(exception.getMessage(), exception);
            } else {
                super.handle(exception, context);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            super.handle(exception, context);
        }
    }

    private boolean shouldHandleAsAvvik(FlowExecutionException exception) {
        Throwable cause = exception.getCause();
        return !(cause instanceof PageAccessDeniedException || cause instanceof PageAuthenticationRequiredException);
    }

    private Map<String, String> extractSakData(RequestControlContext context) {
        Map<String, String> sakData = new HashMap<>();
        Map pensjonContext = context.getExternalContext().getSessionMap().get("PENSJON_COMMON_SESSIONMAP", HashMap.class);

        if (pensjonContext == null) {
            return sakData;
        }

        NAVAnsatt navAnsatt = (NAVAnsatt) pensjonContext.get("PSAK_LOGGED_ON_NAVANSATT");
        putEntryNullSafe(sakData, "saksbehandlerId", navAnsatt != null ? navAnsatt.getAnsattId() : null);
        Pid pid = (Pid) pensjonContext.get("COMMON_BRUKER_PID");

        if (pid != null) {
            try {
                Long personId = findPersonIdByPid(pid);
                putEntryNullSafe(sakData, "personId", personId);
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
            }
        }

        putEntryNullSafe(sakData, "sakId", pensjonContext.get("PSAK_SAKNR"));
        putEntryNullSafe(sakData, "kravId", pensjonContext.get("PSAK_KRAVID"));
        putEntryNullSafe(sakData, "vedtakId", pensjonContext.get("PSAK_VEDTAKID"));
        return sakData;
    }

    private Map<String, String> extractServerData(RequestControlContext context) {
        Map<String, String> serverData = new HashMap<>();
        putEntryNullSafe(serverData, "node", extractNodeName());
        putEntryNullSafe(serverData, "port", extractPort(context));
        putEntryNullSafe(serverData, "artifakt", extractArtifact(context));
        return serverData;
    }

    private Object extractNodeName() {
        try {
            return new InitialContext().lookup("thisNode/nodename");
        } catch (NamingException e) {
            LOGGER.error("Could not extract node name from the context", e);
            return null;
        }
    }

    private Object extractPort(RequestControlContext context) {
        ServletRequest request = (ServletRequest) context.getExternalContext().getNativeRequest();
        return request != null ? request.getServerPort() : null;
    }

    private Object extractArtifact(RequestControlContext context) {
        ServletContext nativeContext = (ServletContext) context.getExternalContext().getNativeContext();

        if (nativeContext == null) {
            return null;
        }

        Properties props = new Properties();

        try {
            props.load(nativeContext.getResourceAsStream("/META-INF/MANIFEST.MF"));
            return props.getProperty("Package");
        } catch (IOException e) {
            return "UNKNOWN MODULE";
        }
    }

    private void putEntryNullSafe(Map<String, String> map, String key, Object value) {
        if (value == null) {
            return;
        }

        map.put(key, value.toString());
    }

    protected abstract LocalAttributeMap handleAvvik(FlowExecutionException exception, Map<String, String> contextData, Map<String, String> serverData);

    protected abstract Long findPersonIdByPid(Pid fnr);

    @Override
    public void setErrorPage(String errorPage) {
        this.errorPage = errorPage;
    }
}

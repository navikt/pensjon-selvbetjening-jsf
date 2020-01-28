package no.nav.presentation.pensjon.pselv.common;

import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_PENSJON;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_SESSION_ATTR;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_UT;

import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.core.collection.SharedAttributeMap;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;

/**
 * @see no.nav.presentation.pensjon.pselv.common.PselvContextFilter, which ensures that
 * context parameter is always saved to session when present in the original request.
 */
public class PselvContextListener extends FlowExecutionListenerAdapter {

    /**
     * Sets correct application context when entering a page.
     * Default is pensjon, and will be set when no other overriding context is present.
     * Get context parameter by following priority:
     * 1. CONTEXT_SESSION_ATTR
     * 3. DEFAULT.
     */
    @Override
    public void sessionStarting(RequestContext context, FlowSession session, MutableAttributeMap input) {
        updateContextIfSpecialCase(context, session);
        SharedAttributeMap<Object> map = context.getExternalContext().getSessionMap();
        String appContext = map.contains(CONTEXT_SESSION_ATTR) ? map.getString(CONTEXT_SESSION_ATTR) : CONTEXT_PENSJON;
        addApplicationContextInfoToFlowScope(context, appContext);
    }

    /**
     * This method is used to link between pensjon and ufore context (eg when opening a Skjema in ufore context)
     * In this method there are spesific flows defined, that should always be opened in a spesific context
     * The context gets overridden in these cases, so that links and url addresses always are correct.
     * This method needs to be called from sessionStarting because this is when we know the name of the flow
     * that we get to. The name of the context is not available in
     * org.springframework.webflow.execution.FlowExecutionListener#requestSubmitted(org.springframework.webflow.execution.RequestContext)
     * if the flow gets opened as a subflow
     */
    private void updateContextIfSpecialCase(RequestContext context, FlowSession session) {
        String id = session.getDefinition().getId();

        if (id.equals("skjema/alderspensjon")) {
            context.getExternalContext().getSessionMap().put(CONTEXT_SESSION_ATTR, CONTEXT_PENSJON);
        } else if (id.equals("skjema/endringalderspensjon")) {
            context.getExternalContext().getSessionMap().put(CONTEXT_SESSION_ATTR, CONTEXT_PENSJON);
        }
    }

    private void addApplicationContextInfoToFlowScope(RequestContext context, String appContext) {
        MutableAttributeMap<Object> flowScope = context.getFlowScope();
        flowScope.put(CONTEXT_UT, appContext.equals(CONTEXT_UT));
        flowScope.put(CONTEXT_PENSJON, appContext.equals(CONTEXT_PENSJON));
    }
}

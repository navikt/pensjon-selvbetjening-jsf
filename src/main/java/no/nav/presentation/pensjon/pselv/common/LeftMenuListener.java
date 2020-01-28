package no.nav.presentation.pensjon.pselv.common;

import javax.servlet.ServletContext;

import org.springframework.webflow.context.ExternalContext;
import org.springframework.webflow.core.collection.AttributeMap;
import org.springframework.webflow.definition.FlowDefinition;
import org.springframework.webflow.definition.StateDefinition;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.View;

import no.nav.presentation.pensjon.pselv.common.menu.MenuItem;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.tag.PselvContextFunctions;

/**
 * Uses a linked list of Strings in the conversation scope.
 */
public class LeftMenuListener extends FlowExecutionListenerAdapter {

    /**
     * Array with flows that may be subflows. If they are subflows, the parent flow should get its menu reset when the subflow returns
     */
    private static final String[] SUBFLOW_IDS = new String[]{
            "skjemasider",
            "dokumentasjonskrav",
            "registrer",
            "utbetalingsmelding",
            "saksoversikt",
            "beregningsdetaljer",
            "dinepensjonspoeng",
            "mineutbetalinger",
            "sendthenvendelse"
    };

    @Override
    public void sessionEnded(RequestContext context, FlowSession session, String outcome, AttributeMap output) {
        if (!flowSessionCouldBeSubflow(session) || session.getParent() == null) {
            return;
        }

        FlowDefinition parentDefinition = session.getParent().getDefinition();
        setActiveMenuItem(context, parentDefinition);
    }

    @SuppressWarnings("unchecked")
    private void setActiveMenuItem(RequestContext context, FlowDefinition definition) {
        String definitionId = definition.getId();
        ExternalContext externalContext = context.getExternalContext();
        ServletContext servletContext = (ServletContext) externalContext.getNativeContext();
        MenuItem item = getRelevantLeftMenu(servletContext);
        MenuItem active = item.findItem(definitionId);

        if (active != null) {
            PselvSessionMapWrapper.put(PselvSessionMapWrapper.ACTIVE, active);
            return;
        }

        active = findItem(definitionId, item);

        if (active != null) {
            PselvSessionMapWrapper.put(PselvSessionMapWrapper.ACTIVE, active);
        }
    }

    @Override
    public void viewRendering(RequestContext context, View view, StateDefinition viewState) {
        setActiveMenuItem(context, context.getActiveFlow());
    }

    /**
     * Either returns the left-menu for Din pensjon or Din uforetrygd based on which context the user is in.
     */
    private MenuItem getRelevantLeftMenu(ServletContext cont) {
        String menuId = PselvContextFunctions.isUTContext()
                ? PselvConstants.LEFT_MENU_UT
                : PselvConstants.LEFT_MENU_PENSJON;

        return (MenuItem) cont.getAttribute(menuId);
    }

    /**
     * If the ending flow can be a subflow, this method will return true. Used to determine if the parent flow should get the
     * menu reset when the subflow returns.
     */
    private boolean flowSessionCouldBeSubflow(FlowSession session) {
        boolean subflow = false;

        for (String element : SUBFLOW_IDS) {
            if (session.getDefinition().getId().contains(element)) {
                subflow = true;
            }
        }

        return subflow;
    }

    private static MenuItem findItem(String definitionId, MenuItem item) {
        if (definitionId.startsWith("skjema/alderspensjon")) {
            return item.findItem("skjema/alderspensjon");
        }
        if (definitionId.startsWith("skjema/endringalderspensjon")) {
            return item.findItem("skjema/endringalderspensjon");
        }
        if (definitionId.contains("inntektogskatt")) {
            return item.findItem("transaksjon/skatt");
        }
        if (definitionId.contains("skjema")) {
            return item.findItem("skjema/skjemaoversikt");
        }
        if (definitionId.contains("beregningsdetaljer")) {
            return item.findItem("publisering/saksoversikt");
        }
        if (definitionId.contains("byttbruker")) {
            return item.findItem("fullmakter/fullmakter");
        }
        if (definitionId.startsWith("simulering")) {
            return item.findItem("simulering");
        }
        return null;
    }
}

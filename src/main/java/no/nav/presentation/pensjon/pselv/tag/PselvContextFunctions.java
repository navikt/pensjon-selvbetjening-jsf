package no.nav.presentation.pensjon.pselv.tag;

import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_UT;
import static org.apache.commons.lang.BooleanUtils.isFalse;

import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.RequestContextHolder;

public class PselvContextFunctions {

    public static boolean isUTContext() {
        MutableAttributeMap<Object> flowScope = RequestContextHolder.getRequestContext().getFlowScope();
        return flowScope.contains(CONTEXT_UT) ? (Boolean) flowScope.get(CONTEXT_UT) : Boolean.FALSE;
    }

    public static boolean isPensjonContext() {
        return isFalse(isUTContext());
    }

    private PselvContextFunctions() {
    }
}

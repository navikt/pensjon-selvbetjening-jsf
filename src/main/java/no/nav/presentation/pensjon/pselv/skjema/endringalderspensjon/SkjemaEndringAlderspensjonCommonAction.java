package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import org.springframework.webflow.execution.RequestContext;

import no.nav.presentation.pensjon.pselv.skjema.SkjemaCommonAction;

/**
 * Common action baseclass for SKS016 and SKS017.
 *
 */
public abstract class SkjemaEndringAlderspensjonCommonAction extends SkjemaCommonAction implements SkjemaEndringAlderspensjonConstants {

    /**
     * Creates the form object and populates it with the input data object fetched from flowscope.
     *
     * @param context RequestContext
     * @return SkjemaEndringCommonForm
     * @throws Exception Spring exception
     * @link org.springframework.webflow.action.FormAction#createFormObject(org.springframework.webflow.execution.RequestContext)
     */
    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        SkjemaEndringAlderspensjonCommonForm form = (SkjemaEndringAlderspensjonCommonForm) super.createFormObject(context);
        form.setShowGlobalMessages(false);

        SkjemaEndringAlderspensjonCommonInputData inputData = (SkjemaEndringAlderspensjonCommonInputData) context.getFlowScope().get(SKJEMA_INPUT_DATA);
        if (inputData != null) {
            form.setInputData(inputData);
        }

        /* Returns the SkjemaEndringCommonForm object to the method caller. */
        return form;
    }
}

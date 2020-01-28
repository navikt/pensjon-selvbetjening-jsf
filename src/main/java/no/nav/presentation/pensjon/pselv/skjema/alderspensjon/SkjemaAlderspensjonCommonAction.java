package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.presentation.pensjon.pselv.common.delegate.SkjemaCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.skjema.SkjemaCommonAction;

public abstract class SkjemaAlderspensjonCommonAction extends SkjemaCommonAction {

    public static final String SKJEMA_INPUT_DATA = "skjemaCommonInputData";
    protected SkjemaCommonActionDelegate skjemaCommonActionDelegate;
    protected PselvTransferObject pselvTransferObject;

    /**
     * Retrieves skjemaId from PselvSessionMapWrapper. If the skjemaId exists on the session, the CommonSkjemaActionDelegate is
     * called and the corresponding Skjema is retrieved and put into the CommonSkjemaForm.
     */
    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        SkjemaAlderspensjonCommonForm form = (SkjemaAlderspensjonCommonForm) super.createFormObject(context);
        form.setShowGlobalMessages(false);
        SkjemaAlderspensjonCommonInputData inputData = (SkjemaAlderspensjonCommonInputData) context.getFlowScope().get(SKJEMA_INPUT_DATA);

        if (inputData != null) {
            form.setInputData(inputData);

            if (inputData.getSkjemaId() != null) {
                Skjema skjema = skjemaCommonActionDelegate.hentSkjema(inputData.getSkjemaId());
                form.setSkjema(skjema);

                /* Synchronizing the Fng and the PenPerson objects from db for the Skjema object. */
                if (null != skjema && null != skjema.getPenPerson() && null != skjema.getPenPerson().getFnr()) {
                    skjema.getPenPerson().getFnr();
                }
            }
        }

        form.dividingIntoKull();
        return form;
    }

    public Event saveSkjema(RequestContext context) {
        SkjemaAlderspensjonCommonForm form = (SkjemaAlderspensjonCommonForm) getFormObject(context);
        form.setSkjema(skjemaCommonActionDelegate.lagreSkjema(form.getSkjema()));
        return success();
    }

    public Event removeSkjema(RequestContext context) {
        SkjemaAlderspensjonCommonForm form = (SkjemaAlderspensjonCommonForm) getFormObject(context);
        skjemaCommonActionDelegate.slettSkjema(form.getSkjema().getSkjemaId());
        return success();
    }

    public void setSkjemaCommonActionDelegate(SkjemaCommonActionDelegate skjemaCommonActionDelegate) {
        this.skjemaCommonActionDelegate = skjemaCommonActionDelegate;
    }

    public void setPselvTransferObject(PselvTransferObject pselvTransferObject) {
        this.pselvTransferObject = pselvTransferObject;
    }
}

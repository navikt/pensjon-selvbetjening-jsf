package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import static no.stelvio.common.util.DateUtil.isAfterByDay;
import static no.stelvio.common.util.DateUtil.isBeforeByDay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.common.tjenestepensjon.TjenestepensjonForhold;
import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.grunnlag.Persongrunnlag;
import no.nav.domain.pensjon.kjerne.grunnlag.Uforegrunnlag;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagsrolleCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SkjemaStatusCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaInnledning;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.PselvConstants;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.common.utils.RelasjonUtil;
import no.nav.presentation.pensjon.pselv.simulering.personopplysninger.support.Uforeberegninger; //TODO why dep'y on simulering?
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonAction;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonForm;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonInputData;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;

/**
 * Action class used by SKS001 "Skjemaside innledning".
 */
public class SkjemaInnledningAction extends SkjemaAlderspensjonCommonAction {

    private static final Log LOG = LogFactory.getLog(SkjemaInnledningAction.class);
    private SkjemaInnledningFormPopulator formPopulator;
    private SkjemaInnledningDomainPopulator domainPopulator;
    private SkjemaInnledningActionDelegate skjemaInnledningActionDelegate;
    private CodesTableManager codesTableManager;

    /**
     * Retrives an existing skjema using either skjemaId or SkjemaInputdata object from the session. Creates a new Skjema object
     * if none is found.
     */
    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        SkjemaInnledningForm form = (SkjemaInnledningForm) super.createFormObject(context);
        Skjema skjema = retrieveStartedAlderspensjonSkjemaIfExists(form.getBruker().getPid());

        if (skjema == null) {
            skjema = createNewSkjema(form.getBruker());
        }

        form.setSkjema(skjema);
        populateForm(form);
        return form;
    }

    /**
     * Checks if user has a started alderspensjonsøknad (a skjema of type AP with status opprettet).
     */
    private Skjema retrieveStartedAlderspensjonSkjemaIfExists(Pid pid) {
        List<Skjema> skjemaer = skjemaInnledningActionDelegate.hentSkjemaListe(pid);

        if (skjemaer == null) {
            return null;
        }

        for (Skjema skjema : skjemaer) {
            if (isSkjemaOpprettet(skjema) && isSkjemaAlderspensjon(skjema)) {
                return skjema;
            }
        }

        return null;
    }

    @Override
    public Event saveSkjema(RequestContext context) {
        SkjemaInnledningForm form = (SkjemaInnledningForm) getFormObject(context);
        Skjema skjema = form.getSkjema();
        SkjemaInnledning skjemaInnledning = domainPopulator.populateSkjemaInnledning(form, skjema);
        skjema.setSkjemaInnledning(skjemaInnledning);

        if (skjemaInnledning.getSoktAfpPrivat() == false) {
            skjema.setSkjemaAFPPrivat(null);
        }

        super.saveSkjema(context);
        skjema = form.getSkjema();

        if (LOG.isDebugEnabled()) {
            Long skjemaId = skjema.getSkjemaId();
            if (skjemaId != null) {
                LOG.debug("Skjema med id " + skjemaId + " ble lagret i SkjemaInnledningAction.saveForm()");
            } else {
                LOG.debug("OBS! Skjema ble lagret i SkjemaInnledningAction.saveForm(), men skjemaId er null!¨"
                        + "\nDette kan lage problemer for neste skjermbilde...");
            }
        }

        setSkjemaCommonInputData(skjema, form);
        context.getFlowScope().put(SkjemaAlderspensjonCommonAction.SKJEMA_INPUT_DATA, form.getInputData());
        form.setShowLiteBox(false);
        return success();
    }

    public Event hasBrukerChosenDate67MAndHasLopendeUfore(RequestContext context) {
        SkjemaInnledningForm form = (SkjemaInnledningForm) super.getFormObject(context);
        Skjema skjema = form.getSkjema();
        SkjemaInnledning skjemaInnledning = domainPopulator.populateSkjemaInnledning(form, skjema);
        Date brukers67M = calculateFirstDayInMonthAfterPersonTurns67(form.getBruker().getPid().getFodselsdato());

        if (skjemaInnledning.getUttaksgrad() < 100 && form.getHasLopendeUforeAt67M()
                && skjemaInnledning.getIverksettelsesdato().equals(brukers67M)) {
            form.setShowLiteBox(true);
            return yes();
        }
        return no();
    }

    @Override
    public Event setTypedKeyInformation(RequestContext context) {
        SkjemaInnledningForm form = (SkjemaInnledningForm) getFormObject(context);
        String typedKeyInformation = formPopulator.setTypedKeyInformation(form);
        setTypedKeyInformationCurrentStep(context, typedKeyInformation);
        return success();
    }

    public Event removeSkjemaInnledning(RequestContext context) {
        SkjemaAlderspensjonCommonForm form = (SkjemaAlderspensjonCommonForm) getFormObject(context);

        if (form.getSkjema().getSkjemaId() != null) {
            skjemaCommonActionDelegate.slettSkjema(form.getSkjema().getSkjemaId());
        }

        return success();
    }

    private Skjema createNewSkjema(Person bruker) {
        ElektroniskSkjemaTypeCti skjemaType = codesTableManager.getCodesTablePeriodic(ElektroniskSkjemaTypeCti.class)
                .getCodesTableItem(ElektroniskSkjemaTypeCode.AP.name());

        Skjema skjema = new Skjema();
        skjema.setSkjemaPselvType(skjemaType);

        // Sets new PenPerson with Pid from current Person
        PenPerson penPerson = new PenPerson();
        penPerson.setFnr(bruker.getPid());
        skjema.setPenPerson(penPerson);

        if (LOG.isDebugEnabled()) {
            LOG.debug("A Skjema was created in SkjemaInnledningAction");
        }

        return skjema;
    }

    private SkjemaInnledningForm populateForm(SkjemaInnledningForm form) {
        Skjema skjema = form.getSkjema();

        // Add a SkjemaInnledning object if it's not there already
        if (skjema.getSkjemaInnledning() == null) {
            skjema.setSkjemaInnledning(new SkjemaInnledning());
        }

        // Set SkjemaCommonInputData
        setSkjemaCommonInputData(skjema, form);
        // Set properties not defined in Skjema, ytelse AP, AFP, Gjenlevendepensjon or Uforepensjon
        setHasLopendeYtelse(form);

        // Only perform call to external service TPEN486 BestemGjeldendeVedtak if the date 67M is possible to choose
        if (userWillBeAbleToChooseUttakstidspunktEqualTo67M(form)) {

            Pid pid = form.getBruker().getPid();
            List<Vedtak> lopendeVedtakAt67MListe = skjemaInnledningActionDelegate.getLopendeVedtakListeAt67M(pid, calculateFirstDayInMonthAfterPersonTurns67(pid.getFodselsdato()));

            setHasLopendeUforeAt67M(form, lopendeVedtakAt67MListe);
            setHasLopendeUforeGjenlevOrFamplAt67M(form, lopendeVedtakAt67MListe);
        }

        // PENPORT-201 Check if user has a valid EPS relation
        form.setUserHasManglendeEpsInformasjon(harManglendeEpsInformasjon(form.getBruker()));

        formPopulator.setErrorMessageUserCantApply(form);

        if (!form.isShowErrorMessage()) {
            SkjemaDataForPreComplementing skjemaData = getMappingObjectKalkulatorToSkjema();
            formPopulator.populateForm(form, skjemaData);

            if (form.isAldersPensjonSoknad()) {
                populateShowMessageAFP(form);
            }
        }

        return form;
    }

    protected boolean userWillBeAbleToChooseUttakstidspunktEqualTo67M(SkjemaInnledningForm form) {
        Date bruker67M = calculateFirstDayInMonthAfterPersonTurns67(form.getBruker().getPid().getFodselsdato());
        Date latestChooseableDateDueToSPERREFRIST = DateUtil.getFirstDayOfMonth(DateUtil.getRelativeDateByMonth(Calendar.getInstance().getTime(), PselvConstants.SPERREFRIST + 1));
        Date firstDayInNextMonth = DateUtil.getFirstDayOfMonth(DateUtil.getRelativeDateByMonth(Calendar.getInstance().getTime(), 1));
        return (isAfterByDay(bruker67M, firstDayInNextMonth, true)) && (isBeforeByDay(bruker67M, latestChooseableDateDueToSPERREFRIST, true));
    }

    private boolean harManglendeEpsInformasjon(Person bruker) {
        List<Relasjon> relasjoner = skjemaInnledningActionDelegate.getEpsRelasjoner(bruker.getPid());
        return !new RelasjonUtil().containsValidEps(relasjoner, bruker.getSivilstand());
    }

    /**
     * Gets the object used for mapping values from pensjonskalkulator into skjema. The object is set on PselvTransferObject by
     * the last step in pensjonkalkulatoren - SIS005 Resultat hva hvis.
     */
    private SkjemaDataForPreComplementing getMappingObjectKalkulatorToSkjema() {
        SkjemaDataForPreComplementing skjemaData = null;

        if (pselvTransferObject.containsKey(PselvTransferObject.CALC_TO_SKJEMA_MAPPING)) {
            skjemaData = (SkjemaDataForPreComplementing) pselvTransferObject.get(PselvTransferObject.CALC_TO_SKJEMA_MAPPING);
        }

        return skjemaData;
    }

    private void setSkjemaCommonInputData(Skjema skjema, SkjemaInnledningForm form) {
        Long skjemaId = skjema.getSkjemaId();

        Boolean barnetillegg = skjema.getSkjemaInnledning().getForsorgingstilleggBarn();
        boolean barnetilleggIsChosen = barnetillegg != null ? barnetillegg : false;

        Boolean ektefelletillegg = skjema.getSkjemaInnledning().getForsorgingstilleggEPS();
        boolean ektefelletilleggIsChosen = ektefelletillegg != null ? ektefelletillegg : false;

        SkjemaAlderspensjonCommonInputData inputData = new SkjemaAlderspensjonCommonInputData(skjemaId, barnetilleggIsChosen,
                isSkjemaAlderspensjon(skjema), form.getSkjemaData());
        inputData.setEktefelletilleggIsChosen(ektefelletilleggIsChosen);

        Boolean afpPrivat = skjema.getSkjemaInnledning().getSoktAfpPrivat();
        boolean afpPrivatIsChosen = afpPrivat != null ? afpPrivat : false;

        inputData.setAfpPrivat(afpPrivatIsChosen);
        form.setInputData(inputData);
    }

    private void setHasLopendeUforeGjenlevOrFamplAt67M(SkjemaInnledningForm form, List<Vedtak> lopendeVedtakAt67M) {
        boolean hasLopendeUforeGjenlevOrFamplAt67M = lopendeVedtakAt67M
                .stream()
                .anyMatch(v -> v.getSakstype().isCodeEqualTo(SakTypeCode.UFOREP) || v.getSakstype().isCodeEqualTo(SakTypeCode.GJENLEV) || v.getSakstype()
                        .isCodeEqualTo(SakTypeCode.FAM_PL));

        form.setHasLopendeUforeGjenlevOrFamplAt67M(hasLopendeUforeGjenlevOrFamplAt67M);
    }

    private void setHasLopendeUforeAt67M(SkjemaInnledningForm form, List<Vedtak> lopendeVedtakAt67M) {
        boolean hasLopendeUforeAt67M = lopendeVedtakAt67M
                .stream()
                .anyMatch(v -> v.getSakstype().isCodeEqualTo(SakTypeCode.UFOREP));

        form.setHasLopendeUforeAt67M(hasLopendeUforeAt67M);
    }

    /**
     * Helper for calculating the date when a user turns 67
     */
    private Date calculateFirstDayInMonthAfterPersonTurns67(Date dateOfBirth) {
        Date dateOf67m = DateUtil.getRelativeDateByYear(dateOfBirth, 67);
        dateOf67m = DateUtil.getRelativeDateByMonth(dateOf67m, 1);
        dateOf67m = DateUtil.getFirstDayOfMonth(dateOf67m);
        return dateOf67m;
    }

    /**
     * Check if the user is already receiving Alderspensjon, has Alderspensjonssoknad til behandling, is already receiving AFP,
     * Gjenlevendepensjon or Uforepensjon (ie there is a Sak with status 'Lopende' or 'til behandling')
     */
    private void setHasLopendeYtelse(SkjemaInnledningForm form) {
        Pid pid = form.getBruker().getPid();
        List<Sak> sakListe = skjemaInnledningActionDelegate.hentSakListe(pid);

        if (sakListe == null || sakListe.isEmpty()) {
            return;
        }

        for (Sak sak : sakListe) {
            SakStatusCti sakstatusTypeCti = sak.getSakStatus();
            SakTypeCti sakTypeCti = sak.getSakType();

            if (sakTypeCti.isCodeEqualTo(SakTypeCode.ALDER) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.LOPENDE)) {
                populateInformationLopendeAlderspensjon(form, sak);
            }

            if (sakTypeCti.isCodeEqualTo(SakTypeCode.ALDER) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.TIL_BEHANDLING)) {
                populateInformationAlderspensjonTilBehandling(form, sak);
            }

            if (sakTypeCti.isCodeEqualTo(SakTypeCode.AFP) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.LOPENDE)) {
                form.setLopendeAFP(true);
            }

            if (sakTypeCti.isCodeEqualTo(SakTypeCode.GJENLEV) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.LOPENDE)) {
                form.setLopendeGjenlevende(true);
            }

            if (sakTypeCti.isCodeEqualTo(SakTypeCode.UFOREP) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.LOPENDE)) {
                if (hasUserMoreThanOneUforeVedtakAfterTodayOrOneVedtakInFuture(pid, sak)) {
                    form.setUserHasUforeVedtakFremITid(true);
                } else {
                    form.setLopendeUforepensjon(true);
                    populateUforegrad(sak, form);
                }
            }
            // CR 191171: user is allowed to apply 'Alderspensjon' if he/she has an sak with status 'oprettet'
            if (sakTypeCti.isCodeEqualTo(SakTypeCode.UFOREP) && sakstatusTypeCti.isCodeEqualTo(SakStatusCode.TIL_BEHANDLING)) {
                form.setUforepensjonTilBehandling(false);

                for (KravHode krav : sak.getKravHodeListe()) {
                    if (krav.kravKanGiLopendeYtelse() && krav.isAapentKrav()) {
                        form.setUforepensjonTilBehandling(true);
                        break;
                    }
                }
            }
        }
    }

    private boolean hasUserMoreThanOneUforeVedtakAfterTodayOrOneVedtakInFuture(Pid pid, Sak sak) {
        List<Vedtak> uforeVedtakList = getUforeVedtakListe(pid, sak.getSakId());
        Date today = Calendar.getInstance().getTime();

        if (uforeVedtakList == null) {
            return false;
        }

        int size = uforeVedtakList.size();

        if (size > 1) {
            return true;
        }

        return size == 1 && isBeforeByDay(today, uforeVedtakList.get(0).getVirkFom(), false);
    }

    /**
     * Populates form with information from sak lopende alderspensjon. If the application is forsorgingstillegg, we need
     * information about regelverk and uttaksgrad.
     */
    private void populateInformationLopendeAlderspensjon(SkjemaInnledningForm form, Sak sak) {
        form.setReceivingAP(true);

        if (!form.isForsorgningstilleggSoknad()) {
            return;
        }

        KravHode krav = findKravLopendeAlderspensjon(sak, form);
        boolean nyttRegelverk = !krav.getRegelverkType().equals(RegelverkTypeCode.G_REG);
        form.setUserHasAlderspensjonNyttRegelverk(nyttRegelverk);

        if (nyttRegelverk) {
            formPopulator.populateGjeldendeUttaksgradFromKrav(form, krav);
        }
    }

    /**
     * Populates form with information from sak lopende alderspensjon. If the application is forsorgingstillegg, we need
     * information about regelverk.
     */
    private void populateInformationAlderspensjonTilBehandling(SkjemaInnledningForm form, Sak sak) {
        form.setSoknadInne(true);

        if (!form.isForsorgningstilleggSoknad()) {
            return;
        }

        KravHode aapentKrav = null;

        for (KravHode kravhode : sak.getKravHodeListe()) {
            if (kravhode.isAapentKrav()) {
                aapentKrav = kravhode;
            }
        }

        boolean nyttRegelverk = !aapentKrav.getRegelverkType().equals(RegelverkTypeCode.G_REG);
        form.setUserHasAlderspensjonNyttRegelverk(nyttRegelverk);
    }

    /**
     * Find the krav of a lopende alderspensjon sak.
     */
    private KravHode findKravLopendeAlderspensjon(Sak sak, SkjemaInnledningForm form) {
        // Sak is lopende. A lopende sak can only have one lopende vedtak.
        Vedtak vedtak = getVedtak(form.getBruker().getPid(), sak.getSakId());
        return vedtak.getKravhode();
    }

    /**
     * Check if a message about AFP should be shown to the user.
     * <p>
     * 1. A service call finnTjenestepensjonforhold is performed if user does not have a lopende ytelse AFP, gjenlevende or uforepensjon and the user is between 62 and 66 years
     * old. <br>
     * 2. The response from the service is filtered by lopende tjenestepensjonforhold not from Norsk pensjon. <br>
     * 3. If any tjenestepensjonforhold is found (step 2), we suspect the user have AFP.
     * <p>
     * If the service call finnTjenestepensjonforhold call, a message shall be shown to the user.
     */
    private void populateShowMessageAFP(SkjemaInnledningForm form) {
        boolean showMessageAFP = false;
        if (!(form.isLopendeAFP() || form.isLopendeGjenlevende() || form.isLopendeUforepensjon())
                && formPopulator.isUserBetween62And67(form.getBruker())) {
            try {
                List<TjenestepensjonForhold> tjenestepensjonForholdList = skjemaInnledningActionDelegate.finnTjenestepensjonsforhold(form.getBruker().getPid().getPid());
                showMessageAFP = !tjenestepensjonForholdList.isEmpty();
            } catch (ConsumerRecoverableException e) {
                showMessageAFP = true;
            }
        }
        form.setHasLopendeOffentligAFP(showMessageAFP);
    }

    /**
     * Find and set uforegrad.
     * Retrieves uforegrad from different places based on uforetrygd and uforepensjon.
     * Uforepensjon: Kravhode -> Persongrunnlag -> Uforegrunnlag -> Ufg.
     * Uforetrygd: Vedtak -> Vilkarsvedtak -> UforegradBeregningsvilkar -> Uforegrad.
     */
    private void populateUforegrad(Sak sak, SkjemaInnledningForm form) {
        Vedtak vedtak = getVedtak(form.getBruker().getPid(), sak.getSakId());

        //SIR233393 added null-check for vedtak
        if (vedtak == null) {
            return;
        }

        KravHode kravHode = vedtak.getKravhode();
        Integer uforegrad = null;

        if (kravHode.isUforepensjon()) {
            uforegrad = getUforegradForUforepensjon(kravHode);
        } else if (kravHode.isUforetrygd()) {
            Uforeberegninger uforeberegninger = new Uforeberegninger();
            uforeberegninger.addBeregningerFor(vedtak);
            uforegrad = uforeberegninger.getGjeldendeUforegrad().orElse(null);
        }

        form.setUforeGrad(uforegrad);
    }

    private Integer getUforegradForUforepensjon(KravHode kravhode) {
        Persongrunnlag personGrunnlag = kravhode.findPersonGrunnlagIGrunnlagsRolle(GrunnlagsrolleCode.SOKER);

        if (personGrunnlag == null) {
            return null;
        }

        Uforegrunnlag uforeGrunnlag = personGrunnlag.getUforegrunnlag();
        return uforeGrunnlag == null ? null : uforeGrunnlag.getUfg();
    }

    private Vedtak getVedtak(Pid pid, Long sakId) {
        Date today = Calendar.getInstance().getTime();
        List<Vedtak> vedtakList = skjemaInnledningActionDelegate.hentVedtakListe(pid, sakId, today, today, false);

        if (vedtakList == null || vedtakList.isEmpty()) {
            return null;
        }

        if (vedtakList.size() == 1) {
            return vedtakList.get(0);
        }

        throw new ImplementationUnrecoverableException("SkjemasideKvitteringActionDelegate.getVedtak: multiple vedtak found!");
    }

    private List<Vedtak> getUforeVedtakListe(Pid pid, Long sakId) {
        Date today = Calendar.getInstance().getTime();
        return skjemaInnledningActionDelegate.hentVedtakListe(pid, sakId, today, null, true);
    }

    private boolean isSkjemaAlderspensjon(Skjema skjema) {
        return ElektroniskSkjemaTypeCode.AP.equals(skjema.getSkjemaPselvType().getCode());
    }

    private boolean isSkjemaOpprettet(Skjema skjema) {
        return SkjemaStatusCode.OPPRETTET.equals(skjema.getSkjemaPselvStatus().getCode());
    }

    public void setFormPopulator(SkjemaInnledningFormPopulator formPopulator) {
        this.formPopulator = formPopulator;
    }

    public void setDomainPopulator(SkjemaInnledningDomainPopulator domainPopulator) {
        this.domainPopulator = domainPopulator;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public void setSkjemaInnledningActionDelegate(SkjemaInnledningActionDelegate skjemaInnledningActionDelegate) {
        this.skjemaInnledningActionDelegate = skjemaInnledningActionDelegate;
    }
}

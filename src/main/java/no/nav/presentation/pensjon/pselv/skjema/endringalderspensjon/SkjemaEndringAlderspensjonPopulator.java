package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import no.stelvio.common.util.DateUtil;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.VedtakstatusTypeCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.sak.SakSammendrag;
import no.nav.domain.pensjon.kjerne.sak.Uttaksgrad;
import no.nav.domain.pensjon.kjerne.vedtak.Beregningsperiode;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.PselvUtil;
import no.nav.presentation.pensjon.pselv.common.enums.EndringAlderspensjonState;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.CancelLink;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.NavigationStepData;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.NextStepButton;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.StepHolder;
import no.nav.presentation.pensjon.pselv.common.utils.RelasjonUtil;
import no.nav.presentation.pensjon.pselv.common.utils.UttaksgradUtil;
import no.nav.presentation.pensjon.pselv.simulering.personopplysninger.support.Uforeberegninger;
import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

public class SkjemaEndringAlderspensjonPopulator {

    private static final int UTTAKSGRAD_GAMMELT_REGELVERK = 100;
    private static final Integer INDEX_OF_FIRST_STEP = 0;
    private static final String ENDRING_ALDERSPENSJON_TITLE = "pselv.sks016.statisk_tekst.endrealderspensjonoverskrift";
    private static final String ENDRING_ALDERSPENSJON_FLOW = "endring";
    private static final String BEKREFT_ENDRING_ALDERSPENSJON_TITLE = "pselv.sks017.statisk_tekst.bekreftendringalderspensjonoverskrift";
    private static final String BEKREFT_ENDRING_ALDERSPENSJON_FLOW = "bekreft";
    private static final String GAA_VIDERE_KNAPP_VALUE = "pselv.standardtekst.knappetekst.gavidere";
    private static final String GAA_VIDERE_KNAPP_TOOLTIP = "pselv.standardtekst.tooltip.gavidere_title";
    private static final String GAA_VIDERE_KNAPP_ALT = "pselv.standardtekst.alt_tekst.gavidere_alt";
    private static final String GAA_VIDERE_KNAPP_BEKREFT_VALUE = "pselv.sks013.statisk_tekst.bekreftsendinn";
    private static final String GAA_VIDERE_KNAPP_BEKREFT_TOOLTIP = "pselv.sks013.tooltip.bekreftsendinn_title";
    private static final String GAA_VIDERE_KNAPP_BEKREFT_ALT = "pselv.sks013.alt_tekst.bekreftsendinn_alt";
    private static final String AVBRYT_LENKE_VALUE = "pselv.standardtekst.knappetekst.avbryt";
    private static final String AVBRYT_LENKE_MESSAGE = "pselv.sks016.statisk_tekst.bekreftavbryt";
    private static final String AVBRYT_LENKE_TOOLTIP = "pselv.standardtekst.tooltip.avbryt_title";
    private static final String AVBRYT_LENKE_ALT = "pselv.standardtekst.alt_tekst.avbryt_alt";
    private SkjemaEndringAlderspensjonActionDelegate skjemaEndringAlderspensjonActionDelegate;
    private RelasjonUtil relasjonUtil;
    private UttaksgradUtil uttaksgradUtil;
    private MessageSource messageSource;

    public StepHolder createAndPopulateStepHolder(SkjemaEndringAlderspensjonCommonInputData skjemaEndringCommon) {
        StepHolder stepHolder = new StepHolder();
        List<NavigationStepData> stepList = new ArrayList<NavigationStepData>();
        addEndringAlderspensjon(stepList, skjemaEndringCommon.getEndringAlderspensjonState());

        if (skjemaEndringCommon.getEndringAlderspensjonState().getUserCanApplyEndringAlderspensjon()) {
            addBekreftEndringAlderspensjon(stepList);
        }

        stepHolder.setStepList(stepList);
        stepHolder.setCurrentStepIndex(INDEX_OF_FIRST_STEP);
        return stepHolder;
    }

    public void findEndringAlderspensjonStateFromBrukerSakerAndVedtak(Person person,
                                                                      SkjemaEndringAlderspensjonCommonInputData commonInputData, List<Sak> saksliste, List<Vedtak> vedtaksliste) {
        List<EndringAlderspensjonState> stateList = new ArrayList<EndringAlderspensjonState>();
        Sak alderssak = getAlderssak(saksliste);
        Vedtak aldersvedtak = getAldersvedtak(vedtaksliste);

        //To make sure the tests below are consistent
        if (aldersvedtak != null && aldersvedtak.getSakId() != null && !aldersvedtak.getSakId().equals(alderssak.getSakId())) {
            alderssak = aldersvedtak.getKravhode().getSak();
        }

        Sak uforesak = getUforesak(saksliste);
        //CR 225352 kap 10.1.1 in design is solved inside this method. Returns null if state is populated
        Vedtak uforevedtak = getUforevedtak(vedtaksliste, stateList);
        //To make sure the tests below are consistent
        if (uforevedtak != null && uforevedtak.getSakId() != null && !uforevedtak.getSakId().equals(uforesak.getSakId())) {
            uforesak = uforevedtak.getKravhode().getSak();
        }

        EndringAlderspensjonState state = getEndringState(person, commonInputData, stateList, alderssak, aldersvedtak, uforesak, uforevedtak);
        commonInputData.setEndringAlderspensjonState(state);
    }

    /**
     * Side-effect: inputData is populated
     */
    private EndringAlderspensjonState getEndringState(Person person,
                                                      SkjemaEndringAlderspensjonCommonInputData inputData,
                                                      List<EndringAlderspensjonState> states,
                                                      Sak alderssak,
                                                      Vedtak aldersvedtak,
                                                      Sak uforesak,
                                                      Vedtak uforevedtak) {
        if (!states.isEmpty()) {
            return states.get(0);
        }

        if (alderssak == null || aldersvedtak == null) {
            // user does not have an alderspensjon sak or the sak is avsluttet
            //Avsluttet and Opprettet Saker have been removed from the Sak list
            //or user does not have a aldersvedtak on the vedtaksliste
            return EndringAlderspensjonState.KAN_IKKE_SOKE;
        } else if (isSakLopendeAndStoppet(alderssak, aldersvedtak)) {
            // user has an lopende sak, but the sak is stoppet
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_VEDTAK_ALDER_STOPPET;
        } else if (isSakTilBehandlingWithOpenPossibleLopendeKrav(alderssak)) {
            // user has an alderspensjon sak til behandling
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_ALDER_SOKNAD_INNE;
        } else if (isSakTilBehandlingWithOpenPossibleLopendeKrav(uforesak)) {
            // user has an uforepensjon sak til behandlig
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_UFORE_SOKNAD_INNE;
        } else if (hasLopendeAlderspensjonAndLopendeUforegradGreaterThan80(person, alderssak, uforesak, uforevedtak)) {
            // user has an lopende alderspensjon and lopende uforepensjon with uforegrad greater than 80 %
            Integer uforegrad = getUforegrad(uforevedtak);
            inputData.setLopendeUforegrad(uforegrad);
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_FOR_HOY_UFOREGRAD;
        } else if (!canChooseAgeOver67And1Month(person) && uforeOver80(uforevedtak)) {
            Integer uforegrad = getUforegrad(uforevedtak);
            inputData.setLopendeUforegrad(uforegrad);
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_FOR_HOY_UFOREGRAD;
        } else if (harManglendeEpsInformasjon(person)) {
            return EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_MANGLENDE_INFO_OM_EPS;
        } else {
            // Gammelt regelverk har ikke uttaksgradhistorikk. Dermed m� virkfom hentes fra forste virk p� saken
            if (aldersvedtak.getKravhode().getRegelverkType() == RegelverkTypeCode.G_REG) {
                inputData.setLopendeUttaksgrad(UTTAKSGRAD_GAMMELT_REGELVERK);
                inputData.setLopendeVirkFom(getForsteVirkAlderssakGammeltRegelverk(alderssak));
            } else {
                //PENPORT-3170 added uttaksgradUtil as a private of the class
                Uttaksgrad uttaksgrad = UttaksgradUtil.getLatestUttaksgrad(alderssak.getUttaksgradhistorikk());
                inputData.setLopendeUttaksgrad(uttaksgrad.getUttaksgrad());
                inputData.setLopendeVirkFom(uttaksgrad.getFomDato());
            }

            return EndringAlderspensjonState.KAN_SOKE;
        }
    }

    private boolean uforeOver80(Vedtak uforevedtak) {
        Integer uforegrad = getUforegrad(uforevedtak);
        return uforegrad != null && uforegrad > 80;
    }

    private boolean isVedtakStoppet(Vedtak vedtak) {
        return vedtak.getVedtakstatus().isCodeEqualTo(VedtakstatusTypeCode.STOPPES)
                || vedtak.getVedtakstatus().isCodeEqualTo(VedtakstatusTypeCode.STOPPET);
    }

    private boolean hasLopendeAlderspensjonAndLopendeUforegradGreaterThan80(Person person, Sak aldersak, Sak uforesak, Vedtak uforevedtak) {
        if (!canChooseAgeOver67And1Month(person)) {
            if (isSakLopende(aldersak) && isSakLopende(uforesak)) {
                Integer uforegrad = getUforegrad(uforevedtak);
                if (uforegrad != null && uforegrad > 80) {
                    return true;
                }
            }
        }
        return false;
    }

    private Integer getUforegrad(Vedtak vedtak) {
        if (vedtak == null) {
            return null;
        }

        Uforeberegninger uforeBeregninger = new Uforeberegninger();
        uforeBeregninger.addBeregningerFor(vedtak);
        return uforeBeregninger.getGjeldendeUforegrad().orElse(null);
    }

    private boolean canChooseAgeOver67And1Month(Person person) {
        Date fodselsDato = person.getPid().getFodselsdato();
        Date dateWhenUserIs67And1Month = DateUtils.addYears(fodselsDato, 67);
        dateWhenUserIs67And1Month = DateUtils.addMonths(dateWhenUserIs67And1Month, 1);
        int sperrefrist = PselvUtil.fetchSperreVedtakFremITid();
        Date latestPossibleDate = DateUtils.addMonths(new Date(), sperrefrist);
        return latestPossibleDate.after(dateWhenUserIs67And1Month);
    }

    private boolean harManglendeEpsInformasjon(Person person) {
        List<Relasjon> relasjoner = skjemaEndringAlderspensjonActionDelegate.getEpsRelasjoner(person.getPid());
        return !relasjonUtil.containsValidEps(relasjoner, person.getSivilstand());
    }

    private Date getForsteVirkAlderssakGammeltRegelverk(Sak alderssak) {
        List<SakSammendrag> sakSammendragListe = skjemaEndringAlderspensjonActionDelegate.getSakSammendragListe(alderssak.getPenPerson().getFnr());

        for (SakSammendrag sakSammendrag : sakSammendragListe) {
            if (sakSammendrag.getSakId() == alderssak.getSakId()) {
                return sakSammendrag.getFomDato();
            }
        }

        return null;
    }

    private boolean isSakTilBehandlingWithOpenPossibleLopendeKrav(Sak sak) {
        if (sak == null || !sak.getSakStatus().isCodeEqualTo(SakStatusCode.TIL_BEHANDLING)) {
            return false;
        }

        if (sak.getKravHodeListe() == null) {
            return false;
        }

        for (KravHode krav : sak.getKravHodeListe()) {
            if (krav.kravKanGiLopendeYtelse() && krav.isAapentKrav()) {
                return true;
            }
        }

        return false;
    }

    private boolean isSakLopendeAndStoppet(Sak sak, Vedtak vedtak) {
        return isSakLopende(sak) && isVedtakStoppet(vedtak);
    }

    private boolean isSakLopende(Sak sak) {
        return sak != null && sak.getSakStatus().isCodeEqualTo(SakStatusCode.LOPENDE);
    }

    private Beregning getSisteBeregningVedtak(Vedtak uforevedtak) {
        Beregning beregning = null;

        for (Beregning currentBeregning : uforevedtak.getBeregningListe()) {
            if (beregning == null || currentBeregning.getFomDato().after(beregning.getFomDato())) {
                beregning = currentBeregning;
            }
        }

        return beregning;
    }

    private Beregningsperiode getSisteBeregningsperiodeFromVedtak(Vedtak vedtak) {
        Beregningsperiode periode = null;

        if (vedtak.getBeregningsperiodeListe() == null) {
            return periode;
        }

        for (Beregningsperiode beregningsPeriode : vedtak.getBeregningsperiodeListe()) {
            if (periode == null || beregningsPeriode.getFomDato().after(periode.getFomDato())) {
                periode = beregningsPeriode;
            }
        }

        return periode;
    }

    private Sak getAlderssak(List<Sak> saksliste) {
        Sak alderspensjonSak = null;

        if (saksliste == null || saksliste.isEmpty()) {
            return alderspensjonSak;
        }

        for (Sak sak : saksliste) {
            if (sak.getSakType().isCodeEqualTo(SakTypeCode.ALDER)) {
                alderspensjonSak = sak;
            }
        }

        return alderspensjonSak;
    }

    private Vedtak getAldersvedtak(List<Vedtak> vedtakList) {
        return getVedtakMedSisteVirkFom(vedtakList, SakTypeCode.ALDER);
    }

    private Sak getUforesak(List<Sak> saksliste) {
        Sak uforesak = null;

        if (saksliste == null || saksliste.isEmpty()) {
            return uforesak;
        }

        for (Sak sak : saksliste) {
            if (sak.getSakType().isCodeEqualTo(SakTypeCode.UFOREP)) {
                uforesak = sak;
            }
        }

        return uforesak;
    }

    /**
     * Finds a vedtak with type ufore if just one exists and virkFom of this is not in the future. If in the future, or more
     * than one vedtak in list, then state is set to not conduct further ap endring
     */
    private Vedtak getUforevedtak(List<Vedtak> vedtakList, List<EndringAlderspensjonState> stateList) {
        List<Vedtak> uforeVedtakList = getAllVedtakWithSakType(vedtakList, SakTypeCode.UFOREP);

        if (uforeVedtakList.isEmpty()) {
            return null;
        }

        if (uforeVedtakList.size() > 1) {
            stateList.add(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_UFORE_FREM_I_TID);
        } else if (uforeVedtakList.size() == 1) {
            if (DateUtil.isBeforeByDay(Calendar.getInstance().getTime(), uforeVedtakList.get(0).getVirkFom(), false)) {
                stateList.add(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_UFORE_FREM_I_TID);
            } else {
                return uforeVedtakList.get(0);
            }
        }

        return null;
    }

    private List<Vedtak> getAllVedtakWithSakType(List<Vedtak> vedtakList, SakTypeCode sakTypeCode) {
        List<Vedtak> resultList = new ArrayList<>();

        for (Vedtak vedtak : vedtakList) {
            if (vedtak.getSakstype().isCodeEqualTo(sakTypeCode)) {
                resultList.add(vedtak);
            }
        }

        return resultList;
    }

    private Vedtak getVedtakMedSisteVirkFom(List<Vedtak> vedtakList, SakTypeCode sakType) {
        Vedtak foundAldersvedtak = null;

        for (Vedtak vedtak : vedtakList) {
            if (vedtak.getSakstype().isCodeEqualTo(sakType)
                    && (foundAldersvedtak == null || foundAldersvedtak.getVirkFom().before(vedtak.getVirkFom()))) {
                foundAldersvedtak = vedtak;
            }
        }

        return foundAldersvedtak;
    }

    private void addEndringAlderspensjon(List<NavigationStepData> steps, EndringAlderspensjonState state) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(ENDRING_ALDERSPENSJON_TITLE);
        step.setSubflowId(ENDRING_ALDERSPENSJON_FLOW);
        step.setCurrentPage(true);

        if (state.getUserCanApplyEndringAlderspensjon()) {
            NextStepButton nextStepButton = createNextStepButton();
            step.setNextStepButton(nextStepButton);
            CancelLink cancelLink = createCancelLink();
            step.setCancelLink(cancelLink);
        }

        steps.add(step);
        step.setStep(steps.indexOf(step) + 1);
    }

    /**
     * SKS017
     */
    private void addBekreftEndringAlderspensjon(List<NavigationStepData> steps) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(BEKREFT_ENDRING_ALDERSPENSJON_TITLE);
        step.setSubflowId(BEKREFT_ENDRING_ALDERSPENSJON_FLOW);
        CancelLink cancelLink = createCancelLink();
        step.setCancelLink(cancelLink);
        NextStepButton nextStepButton = createSendInnButton();
        step.setNextStepButton(nextStepButton);
        steps.add(step);
        step.setStep(steps.indexOf(step) + 1);
    }

    private NextStepButton createNextStepButton() {
        return new NextStepButton(getMsg(GAA_VIDERE_KNAPP_VALUE), getMsg(GAA_VIDERE_KNAPP_ALT),
                getMsg(GAA_VIDERE_KNAPP_TOOLTIP));
    }

    private NextStepButton createSendInnButton() {
        NextStepButton sendInnButton = new NextStepButton(getMsg(GAA_VIDERE_KNAPP_BEKREFT_VALUE),
                getMsg(GAA_VIDERE_KNAPP_BEKREFT_ALT), getMsg(GAA_VIDERE_KNAPP_BEKREFT_TOOLTIP));

        if (PSELVSecurityFunctions.isReadOnlyBrukerhjelpa()) {
            sendInnButton.disableButton();
        }

        return sendInnButton;
    }

    private CancelLink createCancelLink() {
        return new CancelLink(
                getMsg(AVBRYT_LENKE_VALUE),
                getMsg(AVBRYT_LENKE_MESSAGE),
                getMsg(AVBRYT_LENKE_TOOLTIP),
                getMsg(AVBRYT_LENKE_ALT));
    }

    protected String getMsg(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setSkjemaEndringAlderspensjonActionDelegate(SkjemaEndringAlderspensjonActionDelegate delegate) {
        skjemaEndringAlderspensjonActionDelegate = delegate;
    }

    public void setRelasjonUtil(RelasjonUtil relasjonUtil) {
        this.relasjonUtil = relasjonUtil;
    }

    public void setUttaksgradUtil(UttaksgradUtil uttaksgradUtil) {
        this.uttaksgradUtil = uttaksgradUtil;
    }
}

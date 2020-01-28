package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger;

import static org.apache.commons.lang.StringUtils.isEmpty;

import static no.nav.presentation.pensjon.pselv.common.utils.FacesUrlUtil.comingFrom;
import static no.nav.presentation.pensjon.pselv.common.utils.FacesUrlUtil.getEncodedBackUrl;

import javax.faces.event.AjaxBehaviorEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.AnnenAdresse;
import no.nav.domain.pensjon.common.person.BostedsAdresse;
import no.nav.domain.pensjon.common.person.NorskKonto;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.PersonUtland;
import no.nav.domain.pensjon.common.person.Utbetalingsinformasjon;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCode;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaPersonopplysninger;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.common.utils.AdresseUtil;
import no.nav.presentation.pensjon.pselv.common.utils.SprakUtil;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonAction;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;

/**
 * ActionClass for SKS002 SkjemaPersonOpplysninger. Handles interactions with the logged on user. It then uses the Form
 * Populator to manage the form, and do service calls.
 */
public class SkjemaPersonOpplysningerAction extends SkjemaAlderspensjonCommonAction {

    private static final Log LOG = LogFactory.getLog(SkjemaPersonOpplysningerAction.class);
    private static final String MANGLER_EPS_DATA = "pselv.sks002.avviksmelding.manglerepsdata";
    private static final String PERSONOPPLYSNINGER_SOURCE_ID = "po";
    private static final String CURRENT_PAGE_ID = "/skjema/alderspensjon/";
    private static final String FORM_KEY = "form";

    private SkjemaPersonOpplysningerFormPopulator skjemaPersonOpplysningerFormPopulator;
    private CodesTableManager codesTableManager;
    private MessageSource pselvConfigSource;
    private PersonCommonActionDelegate personCommonActionDelegate;

    /**
     * Updates the step "Personopplysninger" with typed key information from the form.
     */
    @Override
    public Event setTypedKeyInformation(RequestContext context) {
        SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) getFormObject(context);
        String keyInformation = skjemaPersonOpplysningerFormPopulator.buildTypedKeyInformation(form);
        setTypedKeyInformationCurrentStep(context, keyInformation);
        return success();
    }

    /**
     * Method that is called whenever one of the adresse fields (when the user is utflyttet) is changed.
     */
    @Override
    public Event saveSkjema(RequestContext context) {
        lagreSkjema(context);
        return success();
    }

    /**
     * Called when the 'Avbryt og slett skjema' button is pressed.
     */
    @Override
    public Event removeSkjema(RequestContext context) {
        super.removeSkjema(context);
        return success();
    }

    public String getPersonopplysningerUrl() {
        String backUrl = getEncodedBackUrl(getCurrentFacesContext(), PERSONOPPLYSNINGER_SOURCE_ID);
        return CURRENT_PAGE_ID + backUrl;
    }

    public void refreshPersonopplysninger(AjaxBehaviorEvent event) {
        if (!comingFrom(PERSONOPPLYSNINGER_SOURCE_ID, getCurrentFacesContext())) {
            return;
        }

        setRefreshCacheEnabled(true);
        SkjemaPersonOpplysningerForm form = getForm();
        Person person = fetchPerson(form);
        setKontonummer(form, person);
        setTelefonnummer(form, person);
    }

    /**
     * Called when the 'Tilbake' button is pressed. Note! lagreSkjema is removed due to uncertainties regarding this functionality.
     * This method is still here, in case of future changes.
     */
    public Event tilbake(RequestContext context) {
        return success();
    }

    /**
     * Brukers fornavn og etternavn skal finnes.
     */
    public Event checkPersonIntegrity(RequestContext context) {
        SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) getFormObject(context);

        if (isValid(form.getBruker())) {
            return success();
        }

        pselvTransferObject.put(PselvTransferObject.FEILMELDING, getMsg(MANGLER_EPS_DATA));
        pselvTransferObject.put(PselvTransferObject.TITLE_KEY, form.getPageTitle());
        return error();
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public SkjemaPersonOpplysningerFormPopulator getSkjemaPersonOpplysningerFormPopulator() {
        return skjemaPersonOpplysningerFormPopulator;
    }

    public void setSkjemaPersonOpplysningerFormPopulator(SkjemaPersonOpplysningerFormPopulator skjemaPersonOpplysningerFormPopulator) {
        this.skjemaPersonOpplysningerFormPopulator = skjemaPersonOpplysningerFormPopulator;
    }

    public void setPersonCommonActionDelegate(PersonCommonActionDelegate personCommonActionDelegate) {
        this.personCommonActionDelegate = personCommonActionDelegate;
    }

    public MessageSource getPselvConfigSource() {
        return pselvConfigSource;
    }

    public void setPselvConfigSource(MessageSource source) {
        pselvConfigSource = source;
    }

    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) super.createFormObject(context);
        init(form);
        log(form);
        return form;
    }

    /**
     * Initializes the form with the user's data. The data is populated either from the user's profile (tpen) or from a previously stored skjema.
     */
    private void init(SkjemaPersonOpplysningerForm form) {
        SkjemaPersonopplysninger personopplysninger = form.getSkjema().getSkjemaPersonopplysninger();
        SkjemaDataForPreComplementing skjemaData = form.getInputData().getSkjemaData();

        if (personopplysninger == null) {
            boolean isFlyktning = skjemaData == null ? false : skjemaData.getFlyktning();
            skjemaPersonOpplysningerFormPopulator.populateForm(form, isFlyktning);
            return;
        }

        SkjemaPersonOpplysningerMapper mapper = new SkjemaPersonOpplysningerMapper();
        SkjemaPersonOpplysningerForm updatedForm = mapper.mapFromSkjemaDOToForm(personopplysninger, form, skjemaData);
        Person person = hentPerson(personopplysninger.getPenPerson().getFnr());
        updatedForm = mapFromPersonToForm(person, updatedForm);
        skjemaPersonOpplysningerFormPopulator.setupForm(updatedForm);
    }

    private Person hentPerson(Pid pid) {
        try {
            Person person = personCommonActionDelegate.hentPerson(pid, isRefreshCacheEnabled());
            setRefreshCacheEnabled(false);
            return person;
        } catch (PersonIkkeFunnetException e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    private void log(SkjemaPersonOpplysningerForm form) {
        if (!logger.isDebugEnabled()) {
            return;
        }

        logger.debug("Kode = " + form.getBruker().getDiskresjonskode());
        logger.debug("variant ForsørginsTilleg : " + form.isForsorgningstilleggSoknad());
        logger.debug("variant AldersPenson : " + form.isAldersPensjonSoknad());
    }

    /**
     * Stores the skjema by delegating the call towards the saveSkjema method in the superclass.
     * This class also delegates calls towards mapper classes that handle the mapping to the correct DTO.
     */
    private void lagreSkjema(RequestContext context) {
        SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) super.getFormObject(context);
        SkjemaPersonopplysninger personopplysninger = new SkjemaPersonOpplysningerMapper().mapFromFormToSkjemaDO(form, codesTableManager);
        form.getSkjema().setSkjemaPersonopplysninger(personopplysninger);
        super.saveSkjema(context);
    }

    private SkjemaPersonOpplysningerForm mapFromPersonToForm(Person person, SkjemaPersonOpplysningerForm form) {
        setNavn(person, form);
        setAdresse(person, form);
        setTelefon(person, form);
        setStatsborgerskap(person.getPersonUtland(), form);
        setKontonummer(person, form);
        setValgtMalform(person, form);
        return form;
    }

    private void setValgtMalform(Person person, SkjemaPersonOpplysningerForm form) {
        form.setSprakMalformListe(SprakUtil.getSprakMalformListe(codesTableManager));
        form.setValgtSprakMalform(person.getSprakKode());
    }

    private void setNavn(Person person, SkjemaPersonOpplysningerForm form) {
        form.setFornavn(person.getFornavn());
        form.setMellomnavn(person.getMellomnavn());
        form.setEtternavn(person.getEtternavn());
    }

    private void setAdresse(Person person, SkjemaPersonOpplysningerForm form) {
        AdresseUtil util = new AdresseUtil();
        Person bruker = form.getBruker();
        BostedsAdresse bostedsadresse = bruker.getBostedsAdresse();

        if (util.hasBostedsadresse(bostedsadresse)) {
            form.setBostedAdresse(bostedsadresse);
            return;
        }

        AnnenAdresse tilleggsadresse = bruker.getTilleggsAdresse();

        if (util.hasTilleggsadresse(tilleggsadresse)) {
            form.setTilleggsAdresse(tilleggsadresse);
            return;
        }

        // User is utflyttet; use updated adresse from person
        form.setUtvandretAdresse(getUtvandretAdresse(person.getUtenlandsAdresse()));
    }

    private void setTelefon(Person person, SkjemaPersonOpplysningerForm form) {
        form.setTelefonNummerHjem(person.getTlfPrivat());
        form.setTelefonNummerArbeid(person.getTlfJobb());
        form.setTelefonNummerMob(person.getTlfMobil());
    }

    private void setKontonummer(Person person, SkjemaPersonOpplysningerForm form) {
        form.setKontonummerNorge(person.getUtbetalingsinformasjon().getNorskKonto().getKontonummer());
        form.setKontonummerPrefilled(true);
    }

    private void setStatsborgerskap(PersonUtland person, SkjemaPersonOpplysningerForm form) {
        String statsborgerKode = person.getStatsborgerKode();

        if (statsborgerKode == null) {
            // No data exists regarding statsborgerskap. Assume Norwegian citizen.
            // An alternative is that the user is statsløs, but an existing value should exist for this option.
            if (LOG.isDebugEnabled()) {
                LOG.debug("No statsborgerskap on person");
            }

            form.setStatsborgerskap(Land3TegnCode.NOR.name());
            return;
        }

        form.setStatsborgerskap(statsborgerKode);
        form.setNorskStatsborger(Land3TegnCode.NOR.name().equals(statsborgerKode));
    }

    private static AnnenAdresse getUtvandretAdresse(AnnenAdresse source) {
        AnnenAdresse utvandretAdresse = new AnnenAdresse();
        utvandretAdresse.setAdresselinje1(source.getAdresselinje1());
        utvandretAdresse.setAdresselinje2(source.getAdresselinje2());
        utvandretAdresse.setAdresselinje3(source.getAdresselinje3());
        String landkode = source.getLandkode();

        if (landkode != null) {
            utvandretAdresse.setLandkode(landkode);
        }

        return utvandretAdresse;
    }

    private static boolean isValid(Person person) {
        return Pid.isValidPid(person.getPid().getPid())
                && !isEmpty(person.getFornavn())
                && !isEmpty(person.getEtternavn());
    }

    private Person fetchPerson(SkjemaPersonOpplysningerForm form) {
        Pid pid = new Pid(form.getFnr());
        return hentPerson(pid);
    }

    private SkjemaPersonOpplysningerForm getForm() {
        return (SkjemaPersonOpplysningerForm) getRequestContext().getFlowScope().get(FORM_KEY);
    }

    private static void setKontonummer(SkjemaPersonOpplysningerForm form, Person person) {
        Utbetalingsinformasjon utbetalingsinformasjon = person.getUtbetalingsinformasjon();

        if (utbetalingsinformasjon == null) {
            return;
        }

        NorskKonto konto = utbetalingsinformasjon.getNorskKonto();

        if (konto == null) {
            return;
        }

        form.setKontonummerNorge(konto.getKontonummer());

        if (hasVisibleCharacters(form.getKontonummerNorge())) {
            form.setKontonummerPrefilled(true);
        }
    }

    private static void setTelefonnummer(SkjemaPersonOpplysningerForm form, Person person) {
        form.setTelefonNummerHjem(person.getTlfPrivat());
        form.setTelefonNummerArbeid(person.getTlfJobb());
        form.setTelefonNummerMob(person.getTlfMobil());
    }

    private static boolean hasVisibleCharacters(String value) {
        return value != null && !value.trim().isEmpty();
    }
}

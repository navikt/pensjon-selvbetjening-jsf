//package pselv2.apsoknad.skjema.ap.po;
package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.codestable.CodesTablePeriodic;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCti;
import no.nav.presentation.pensjon.common.security.tag.CommonPensjonSecurityFunctions;
import no.nav.presentation.pensjon.pselv.common.utils.AdresseUtil;

/**
 * Handles events from triggered actions in the Personopplysninger screen.
 */
public class SkjemaPersonOpplysningerFormPopulator {

    private CodesTableManager codesTableManager;
    private MessageSource messageSource;
    private static final String DEFAULT_LAND_VALUE = "Velg land";
    private static final String NORGE_LAND_CODE = "NOR";
    private static final String LEFT_PARENTHESIS = " (";
    private static final String RIGHT_PARENTHESIS = ")";
    private static final String NOKKELINFO_PERSON_UTVANDRET = "pselv.sks002.statisk_tekst.nokkelinfopersonutvandret";
    private static final String NOKKELINFO_KONTONUMMER = "pselv.sks002.statisk_tekst.nokkelinfokontonummer";
    private static final String OPPGITT_FLYKTNING = "pselv.sks002.dialogboks.oppgittflyktningskjema";

    /**
     * Called during init of the form. Handles the setup of the form and handles the initial population of the form.
     */
    public void setupForm(SkjemaPersonOpplysningerForm form) {
        populateMalform(form);
        populateLandListform(form);
        form.setShowDialogboks(form.getFlyktning());
        form.setDialogboksMessage(getMsg(OPPGITT_FLYKTNING));

        if (form.getBruker() == null) {
            return;
        }

        AdresseUtil util = new AdresseUtil();
        form.setHarBostedAdresse(util.hasBostedsadresse(form.getBruker().getBostedsAdresse()));
        form.setHarTilleggsAdresse(util.hasTilleggsadresse(form.getBruker().getTilleggsAdresse()));
    }

    /**
     * main method used to populate the form object based on the user logged in. user is already stored on the form (done in
     * super class) så this metid retrives Person info and populate the form data based on this users
     */
    public void populateForm(SkjemaPersonOpplysningerForm form, boolean isFlyktning) {
        setupForm(form);
        Person person = form.getBruker();

        if (CommonPensjonSecurityFunctions.harDiskresjon(person)) {
            form.setDiskresjon(true);
        }
        if (CommonPensjonSecurityFunctions.harDiskresjonsKode7(person)) {
            form.setDiskresjonkode7(true);
        }

        // Set the various addresses and find out if person is utvandret or not

        if (person != null) {
            String utvandretTil = person.getPersonUtland().getUtvandretTil();
            if (utvandretTil == null || utvandretTil.length() < 1) {
                form.setPersonUtVandret(false);
            } else {
                form.setPersonUtVandret(true);
            }

            form.setFornavn(person.getFornavn());
            form.setEtternavn(person.getEtternavn());
            form.setMellomnavn(person.getMellomnavn());
            form.setFnr(person.getFodselsnummer());
            form.setBostedAdresse(person.getBostedsAdresse());
            form.setUtvandretAdresse(person.getUtenlandsAdresse());
            form.setTilleggsAdresse(person.getTilleggsAdresse());
            form.setTelefonNummerMob(person.getTlfMobil());
            form.setTelefonNummerHjem(person.getTlfPrivat());
            form.setTelefonNummerArbeid(person.getTlfJobb());
            form.setStatsborgerskap(person.getPersonUtland().getStatsborgerKode());
            String statsborgerskap = form.getStatsborgerskap();

            if (statsborgerskap != null && statsborgerskap.equalsIgnoreCase(NORGE_LAND_CODE)) {
                form.setNorskStatsborger(true);
            } else {
                form.setNorskStatsborger(false);
            }

            form.setFlyktning(isFlyktning);
            form.setKontonummerNorge(person.getUtbetalingsinformasjon().getNorskKonto().getKontonummer());
            String kontonummerNorge = form.getKontonummerNorge();

            if (kontonummerNorge != null && !kontonummerNorge.trim().isEmpty()) {
                form.setKontonummerPrefilled(true);
            }

            form.setValgtSprakMalform(person.getSprakKode());
        }
    }

    private void populateMalform(SkjemaPersonOpplysningerForm form) {
        CodesTablePeriodic<SprakCti, SprakCode, String> ctp = codesTableManager.getCodesTablePeriodic(SprakCti.class);
        SprakCti bokmal = ctp.getCodesTableItem(SprakCode.NB);
        SprakCti nynorsk = ctp.getCodesTableItem(SprakCode.NN);
        SprakCti engelsk = ctp.getCodesTableItem(SprakCode.EN);

        // Populate list
        List<SelectItem> sprakMalformListe = new ArrayList<>();
        sprakMalformListe.add(new SelectItem(bokmal.getCodeAsString(), bokmal.getDecode()));
        sprakMalformListe.add(new SelectItem(nynorsk.getCodeAsString(), nynorsk.getDecode()));
        sprakMalformListe.add(new SelectItem(engelsk.getCodeAsString(), engelsk.getDecode()));

        form.setSprakMalformListe(sprakMalformListe);
    }

    private void populateLandListform(SkjemaPersonOpplysningerForm form) {
        List<SelectItem> landListe = new ArrayList<>();
        landListe.add(new SelectItem("", DEFAULT_LAND_VALUE));
        CodesTablePeriodic<Land3TegnCti, Land3TegnCode, String> land = codesTableManager.getCodesTablePeriodic(Land3TegnCti.class);

        for (Land3TegnCti cti : land.getCodesTableItemsValidToday()) {
            landListe.add(new SelectItem(cti.getCodeAsString(), cti.getDecode()));
        }

        form.setLandListe(landListe);
    }

    public CodesTableManager getCodesTableManager() {
        return codesTableManager;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    /**
     * Builds the key information text that is shown in view after it has been visited. For a description of how this should be
     * set up, see design.
     */
    public String buildTypedKeyInformation(SkjemaPersonOpplysningerForm form) {
        String name = form.getFormatedUserName();
        String accountNumber = getMsg(NOKKELINFO_KONTONUMMER, new String[]{form.getKontonummerNorge()});
        String address;

        if (form.isPersonUtVandret()) {
            Land3TegnCti land = codesTableManager.getCodesTablePeriodic(Land3TegnCti.class).getCodesTableItem(
                    form.getUtvandretAdresse().getLandkode());
            address = getMsg(NOKKELINFO_PERSON_UTVANDRET, new String[]{land.getDecode()});
        } else if (form.getHarBostedAdresse()) {
            address = form.getBoadresse1() + ", " + form.getFormatedPostAdress();
        } else {
            address = form.getTilleggsadresse1() + ", " + form.getTilleggAdrPostAdrFormated();
        }

        if (address.trim().equals(",")) {
            return name + "<br/>" + accountNumber;
        } else {
            return name + "<br/>" + address + "<br/>" + accountNumber;
        }
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String key, String[] params) {
        return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
    }
}


package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCode;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaPersonopplysninger;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;

/**
 * Maps to and from the common domain objekct model and the form object used in the JSF.
 */
public class SkjemaPersonOpplysningerMapper {

    private static final String NORSKLAND_KODE = "NOR";
    private static final Log LOG = LogFactory.getLog(SkjemaPersonOpplysningerMapper.class);
    private CodesTableManager ctm;

    SkjemaPersonopplysninger mapFromFormToSkjemaDO(SkjemaPersonOpplysningerForm form, CodesTableManager codesTableManager) {
        SkjemaPersonopplysninger skjemaPersonopplysninger = new SkjemaPersonopplysninger();
        PenPerson person = new PenPerson();
        person.setFnr(new Pid(form.getFnr()));
        skjemaPersonopplysninger.setPenPerson(person);
        skjemaPersonopplysninger.setUtvandret(form.isPersonUtVandret());

        // Set statsborgerskap, if radiobutton "Norsk" is selected the value for
        // NORWAY may not be selected in the dropdown so
        // it has to be retrieved programatically
        if (form.isNorskStatsborger()) {
            skjemaPersonopplysninger.setStatsborgerskap(codesTableManager.getCodesTablePeriodic(Land3TegnCti.class)
                    .getCodesTableItem(Land3TegnCode.NOR));
            if (LOG.isDebugEnabled()) {
                LOG.debug("Setting statsborgerskap to NOR since form.norskStatsborger is true.");
            }
        } else {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Getting statsborgerskap-landCti from code:" + form.getStatsborgerskap());
            }
            skjemaPersonopplysninger.setStatsborgerskap(codesTableManager.getCodesTablePeriodic(Land3TegnCti.class)
                    .getCodesTableItem(form.getStatsborgerskap()));
        }

        skjemaPersonopplysninger.setFlyktning(form.getFlyktning());
        skjemaPersonopplysninger.setValgtMalform(codesTableManager.getCti(SprakCode.valueOf(form.getValgtSprakMalform())));
        return skjemaPersonopplysninger;
    }

    /**
     * Maps from the domain object into the form object so that the page can display data that
     * is already stored. Examples of this use case is when a user is revisiting the SkjemaPersonOpplysninger page in the "page
     * flow" before he/she commits the process. The reason currentForm is used as a parameter is besause some data in the
     * current form has to be reset, while other data must be mantained. That is why a new form object can not be created..
     */
    public SkjemaPersonOpplysningerForm mapFromSkjemaDOToForm(SkjemaPersonopplysninger skjema,
                                                              SkjemaPersonOpplysningerForm currentForm,
                                                              SkjemaDataForPreComplementing skjemaData) {

        currentForm.setFnr(skjema.getPenPerson().getFnr().getPid());
        /*
         * get bostedsadresse from bruker object (not skjema) if person is not utvandret
         */
        if (skjema.getUtvandret() == null || !skjema.getUtvandret()) {
            currentForm.setPersonUtVandret(false);
        } else {
            currentForm.setPersonUtVandret(true);
        }

        if (skjema.getStatsborgerskap() != null) {
            currentForm.setStatsborgerskap(skjema.getStatsborgerskap().getCodeAsString());

            if (LOG.isDebugEnabled()) {
                LOG.debug("Current statsborgerskap in SkjemaPersonopplysninger is "
                        + skjema.getStatsborgerskap().getCodeAsString());
            }

            if (Land3TegnCode.NOR.name().equals(skjema.getStatsborgerskap().getCodeAsString())) {
                currentForm.setNorskStatsborger(true);
            } else {
                currentForm.setNorskStatsborger(false);
            }

            if (LOG.isDebugEnabled()) {
                LOG.debug("Form value norskStatsborger=" + currentForm.isNorskStatsborger());
            }
        } else {
            // No data exists regarding statsborgerskap in the database. Asume
            // norwegian citicen. An alternative is that the user is statsl?s,
            // but an existing value should exist for this option.
            if (LOG.isDebugEnabled()) {
                LOG.debug("No statsborgerskap set in SkjemaPersonopplysninger (defaulting to NOR).");
            }

            currentForm.setStatsborgerskap(NORSKLAND_KODE);
        }

        Boolean flyktning = skjemaData == null ? skjema.getFlyktning() : skjemaData.getFlyktning();
        currentForm.setFlyktning(flyktning);
        return currentForm;
    }

    public CodesTableManager getCtm() {
        return ctm;
    }

    public void setCtm(CodesTableManager ctm) {
        this.ctm = ctm;
    }
}

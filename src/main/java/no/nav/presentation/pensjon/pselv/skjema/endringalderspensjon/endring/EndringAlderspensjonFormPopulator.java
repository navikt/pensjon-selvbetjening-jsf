package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring;

import static no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode.UT_ET;
import static no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode.UT_TFB;
import static no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode.UT_TSB;
import static no.stelvio.presentation.binding.context.MessageContextUtil.getMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import javax.faces.model.SelectItem;

import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.SkjemaEndringAlderspensjonCommonInputData;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.webflow.execution.RequestContextHolder;

import no.stelvio.common.util.DateUtil;
import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.domain.pensjon.kjerne.beregning.BarnetilleggFellesbarn;
import no.nav.domain.pensjon.kjerne.beregning.BarnetilleggSerkullsbarn;
import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.beregning.Ektefelletillegg;
import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.sak.Uttaksgrad;
import no.nav.domain.pensjon.kjerne.vedtak.Beregningsperiode;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.PselvUtil;
import no.nav.presentation.pensjon.pselv.common.enums.EndringAlderspensjonState;
import no.nav.presentation.pensjon.pselv.common.utils.UttaksgradUtil;
import no.nav.presentation.pensjon.pselv.simulering.personopplysninger.support.Uforeberegninger;
import no.nav.presentation.pensjon.pselv.skjema.SkjemaCommonConstants;

/**
 * Form populator for endring alderspensjon SKS016
 */
public class EndringAlderspensjonFormPopulator {

    private static final String KAN_IKKE_SOKE_PGA_MANGLENDE_INFO_OM_EPS = "pselv.sks016.avviksmelding.manglerepsdata";
    private static final int YEAR_75 = 75;
    private static final int YEAR_62 = 62;
    private static final Integer FULLT_UTTAK = 100;
    private static final String HJELPAPOGUP = "pselv.sks016.helpinpage.hjelpapogup";
    private static final String HJELPKUN0OG100 = "pselv.sks016.helpinpage.hjelpkun0og100";
    private static final String HJELPINKLUDERENYOPPTJENING = "pselv.sks016.helpinpage.hjelpinkluderenyopptjening";
    private static final String HJELPENDREUTTAKSGRAD = "pselv.sks016.helpinpage.hjelpendreuttaksgrad";
    private static final String KAN_IKKE_SOKE = "pselv.sks016.statisk_tekst.kanikkesoke";
    private static final String KAN_IKKE_SOKE_PGA_FOR_HOY_UFORE = "pselv.sks016.statisk_tekst.kanikkesokepgaforhoyuforegrad";
    private static final String KAN_IKKE_SOKE_PGA_SOKNAD_INNE = "pselv.sks016.statisk_tekst.kanikkesokepgasoknadinne";
    private static final String KAN_IKKE_SOKE_PGA_UFORE_INNE = "pselv.sks016.statisk_tekst.kanikkesokepgauforeinne";
    private static final String KAN_IKKE_SOKE_PGA_VEDTAK_STOPPET = "pselv.sks016.statisk_tekst.kanikkesokepgavedtakstoppet";
    private static final String INFO_TEKST_FT_AP = "pselv.sks016.statisk_tekst.infotekstftap";
    private static final String INFO_TEKST_FT_UP = "pselv.sks016.statisk_tekst.infotekstftup";
    private static final String INFO_TEKST_UP = "pselv.sks016.statisk_tekst.infotekstup";
    private static final String FOR_GAMMEL_75 = "pselv.sks016.statisk_tekst.forgammel75";
    private static final String VELG_DATO = "pselv.sks016.statisk_tekst.velgdato";
    private static final String VELG_UTTAKSGRAD = "pselv.sks016.statisk_tekst.velgnyuttaksgrad";
    private static final String GJELDENDE_ALDERSPENSJON = "pselv.sks016.statisk_tekst.gjeldendealderspensjon";
    private static final String GJELDENDE_ALDERSPENSJON_1943 = "pselv.sks016.statisk_tekst.gjeldendealderspensjon1943kull";
    private static final String INNVILGET_AP_FREM_I_TID = "pselv.sks016.statisk_tekst.alderspensjonframitid";
    private static final String KAN_IKKE_SOKE_PGA_UFORE_ENDR_FREM_I_TID = "pselv.sks016.avviksmelding.ikkesokepgauforeendringframitid";
    private static final int YEAR_1943 = 1943;

    public void populateFormUserCanApply(List<Vedtak> allevedtak, Sak alderspensjonsak, EndringAlderspensjonForm form) {
        loadForsorgerTilleggMelding(form, allevedtak);
        loadUforeGradMelding(form, allevedtak);
        fillForm(form, alderspensjonsak, allevedtak);
        populateFormWithHandlingsvalgBasedOnState(form);
    }

    private void populateFormWithHandlingsvalgBasedOnState(EndringAlderspensjonForm form) {
        EndringAlderspensjonState state = form.getInputData().getEndringAlderspensjonState();
        String handlingsValg = null;

        if (state.equals(EndringAlderspensjonState.KAN_SOKE_ENDRE_UTTAKSGRAD)) {
            handlingsValg = form.getInkluderOpptjeningOgEndreUttakValue();
        } else if (state.equals(EndringAlderspensjonState.KAN_SOKE_INKLUDER_NY_OPPTJENING)) {
            handlingsValg = form.getInkluderOpptjeningValue();
        }

        form.setHandlingsValg(handlingsValg);
    }

    private Vedtak getAldersvedtak(List<Vedtak> vedtakList) {
        return getVedtakMedSisteVirkFom(vedtakList, SakTypeCode.ALDER);
    }

    private Vedtak getUforevedtak(List<Vedtak> vedtakList) {
        return getVedtakMedSisteVirkFom(vedtakList, SakTypeCode.UFOREP);
    }

    private Vedtak getVedtakMedSisteVirkFom(List<Vedtak> vedtakList, SakTypeCode sakType) {
        Vedtak aldersvedtak = null;

        if (vedtakList == null) {
            return aldersvedtak;
        }

        for (Vedtak vedtak : vedtakList) {
            if (vedtak.getSakstype().isCodeEqualTo(sakType)
                    && (aldersvedtak == null || aldersvedtak.getVirkFom().before(vedtak.getVirkFom()))) {
                aldersvedtak = vedtak;
            }
        }

        return aldersvedtak;
    }

    private void loadForsorgerTilleggMelding(EndringAlderspensjonForm form, List<Vedtak> allevedtak) {
        Vedtak aldersvedtak = getAldersvedtak(allevedtak);
        Vedtak uforevedtak = getUforevedtak(allevedtak);
        Beregningsperiode periodeAldersvedtak = getSisteBeregningsperiode(aldersvedtak);
        Beregningsperiode periodeUforevedtak = getSisteBeregningsperiode(uforevedtak);

        // 'periodeAldersvedtak.getBeregningsresultat() != null' needed to handle 1943 when the user has AP Kap 19 tom 2010
        if (periodeAldersvedtak != null && periodeAldersvedtak.getBeregningsresultat() != null && harFullPensjon(periodeAldersvedtak, uforevedtak)) {
            if (uforevedtak != null && harUforeForsorgerTillegg(periodeUforevedtak)) {
                form.setMelding(getMessage(INFO_TEKST_FT_UP));
                return;
            }

            if (harAldersForsorgerTillegg(periodeAldersvedtak)) {
                form.setMelding(getMessage(INFO_TEKST_FT_AP));
                return;
            }
        }

        if (aldersvedtak != null && aldersvedtak.getBeregningListe() != null && !aldersvedtak.getBeregningListe().isEmpty()) {
            Beregning beregning = getSenesteBeregning(aldersvedtak);
            if (harYtelseskomponent(beregning)) {
                form.setMelding(getMessage(INFO_TEKST_FT_AP));
            }
        }
    }

    private boolean harYtelseskomponent(Beregning beregning) {
        if (harBarnetilleggFellesbarnYtelsesKomponentStorreEnnNull(beregning.getBarnetilleggFellesbarnListe())) {
            return true;
        }

        if (harBarnetilleggSerkullsbarnYtelsesKomponentStorreEnnNull(beregning.getBarnetilleggSerkullsbarnListe())) {
            return true;
        }

        if (harEktefelletilleggYtelsesKomponentStorreEnnNull(beregning.getEktefelletilleggListe())) {
            return true;
        }

        return false;
    }

    private boolean harEktefelletilleggYtelsesKomponentStorreEnnNull(List<Ektefelletillegg> ektefelletilleggListe) {
        if (ektefelletilleggListe == null) {
            return false;
        }

        for (Ektefelletillegg ektefelletillegg : ektefelletilleggListe) {
            if (ektefelletillegg.getNetto() != null && ektefelletillegg.getNetto() > 0) {
                return true;
            }
            if (ektefelletillegg.getBrutto() != null && ektefelletillegg.getBrutto() > 0) {
                return true;
            }
        }

        return false;
    }

    private boolean harBarnetilleggSerkullsbarnYtelsesKomponentStorreEnnNull(List<BarnetilleggSerkullsbarn> barnetilleggSerkullsbarnListe) {
        if (barnetilleggSerkullsbarnListe == null) {
            return false;
        }

        for (BarnetilleggSerkullsbarn barnetilleggSerkullsbarn : barnetilleggSerkullsbarnListe) {
            if (barnetilleggSerkullsbarn.getNetto() != null && barnetilleggSerkullsbarn.getNetto() > 0) {
                return true;
            }
            if (barnetilleggSerkullsbarn.getBrutto() != null && barnetilleggSerkullsbarn.getBrutto() > 0) {
                return true;
            }
        }

        return false;
    }

    private boolean harBarnetilleggFellesbarnYtelsesKomponentStorreEnnNull(List<BarnetilleggFellesbarn> barnetilleggFellesbarnListe) {
        if (barnetilleggFellesbarnListe == null) {
            return false;
        }

        for (BarnetilleggFellesbarn barnetillegg : barnetilleggFellesbarnListe) {
            if (barnetillegg.getNetto() != null && barnetillegg.getNetto() > 0) {
                return true;
            }
            if (barnetillegg.getBrutto() != null && barnetillegg.getBrutto() > 0) {
                return true;
            }
        }

        return false;
    }

    private Beregning getSenesteBeregning(Vedtak vedtak) {
        Beregning beregning = null;

        if (vedtak == null || vedtak.getBeregningListe() == null) {
            return null;
        }

        for (Beregning currentBeregning : vedtak.getBeregningListe()) {
            if (beregning == null || beregning.getFomDato().before(currentBeregning.getFomDato())) {
                beregning = currentBeregning;
            }
        }

        return beregning;
    }

    private boolean harFullPensjon(Beregningsperiode periodeAldersvedtak, Vedtak uforevedtak) {
        int uforegrad = 0;
        int aldersgrad = periodeAldersvedtak.getBeregningsresultat().getUttaksgrad();

        if (uforevedtak != null) {
            uforegrad = getUforegrad(uforevedtak);
        }

        return aldersgrad + uforegrad == FULLT_UTTAK;
    }

    /**
     * Returns whether forsorgertillegg exist by looking for a ytelseskomponent of one of the following types: UT_ET, UT_TFB or UT_TSB.
     */
    private boolean harUforeForsorgerTillegg(Beregningsperiode periodeUforevedtak) {
        for (Ytelseskomponent ytelseskomponent : periodeUforevedtak.getBeregningsresultat().hentYtelseskomponentListe(false)) {
            if ((ytelseskomponent.getBrutto() > 0 && EnumSet.of(UT_ET, UT_TFB, UT_TSB).contains(ytelseskomponent.getYtelseKomponentType()))) {
                return true;
            }
        }

        return false;
    }

    private boolean harAldersForsorgerTillegg(Beregningsperiode periodeAldersvedtak) {
        boolean hasAlderspensjonWithForsorgertillegg = false;

        for (Ytelseskomponent komponent : periodeAldersvedtak.getBeregningsresultat().getPensjonUnderUtbetaling().getYtelseskomponenter()) {
            if (isCodeEqualsToETOrTFBOrTSB(komponent) && komponent.getBrutto() > 0) {
                hasAlderspensjonWithForsorgertillegg = true;
            }
        }

        return hasAlderspensjonWithForsorgertillegg;
    }

    private boolean isCodeEqualsToETOrTFBOrTSB(Ytelseskomponent komponent) {
        return komponent.getYtelseKomponentType().equals(YtelseKomponentTypeCode.ET)
                || komponent.getYtelseKomponentType().equals(YtelseKomponentTypeCode.TFB)
                || komponent.getYtelseKomponentType().equals(YtelseKomponentTypeCode.TSB);
    }

    /**
     * Fills the errormessage indicated by inputdatas endringalderspensjonstate into the form
     */
    public void fillError(EndringAlderspensjonForm form) {
        form.setMelding(getError(form));
    }

    private String getError(EndringAlderspensjonForm endringAldersPensjonForm) {
        SkjemaEndringAlderspensjonCommonInputData inputData = endringAldersPensjonForm.getInputData();
        EndringAlderspensjonState state = inputData.getEndringAlderspensjonState();

        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE)) {
            return getMessage(KAN_IKKE_SOKE);
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_ALDER_SOKNAD_INNE)) {
            return getMessage(KAN_IKKE_SOKE_PGA_SOKNAD_INNE);
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_UFORE_SOKNAD_INNE)) {
            return getMessage(KAN_IKKE_SOKE_PGA_UFORE_INNE);
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_FOR_HOY_UFOREGRAD)) {
            return getMessage(KAN_IKKE_SOKE_PGA_FOR_HOY_UFORE, new Object[]{inputData.getLopendeUforegrad()});
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_VEDTAK_ALDER_STOPPET)) {
            return getMessage(KAN_IKKE_SOKE_PGA_VEDTAK_STOPPET);
        }
        if (state.equals(EndringAlderspensjonState.BRUKER_FYLT_75)) {
            return getMessage(FOR_GAMMEL_75);
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_UFORE_FREM_I_TID)) {
            return getMessage(KAN_IKKE_SOKE_PGA_UFORE_ENDR_FREM_I_TID);
        }
        if (state.equals(EndringAlderspensjonState.KAN_IKKE_SOKE_PGA_MANGLENDE_INFO_OM_EPS)) {
            return getMessage(KAN_IKKE_SOKE_PGA_MANGLENDE_INFO_OM_EPS, getLinkParam(SkjemaCommonConstants.PUS012_SKJEMAOVERSIKT_FLOW));
        }
        return getMessage(KAN_IKKE_SOKE);
    }

    private String[] getLinkParam(String link) {
        String contextPath = RequestContextHolder.getRequestContext().getExternalContext().getContextPath();
        String param = contextPath + link;
        return new String[]{param};
    }

    /**
     * Fills the form with information about existing and possible new uttakstidspunkter and grader in addition to information
     * about inkluder ny opptjening
     */
    private void fillForm(EndringAlderspensjonForm form, Sak alderssak, List<Vedtak> allevedtak) {
        Vedtak aldersvedtak = getAldersvedtak(allevedtak);

        // SIR 222027, The setGjeldendeUttaksgrad has to be done before we try to get Uttaksgrader.
        if (isGammeltRegelverk(aldersvedtak)) {
            populateGjeldendeUttakGammeltRegelverk(form, aldersvedtak);
        } else {
            populateGjeldendeUttakNyttRegelverk(form, alderssak);
        }

        populateYtelseFramITid(form, alderssak);
        Vedtak uforevedtak = getUforevedtak(allevedtak);
        Integer uforegrad = getUforegrad(uforevedtak);
        List<Integer> uttaksgrader = getUttaksgrader(form, uforegrad, alderssak.getUttaksgradhistorikk(), aldersvedtak);
        List<Date> uttakstidspunkter = getUttakstidspunkter();
        loadDefaultInkluderOpptjeningHjelpeMelding(form);
        loadDefaultEndreUttaksgradHjelpeMelding(form);
        form.setBrukerKanSoke(true);
        populateUttaksgrad(uttaksgrader, form);
        populateUttakstidspunkter(uttakstidspunkter, form, alderssak.bestemForsteVirkningsdato());
        form.setUforegrad(uforegrad);
        form.setEndringOpptjeningAldersvedtakFom(form.getGjeldendeUttakstidspunkt());

        boolean visInkluderNyOpptjening = false;
        if (alderssak.getUttaksgradhistorikk() != null) {
            visInkluderNyOpptjening = getVisInkluderNyOpptjening(alderssak.getUttaksgradhistorikk());
        }

        form.setVisInkluderNyOpptjening(visInkluderNyOpptjening);

        if (visInkluderNyOpptjening) {
            Uttaksgrad uttaksgrad = UttaksgradUtil.getLatestUttaksgrad(alderssak.getUttaksgradhistorikk());
            Date uttakstidspunktOpptjening = findForsteUttakstidspunktAfter12MonthsOfGivenData(uttaksgrad.getFomDato());
            form.setInkluderOpptjeningstidspunkt(uttakstidspunktOpptjening);
        } else {
            form.setInkluderOpptjeningstidspunkt(null);
        }
    }

    /**
     * Returns the uforegrad.
     * If uforetrygd it gets it from the vilkarsvedtak.uforegradberegningsvilkar.getUforegrad.
     * If uforepensjon it checks both beregningsperiode and beregning.
     */
    private Integer getUforegrad(Vedtak vedtak) {
        if (vedtak == null) {
            return null;
        }

        KravHode krav = vedtak.getKravhode();
        Integer uforegrad = null;

        if (krav.isUforetrygd()) {
            Uforeberegninger uforeBeregninger = new Uforeberegninger();
            uforeBeregninger.addBeregningerFor(vedtak);
            uforegrad = uforeBeregninger.getGjeldendeUforegrad().orElse(null);
        } else if (krav.isUforepensjon()) {
            Beregningsperiode periode = getSisteBeregningsperiode(vedtak);
            uforegrad = getUforegradFromPeriode(periode);
            if (uforegrad == null) {
                Beregning beregning = getSenesteBeregning(vedtak);
                uforegrad = getUforegradFromBeregning(beregning);
            }
        }

        return uforegrad;
    }

    private Integer getUforegradFromPeriode(Beregningsperiode periode) {
        return periode == null || periode.getBeregning() == null ? null : periode.getBeregning().getUfg();
    }

    private Integer getUforegradFromBeregning(Beregning beregning) {
        return beregning == null ? null : beregning.getUfg();
    }

    private void populateGjeldendeUttakGammeltRegelverk(EndringAlderspensjonForm form, Vedtak aldersvedtak) {
        form.setGjeldendeUttaksgrad(FULLT_UTTAK);
        form.setGjeldendeUttakstidspunkt(aldersvedtak.getVirkFom());
        form.setStatusMelding(getMessage(GJELDENDE_ALDERSPENSJON_1943));
    }

    private void populateGjeldendeUttakNyttRegelverk(EndringAlderspensjonForm form, Sak alderssak) {
        Uttaksgrad latestUttakgrad = UttaksgradUtil.getLatestActiveUttaksgrad(alderssak.getUttaksgradhistorikk());

        if (latestUttakgrad == null) {
            return;
        }

        form.setGjeldendeUttaksgrad(latestUttakgrad.getUttaksgrad());
        form.setGjeldendeUttakstidspunkt(latestUttakgrad.getFomDato());
        form.setStatusMelding(getMessage(GJELDENDE_ALDERSPENSJON,
                new Object[]{form.getGjeldendeUttaksgrad(), DateUtil.format(form.getGjeldendeUttakstidspunkt())}));
    }

    /**
     * Populates form with YtelseFramITid with Uttaksgrad where innvilget AP not yet active.
     */
    private void populateYtelseFramITid(EndringAlderspensjonForm form, Sak alderssak) {
        Uttaksgrad uttaksgradFramITid = getInnvilgetApNotYetActive(alderssak.getUttaksgradhistorikk());

        if (uttaksgradFramITid == null) {
            return;
        }

        form.setFomFramITid(uttaksgradFramITid.getFomDato());
        // SIR 239066, rub2812 if there is no active AP, but is in the future, we set the date.
        if (form.getGjeldendeUttakstidspunkt() == null) {
            form.setGjeldendeUttakstidspunkt(uttaksgradFramITid.getFomDato());
        }

        form.setYtelseFramITid(getMessage(INNVILGET_AP_FREM_I_TID, new Object[]{
                uttaksgradFramITid.getUttaksgrad(), DateUtil.format(form.getFomFramITid())}));
    }

    private void loadDefaultEndreUttaksgradHjelpeMelding(EndringAlderspensjonForm form) {
        String uttaksgradMelding = getMessage(HJELPENDREUTTAKSGRAD);
        form.setEndreUttaksgradInfoTekst(uttaksgradMelding);
    }

    private void loadDefaultInkluderOpptjeningHjelpeMelding(EndringAlderspensjonForm form) {
        form.setInkluderOpptjeningInfoTekst(getMessage(HJELPINKLUDERENYOPPTJENING));
    }

    private void populateUttakstidspunkter(List<Date> uttakstidspunkter, EndringAlderspensjonForm endringAldersPensjonForm, Date aldersSakForsteVirk) {
        if (uttakstidspunkter == null) {
            return;
        }

        List<SelectItem> items = new ArrayList<>();
        SelectItem headerItem = new SelectItem();
        headerItem.setLabel(getMessage(VELG_DATO));
        headerItem.setValue("");
        items.add(headerItem);

        for (Date dato : uttakstidspunkter) {
            if (endringAldersPensjonForm.getSkjema() != null) {
                Date pensjonering = endringAldersPensjonForm.getSkjema().getPensjoneringstidspunkt();
                if (DateUtil.getYear(dato) == DateUtil.getYear(pensjonering)
                        && DateUtil.getMonth(dato) == DateUtil.getMonth(pensjonering)) {
                    endringAldersPensjonForm.setUttakstidspunkt(DateUtil.format(dato));
                }
            }

            if (addUttakstidspunktToListOfUttakstidspunkter(endringAldersPensjonForm, dato, aldersSakForsteVirk)) {
                SelectItem item = new SelectItem();
                item.setValue(DateUtil.format(dato));
                item.setLabel(DateUtil.format(dato));
                items.add(item);
            }
        }

        endringAldersPensjonForm.setUttakstidspunkter(items);
    }

    private boolean addUttakstidspunktToListOfUttakstidspunkter(EndringAlderspensjonForm endringAlderspensjonForm, Date dato, Date aldersSakForsteVirk) {
        boolean lopendeVedtakOfSaksTypUforGjenlevFamPl = endringAlderspensjonForm.getHasLopendeUforeGjenlevOrFamplAt67M();

        return !isDateAfterUserIs75(endringAlderspensjonForm, dato) && !isDateBeforeUserIs62(endringAlderspensjonForm, dato) &&
                !isDateTheMonthAfterBrukerIs67andBrukerHasLopendeUforOrGjenOrFamiliePleie(endringAlderspensjonForm, lopendeVedtakOfSaksTypUforGjenlevFamPl, dato) &&
                DateUtil.isAfterByDay(dato, aldersSakForsteVirk, true);
    }

    private boolean isDateAfterUserIs75(EndringAlderspensjonForm endringAldersPensjonForm, Date dateToCheck) {
        Date birthDate = endringAldersPensjonForm.getBruker().getPid().getFodselsdato();
        Date dateUserTurns75 = DateUtil.getRelativeDateByYear(birthDate, YEAR_75);
        return DateUtil.isAfterByDay(dateToCheck, dateUserTurns75, false);
    }

    private boolean isDateBeforeUserIs62(EndringAlderspensjonForm endringAldersPensjonForm, Date dateToCheck) {
        Date birthDate = endringAldersPensjonForm.getBruker().getPid().getFodselsdato();
        Date dateUserTurns62 = DateUtil.getRelativeDateByYear(birthDate, YEAR_62);
        return DateUtil.isBeforeByDay(dateToCheck, dateUserTurns62, false);
    }

    private boolean isDateTheMonthAfterBrukerIs67andBrukerHasLopendeUforOrGjenOrFamiliePleie(EndringAlderspensjonForm endringAldersPensjonForm, boolean lopendeVedtakOfSaksTypUforGjenlevFamPl, Date dateToCheck) {
        Date firstDayInMonthAfterBrukerTurns67 = calculateFirstDayInMonthAfterPersonTurns67(endringAldersPensjonForm.getBruker().getPid().getFodselsdato());
        return DateUtil.isAfterByDay(dateToCheck, firstDayInMonthAfterBrukerTurns67, false) && lopendeVedtakOfSaksTypUforGjenlevFamPl;
    }

    /**
     * Populates the uttaksgrad element in view. If the user has only one possible uttaksgrad, the uttaksgrad is shown in view
     * as a text. If the user has more than one possible uttaksgrad, it is shown in view as a dropdown.
     */
    private void populateUttaksgrad(List<Integer> uttaksgrader, EndringAlderspensjonForm endringAldersPensjonForm) {
        if (uttaksgrader == null) {
            return;
        }

        if (uttaksgrader.size() == 1) {
            endringAldersPensjonForm.setUttaksgrad(uttaksgrader.get(0).toString());
        } else {
            createDropdownListWithUttaksgrader(uttaksgrader, endringAldersPensjonForm);
        }
    }

    private void createDropdownListWithUttaksgrader(List<Integer> uttaksgrader,
                                                    EndringAlderspensjonForm endringAldersPensjonForm) {
        List<SelectItem> items = new ArrayList<>();
        SelectItem headerItem = new SelectItem();
        headerItem.setLabel(getMessage(VELG_UTTAKSGRAD));
        headerItem.setValue("");
        items.add(headerItem);

        for (Integer grad : uttaksgrader) {
            if (endringAldersPensjonForm.getSkjema() != null
                    && grad.equals(endringAldersPensjonForm.getSkjema().getPensjoneringsgrad())) {
                endringAldersPensjonForm.setUttaksgrad(grad.toString());
            }
            SelectItem item = new SelectItem();
            item.setValue(grad.toString());
            item.setLabel(grad.toString() + " %");
            items.add(item);
        }

        endringAldersPensjonForm.setUttaksgrader(items);
    }

    /**
     * Returns the list of uttakstidspunkter created from one month ahead in time
     */
    private List<Date> getUttakstidspunkter() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        List<Date> uttakstidspunkter = new ArrayList<Date>();
        int sperrefrist = Integer.parseInt(getMessage("SPERRE_VEDTAK_FREM_I_TID").trim());

        for (int index = 0; index < sperrefrist; index++) {
            uttakstidspunkter.add(calendar.getTime());
            calendar.add(Calendar.MONTH, 1);
        }

        return uttakstidspunkter;
    }

    private Boolean getVisInkluderNyOpptjening(List<Uttaksgrad> uttaksgradhistorikk) {
        Boolean show = true;
        // SIR219744
        if (uttaksgradhistorikk == null || uttaksgradhistorikk.isEmpty()) {
            return false;
        }

        Uttaksgrad uttaksgradWithLastFom = UttaksgradUtil.getLatestActiveUttaksgrad(uttaksgradhistorikk);

        if (uttaksgradWithLastFom == null) {
            return true;
        }

        return showOptionNyOpptjeningBasedOnUttaksgrad(uttaksgradWithLastFom)
                && isOneOfUttaksdatoerMoreThanXMonthsFromLastChangeUttaksgrad(uttaksgradWithLastFom);
    }

    /**
     * Check if one of user's possible date of uttak (the list user can choose from) is limits months or more from last change
     * of uttaksgrad.
     */
    private boolean isOneOfUttaksdatoerMoreThanXMonthsFromLastChangeUttaksgrad(Uttaksgrad uttaksgrad) {
        boolean isOneUttakstidspunktMoreThanLimitMonthsSinceChange = false;
        int sperreEndringUttaksgrad = PselvUtil.fetchSperreEndringAvUttaksgrad();

        for (Date uttakstidspunkt : getUttakstidspunkter()) {
            Date fomDateUttaksgradOneYearAhead = DateUtils.addMonths(uttaksgrad.getFomDato(), sperreEndringUttaksgrad);

            if (fomDateUttaksgradOneYearAhead.before(uttakstidspunkt)) {
                isOneUttakstidspunktMoreThanLimitMonthsSinceChange = true;
            }
        }

        return isOneUttakstidspunktMoreThanLimitMonthsSinceChange;
    }

    /**
     * The choice 'inkluder ny opptjening' is not shown if user has an alderspensjon with uttaksgrad 0 or 100 percent.
     */
    private boolean showOptionNyOpptjeningBasedOnUttaksgrad(Uttaksgrad uttaksgrad) {
        Integer grad = uttaksgrad.getUttaksgrad();
        return grad != null && !(grad == 0 || grad == 100);
    }

    private Integer getPensjonMax(Integer uforegrad) {
        Integer pensjonMax = 100;
        return uforegrad == null ? pensjonMax : Integer.valueOf(pensjonMax - uforegrad);
    }

    private void loadUforeGradMelding(EndringAlderspensjonForm form, List<Vedtak> allevedtak) {
        Vedtak uforevedtak = getUforevedtak(allevedtak);
        Integer uforegrad = getUforegrad(uforevedtak);

        if (uforegrad == null) {
            return;
        }

        form.setEndreUttaksgradInfoTekst(getMessage(HJELPAPOGUP));
        form.setMelding(getMessage(INFO_TEKST_UP, new Object[]{uforegrad}));
    }

    private List<Integer> getUttaksgrader(EndringAlderspensjonForm form, Integer uforegrad,
                                          List<Uttaksgrad> uttaksgradhistorikk, Vedtak aldersvedtak) {
        List<Integer> uttaksgrader = getAlleUttaksgrader();

        if (!isGammeltRegelverk(aldersvedtak)) {
            uttaksgrader = filtrerUttakForUttakstidspunktMindreEnn12Maaneder(form, uttaksgrader, uttaksgradhistorikk);
        }

        uttaksgrader = filtrerUttakFor43MedFullPensjon(form, uttaksgrader);

        if (!canChooseAgeOver67And1Month(form)) {
            uttaksgrader = filtrerUttakForUforePensjon(uttaksgrader, uforegrad);
        }

        // 1943-kullet kan ha hatt AP i 2010 og vil da ha en tom uttaksgradshistorikk.
        // Det gir ikke mening � filtrere mot den.
        if (!uttaksgradhistorikk.isEmpty()) {
            uttaksgrader = filtrerUttakForGjeldendeUttak(uttaksgradhistorikk, uttaksgrader);
        }

        return uttaksgrader;
    }

    private boolean canChooseAgeOver67And1Month(EndringAlderspensjonForm form) {
        int sperrrefrist = Integer.parseInt(getMessage("SPERRE_VEDTAK_FREM_I_TID").trim());
        Date fodselsDato = form.getBruker().getPid().getFodselsdato();
        Date dateWhenUserIs67And1Month = DateUtils.addYears(fodselsDato, 67);
        dateWhenUserIs67And1Month = DateUtils.addMonths(dateWhenUserIs67And1Month, 1);
        Date latestPossibleDate = DateUtils.addMonths(new Date(), sperrrefrist);
        return latestPossibleDate.after(dateWhenUserIs67And1Month);
    }

    /**
     * Check if user is born in 1943 and if the current uttaksgrad is null or 100. If so, remove 100 from the uttaksgrad list.
     */
    private List<Integer> filtrerUttakFor43MedFullPensjon(EndringAlderspensjonForm form, List<Integer> uttaksgrader) {
        Date fDate = form.getBruker().getPid().getFodselsdato();

        if (DateUtil.getYear(fDate) == YEAR_1943 && (form.getGjeldendeUttaksgrad() == null || form.getGjeldendeUttaksgrad().equals(FULLT_UTTAK))) {
            uttaksgrader.remove(FULLT_UTTAK);
        }

        return uttaksgrader;
    }

    private List<Integer> filtrerUttakForGjeldendeUttak(List<Uttaksgrad> uttaksgradHistorikk, List<Integer> uttaksgrader) {
        List<Integer> filtrerteUttaksgrader = new ArrayList<>();
        Uttaksgrad latestUttaksgrad = UttaksgradUtil.getLatestActiveUttaksgrad(uttaksgradHistorikk);

        for (Integer uttaksgrad : uttaksgrader) {
            if (latestUttaksgrad == null || !uttaksgrad.equals(latestUttaksgrad.getUttaksgrad())) {
                filtrerteUttaksgrader.add(uttaksgrad);
            }
        }

        return filtrerteUttaksgrader;
    }

    /**
     * Filters list of uttak so that if all uttakstidspunkter is less then 12 months after last uttak only 0% and 100% uttak is
     * returned
     */
    private List<Integer> filtrerUttakForUttakstidspunktMindreEnn12Maaneder(EndringAlderspensjonForm form,
                                                                            List<Integer> uttaksgrader, List<Uttaksgrad> uttaksgradhistorikk) {
        Uttaksgrad uttaksgradWithLastFom = UttaksgradUtil.getLatestActiveUttaksgrad(uttaksgradhistorikk);

        if (uttaksgradWithLastFom == null) {
            return uttaksgrader;
        }

        for (Date uttakstidspunkt : getUttakstidspunkter()) {
            int sperreEndringAvUttaksgrad = PselvUtil.fetchSperreEndringAvUttaksgrad();
            if (uttakstidspunkt.after(DateUtils.addMonths(uttaksgradWithLastFom.getFomDato(), sperreEndringAvUttaksgrad))) {
                return uttaksgrader;
            }
        }

        form.setInkluderOpptjeningInfoTekst(getMessage(HJELPKUN0OG100));
        List<Integer> filtrerteUttaksgrader = new ArrayList<>();
        filtrerteUttaksgrader.add(0);
        filtrerteUttaksgrader.add(100);
        return filtrerteUttaksgrader;
    }

    /**
     * Checks if user has a innvilget vedtak which is not yet active (fomDato is still in the future) and retrieves this if
     * exists.
     */
    private Uttaksgrad getInnvilgetApNotYetActive(List<Uttaksgrad> uttaksgradhistorikk) {
        Uttaksgrad innvilgetApNotYetActive = null;

        for (Uttaksgrad uttaksgrad : uttaksgradhistorikk) {
            if (DateUtil.isAfterByDay(uttaksgrad.getFomDato(), Calendar.getInstance().getTime(), false)) {
                innvilgetApNotYetActive = uttaksgrad;
            }
        }

        return innvilgetApNotYetActive;
    }

    private Beregningsperiode getSisteBeregningsperiode(Vedtak vedtak) {
        Beregningsperiode sisteBeregningsperiode = null;

        if (vedtak == null) {
            return sisteBeregningsperiode;
        }

        for (Beregningsperiode periode : vedtak.getBeregningsperiodeListe()) {
            if (sisteBeregningsperiode == null || sisteBeregningsperiode.getFomDato().before(periode.getFomDato())) {
                sisteBeregningsperiode = periode;
            }
        }

        return sisteBeregningsperiode;
    }

    /**
     * Filters list of uttaksgrad so that it does not contain a value higher than max uttaksgrad. Max uttaksgrad may be less
     * than 100% if uforegrad also exist
     */
    private List<Integer> filtrerUttakForUforePensjon(List<Integer> uttaksgrader, Integer uforegrad) {
        List<Integer> lovligeUttaksgrader = new ArrayList<>();

        for (Integer uttaksgrad : uttaksgrader) {
            if (uttaksgrad <= getPensjonMax(uforegrad)) {
                lovligeUttaksgrader.add(uttaksgrad);
            }
        }

        sorterIntegerListe(lovligeUttaksgrader);
        return lovligeUttaksgrader;
    }

    private void sorterIntegerListe(List<Integer> uttaksgrader) {
        uttaksgrader.sort(new UttaksgradComparator());
    }

    private List<Integer> getAlleUttaksgrader() {
        List<Integer> uttaksgrader = new ArrayList<>();
        uttaksgrader.add(0);
        uttaksgrader.add(20);
        uttaksgrader.add(40);
        uttaksgrader.add(50);
        uttaksgrader.add(60);
        uttaksgrader.add(80);
        uttaksgrader.add(100);
        return uttaksgrader;
    }

    private Date findForsteUttakstidspunktAfter12MonthsOfGivenData(Date date) {
        Date dateToCheck = DateUtils.addMonths(date, PselvUtil.fetchSperreEndringAvUttaksgrad());

        for (Date uttakstidspunkt : getUttakstidspunkter()) {
            if (uttakstidspunkt.after(dateToCheck)) {
                return uttakstidspunkt;
            }
        }

        return null;
    }

    private boolean isGammeltRegelverk(Vedtak vedtak) {
        boolean isGammeltRegelverk = false;
        RegelverkTypeCode regelverkType = vedtak.getKravhode().getRegelverkType();

        if (regelverkType == null || RegelverkTypeCode.G_REG.equals(regelverkType)) {
            isGammeltRegelverk = true;
        }

        return isGammeltRegelverk;
    }

    static final class UttaksgradComparator implements Comparator<Integer>, Serializable {

        private static final long serialVersionUID = 1L;

        @Override
        public int compare(Integer integer1, Integer integer2) {
            return integer1.compareTo(integer2);
        }
    }

    private Date calculateFirstDayInMonthAfterPersonTurns67(Date dateOfBirth) {
        Date dateOf67m = DateUtil.getRelativeDateByYear(dateOfBirth, 67);
        dateOf67m = DateUtil.getRelativeDateByMonth(dateOf67m, 1);
        dateOf67m = DateUtil.getFirstDayOfMonth(dateOf67m);
        return dateOf67m;
    }
}

package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.webflow.execution.RequestContextHolder;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.codestable.CodesTablePeriodic;
import no.stelvio.common.util.DateUtil;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.UttaksgradCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.UttaksgradCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Uttaksgrad;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaInnledning;
import no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants;
import no.nav.presentation.pensjon.pselv.common.PselvConstants;
import no.nav.presentation.pensjon.pselv.common.PselvUtil;
import no.nav.presentation.pensjon.pselv.common.utils.AdresseUtil;
import no.nav.presentation.pensjon.pselv.common.utils.PersonUtil;
import no.nav.presentation.pensjon.pselv.skjema.SkjemaCommonConstants;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning.support.SkjemaInnledningConstants;

public class SkjemaInnledningFormPopulator {

    private static final String URL_ENCODING = "UTF-8";
    private static final String PERSONOPPLYSNINGER_URL_KEY = "cfg.pselv.personopplysninger.url";
    private static final String IS_SELVBETJENINGSSONE_KEY = "cfg.pselv.security.isSelvbetjeningsSone";
    private CodesTableManager codesTableManager;
    private MessageSource pselvConfigSource;
    private MessageSource messageSource;

    private MessageSource pselvPropertyMessageSource;

    public void setErrorMessageUserCantApply(SkjemaInnledningForm form) {
        String errorMessageUserCantApply = null;

        if (form.isAldersPensjonSoknad()) {
            errorMessageUserCantApply = getErrorMessageUserCannotAppyAlderspensjon(form);
        } else if (form.isForsorgningstilleggSoknad()) {
            errorMessageUserCantApply = getMessageUserCannotApplyForsorgingstillegg(form);
        }
        // This message is only shown if and only if no other message is shown
        if (errorMessageUserCantApply == null && !new AdresseUtil().hasRegisteredAdresse(form.getBruker())) {
            // user can only send in an application (alderspensjon or forsorgertillegg ) if he/she is registrated with an adress
            errorMessageUserCantApply = getMessageWhenAdresseMangler();
        }

        form.setErrorMessageUserCantApply(errorMessageUserCantApply);
    }

    public void populateForm(SkjemaInnledningForm form, SkjemaDataForPreComplementing skjemaData) {
        form.setSkjemaId(form.getSkjema().getSkjemaId());
        List<SelectItem> pensjoneringsgradDropDownList = createPensjoneringsgradDropdown(form);
        form.setPensjoneringsgradListe(pensjoneringsgradDropDownList);

        if (skjemaData == null) {
            populateFormWithInformationFromSkjema(form);
        } else {
            populateFormWithInformationFromPensjonskalkulator(form, skjemaData);
            form.setSkjemaData(skjemaData);
        }

        setInfoMessageUserHasYtelse(form);
        populateInnledendeText(form);
    }

    /**
     * Populates form with information from pensjonskalkulator. When a user simulates alderspensjon he/she can choose to send in
     * the information in an application.
     */
    private void populateFormWithInformationFromPensjonskalkulator(SkjemaInnledningForm form,
                                                                   SkjemaDataForPreComplementing skjemaData) {
        setPensjoneringstidspunkt(form, skjemaData.getPensjoneringstidspunkt());
        form.setSvarAFP(skjemaData.getAfpRett());

        if (skjemaData.getPensjoneringsgrad() == null) {
            return;
        }

        String valgtPensjoneringsGrad = addPercentCharToString(String.valueOf(skjemaData.getPensjoneringsgrad()));
        form.setValgtPensjoneringsGrad(valgtPensjoneringsGrad);
    }

    private void populateFormWithInformationFromSkjema(SkjemaInnledningForm form) {
        SkjemaInnledning skjemaInnledning = form.getSkjema().getSkjemaInnledning();

        // Forsorgingstillegg barn og EPS is only updated if the application is of type Forsorgertillegg
        if (form.isForsorgningstilleggSoknad()) {
            form.setForsorgningstilleggBarn(skjemaInnledning.getForsorgingstilleggBarn());
            form.setForsorgningstilleggEPS(skjemaInnledning.getForsorgingstilleggEPS());
        }
        if (skjemaInnledning.getUttaksgrad() != null) {
            String grad = skjemaInnledning.getUttaksgrad().toString();
            form.setValgtPensjoneringsGrad(addPercentCharToString(grad));
        }

        form.setSvarAFP(skjemaInnledning.getSoktAfpPrivat());
        setPensjoneringstidspunkt(form, skjemaInnledning.getIverksettelsesdato());
    }

    /**
     * Populate shown innledende text. This text is dependent on application type (Alderspensjon or Forsorgertillegg) and when
     * user is born.
     */
    private void populateInnledendeText(SkjemaInnledningForm form) {
        String innledendeText = null;

        if (form.isAldersPensjonSoknad()) {
            innledendeText = findInnledendeTextAlderspensjonSoknad(form);
        } else if (form.isForsorgningstilleggSoknad()) {
            String sperrefrist = Integer.toString(PselvUtil.fetchSperreVedtakFremITid());
            innledendeText = getMsg(SkjemaInnledningConstants.INNLEDNING_TEXT_FORSORGERTILLEGG, new String[]{sperrefrist});
        }

        form.setInnledendeText(innledendeText);
    }

    private String findInnledendeTextAlderspensjonSoknad(SkjemaInnledningForm form) {
        String sperrefrist = Integer.toString(PselvUtil.fetchSperreVedtakFremITid());

        if (form.isBornBefore1943()) {
            return getMsg(SkjemaInnledningConstants.INNLEDNING_TEXT_ALDERSPENSJON_BORN_BEFORE_1943, new String[]{sperrefrist});
        }

        if (form.isBornBetweenJanNov1943()) {
            return getMsg(SkjemaInnledningConstants.INNLEDNING_TEXT_ALDERSPENSJON_BORN_BETWEEN_JAN_NOV_1943, new String[]{sperrefrist});
        }

        if (form.isBornFomDec1943()) {
            return getMsg(SkjemaInnledningConstants.INNLEDENING_TEXT_ALDERSPENSJON_BORN_DEC_1943_1948, new String[]{sperrefrist});
        }

        return null;
    }

    /**
     * Checks if user can send in an application Alderspensjon or not. If not the form is populated with a message. Note the
     * sequens of the if-statements is important.
     */
    private String getErrorMessageUserCannotAppyAlderspensjon(SkjemaInnledningForm form) {
        if (form.isReceivingAP()) {
            return getMsg(SkjemaInnledningConstants.IKKE_SOKE_PGA_LIKT_KRAV_MSG);
        }
        if (form.isSoknadInne()) {
            return getMsg(SkjemaInnledningConstants.IKKE_SOKE_PGA_SOKNAD_INNE_MSG);
        }
        if (isForTidligSokAlderspensjon(form)) {
            String sperrefrist = Integer.toString(PselvUtil.fetchSperreVedtakFremITid());
            return getMsg(SkjemaInnledningConstants.FOR_TIDLIG_SOK_ALDER_MSG, new String[]{sperrefrist});
        }
        if (isUserTooOldDagensRegelverk(form)) {
            return getMsgWithLink(SkjemaInnledningConstants.FOR_GAMMEL_70_MSG, SkjemaCommonConstants.PUS012_SKJEMAOVERSIKT_FLOW);
        }
        if (isUserTooOldApplyFleksibelRegelverk(form)) {
            return getMsgWithLink(SkjemaInnledningConstants.FOR_GAMMEL_75_MSG, SkjemaCommonConstants.PUS012_SKJEMAOVERSIKT_FLOW);
        }
        if (form.isUforepensjonTilBehandling()) {
            return getMsg(SkjemaInnledningConstants.IKKE_SOKE_PGA_SOKNAD_UFORE_INNE_MSG);
        }
        if (form.isIkkeSokePgaUforeOver80(form.getBruker().getPid())) {
            return getMsg(SkjemaInnledningConstants.IKKE_SOKE_PGA_UFORE_OVER_80_MSG);
        }
        if (form.isUserHasUforeVedtakFremITid()) {
            return getMsg(SkjemaInnledningConstants.IKKE_SOKE_PGA_UFORE_FREM_I_TID_MSG);
        }
        if (form.isUserHasManglendeEpsInformasjon()) {
            return getMsgWithLink(SkjemaInnledningConstants.IKKE_SOKE_PGA_MANGLENDE_EPS_OPPLYSNINGER, SkjemaCommonConstants.PUS012_SKJEMAOVERSIKT_FLOW);
        }
        return null;
    }

    private String getMessageUserCannotApplyForsorgingstillegg(SkjemaInnledningForm form) {
        if (!form.userHasAlreadyAppliedAlderspensjon()) {
            return getMsg(SkjemaInnledningConstants.IKKE_HOVEDYTELSE_MSG);
        }
        if (form.isUserHasAlderspensjonNyttRegelverk() && isNotAllowedToSokeOmForsorgerTilleggNyttRegelverk(form)) {
            return getMsg(SkjemaInnledningConstants.IKKE_FORSORGINGSTILLEGG_MSG);
        }
        return null;
    }

    /**
     * A user can not apply for alderspensjon when it's more than 4 months to he is 62, and the user is not born 1943 - 1948.
     * (All people born in this time period can apply for fleksibel alderspensjon from 01.01.2011 in project phase Bølgen).
     */
    private boolean isForTidligSokAlderspensjon(SkjemaInnledningForm form) {
        Date fodselsdato = form.getBruker().getPid().getFodselsdato();
        return form.isBornFomDec1943() && isUserTooYoung(fodselsdato);
    }

    /**
     * If a user is born before 1943 (dagens regelverk), a user can only apply for alderspensjon before the age of 70.
     */
    private boolean isUserTooOldDagensRegelverk(SkjemaInnledningForm form) {
        Date fodselsdato = form.getBruker().getPid().getFodselsdato();
        return form.isBornBefore1943() && userTooOld70(fodselsdato);
    }

    /**
     * User born from 1943 - 1958 (nytt regelverk) can apply for alderspensjon until they are 75 years old.
     */
    private boolean isUserTooOldApplyFleksibelRegelverk(SkjemaInnledningForm form) {
        Date fodselsdato = form.getBruker().getPid().getFodselsdato();
        return (form.isBornBetweenJanNov1943() || form.isBornFomDec1943()) && isUser75(fodselsdato);
    }

    private void setInfoMessageUserHasYtelse(SkjemaInnledningForm form) {
        String infoMessageUserHasYtelse = getInfoMessage(form);

        if (infoMessageUserHasYtelse == null) {
            return;
        }

        form.setInfoUserHasOtherYtelse(getMsgWithLink(infoMessageUserHasYtelse, SkjemaInnledningConstants.TRS003_NYHENVENDELSE_FLOW));
    }

    private static String getInfoMessage(SkjemaInnledningForm form) {
        if (form.isLopendeAFP()) {
            return SkjemaInnledningConstants.INFOTEKST_AFP_MSG;
        }
        if (form.isLopendeGjenlevende()) {
            return SkjemaInnledningConstants.INFOTEKST_GJP_MSG;
        }
        if (form.isLopendeUforepensjon()) {
            return SkjemaInnledningConstants.INFOTEKST_UP_MSG;
        }
        return null;
    }

    private void setPensjoneringstidspunkt(SkjemaInnledningForm form, Date iverksettelsesdato) {
        populatePensjoneringstidspunktAlderspensjon(form);
        List<SelectItem> datoer = form.getPensjoneringsArListe();

        // If user has a skjemainnledning already, check that the chosen date is still valid
        if (iverksettelsesdato != null && !datoer.isEmpty()) {
            Date pensjoneringstidspunkt = findPensjoneringstidspunkt(datoer, iverksettelsesdato);
            SimpleDateFormat dateFormat = new SimpleDateFormat(SkjemaInnledningConstants.DATE_FORMAT);
            form.setValgtPensjoneringstidspunkt(dateFormat.format(pensjoneringstidspunkt));

            if (DateUtil.isAfterByDay(iverksettelsesdato, PselvConstants.DATO_NYTT_REGELVERK, true) && (form.isBornBetweenJanNov1943() || form.isBornFomDec1943())) {
                form.setVisPensjoneringsGradBoks(true);
            }
        }

        // Populates the forsorgertilleggTidspunkt
        if (form.isForsorgningstilleggSoknad() && form.userHasAlreadyAppliedAlderspensjon()) {
            populatePensjoneringstidspunktForsorgingstillegg(form);
        }
    }

    /**
     * Method that finds the correct pensjoneringstidspunkt.
     * <p>
     * If user comes from pensjonskalkulator and iverksettelsesdato = ønsket pensjoneringstidspunkt in the pensjonskalkulator, it is possible the iverksettelsesdato is several
     * years in the future. In that case, the application should get the first possible date as default.
     * <p>
     * If the user has started an application, saved the application, but left the page and returned later the chosen pensjoneringstidspunkt should be saved and used when user
     * returns to finish.
     * <p>
     * If the user enters the application without doing anything special before, the default should be the first available date for pensjoneringstidspunkt.
     */
    private Date findPensjoneringstidspunkt(List<SelectItem> datoer, Date iverksettelsesdato) {
        Date firstDateInList = getDate((String) datoer.get(0).getValue());
        Date lastDateInList = getDate((String) datoer.get(datoer.size() - 1).getValue());

        if (iverksettelsesdato.after(lastDateInList)) {
            return firstDateInList;
        }

        return firstDateInList.after(iverksettelsesdato) ? firstDateInList : iverksettelsesdato;
    }

    private Date getDate(String dateString) {
        try {
            return new SimpleDateFormat(SkjemaInnledningConstants.DATE_FORMAT).parse(dateString);
        } catch (ParseException e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    private void populatePensjoneringstidspunktAlderspensjon(SkjemaInnledningForm form) {
        if (form.isBornBefore1943()) {
            populatePensjoneringstidspunktAlderspensjonUserBornBefore1943(form);
        } else if (form.isBornBetweenJanNov1943()) {
            populatePensjoneringstidspunktAlderspensjonUserBornBetweenJanNov1943(form);
        } else if (form.isBornFomDec1943()) {
            populatePensjoneringstidspunktAlderspensjonFrom1943(form);
        }

        if (form.getPensjoneringsArListe().size() == 1) {
            form.setValgtPensjoneringstidspunkt((String) form.getPensjoneringsArListe().get(0).getValue());
        }
    }

    /**
     * Populates pensjoneringstidspunkt when soknad is Forsorgingstillegg. The first time of retirment is the first in the next
     * month.
     */
    private void populatePensjoneringstidspunktForsorgingstillegg(SkjemaInnledningForm form) {
        // Set first tidspunkt in list
        Calendar pafolgendeMnd = getFirstDayOfNextMonth();
        int sperrefrist = fetchSperrefrist();
        List<SelectItem> aarList = populatePensjonsTidspkt(sperrefrist, pafolgendeMnd);
        form.setPensjoneringsArListe(aarList);
    }

    private void populatePensjoneringstidspunktAlderspensjonUserBornBefore1943(SkjemaInnledningForm form) {
        // populate list with 4 months
        int sperrefrist = fetchSperrefrist();
        List<SelectItem> aarList = populatePensjonsTidspkt(sperrefrist, getFirstDayOfNextMonth());
        form.setPensjoneringsArListe(aarList);
        form.setFylt67Aar(true);
    }

    private void populatePensjoneringstidspunktAlderspensjonUserBornBetweenJanNov1943(SkjemaInnledningForm form) {
        Date fdato = form.getBruker().getPid().getFodselsdato();
        Calendar firstLegalDate = getFirstLegalAPDate(fdato);
        Calendar nextMnth = getFirstDayOfNextMonth();
        List<SelectItem> aarList;

        if (userIs67(fdato)) {
            int sperrefrist = fetchSperrefrist();
            aarList = populatePensjonsTidspkt(sperrefrist, nextMnth);
            form.setFylt67Aar(true);
        } else {
            aarList = populatePensjonsTidspkt(getMonthsBetweenDates(nextMnth, firstLegalDate), firstLegalDate);
        }

        form.setPensjoneringsArListe(aarList);
        Calendar firstInL = Calendar.getInstance();
        firstInL.setTime(getDate((String) aarList.get(0).getValue()));

        if (DateUtil.isAfterByDay(firstInL.getTime(), PselvConstants.DATO_NYTT_REGELVERK, true)) {
            form.setVisPensjoneringsGradBoks(true);
        }
    }

    /**
     * Populates time of retirement when user applies for alderspensjon and is born between december 1943 and 1948.
     */
    private void populatePensjoneringstidspunktAlderspensjonFrom1943(SkjemaInnledningForm form) {
        Date fdato = form.getBruker().getPid().getFodselsdato();
        Calendar firstLegalDate = getFirstLegalAPNyttRegelverkDate(fdato);
        Calendar nextMnth = getFirstDayOfNextMonth();
        Calendar today = Calendar.getInstance();
        Calendar firstDateNewRules = Calendar.getInstance();
        firstDateNewRules.setTime(PselvConstants.DATO_NYTT_REGELVERK);
        final int sperrefrist = fetchSperrefrist();
        List<SelectItem> aarList;

        if (!userIs62(fdato)) {
            aarList = populatePensjonsTidspkt(getMonthsBetweenDates(nextMnth, firstLegalDate), firstLegalDate);
        } else if (today.getTime().before(firstDateNewRules.getTime())) {
            aarList = populatePensjonsTidspkt(getMonthsBetweenDates(nextMnth, firstDateNewRules), firstDateNewRules);
        } else {
            int monthsUntil67M = DateUtil.getMonthBetween(today.getTime(), calculateFirstDayInMonthAfterPersonTurns67(fdato));
            if (form.getHasLopendeUforeGjenlevOrFamplAt67M() && monthsUntil67M <= sperrefrist) {
                aarList = populatePensjonsTidspkt(monthsUntil67M, nextMnth);
            } else {
                aarList = populatePensjonsTidspkt(sperrefrist, nextMnth);
            }
        }

        form.setPensjoneringsArListe(aarList);
        form.setVisPensjoneringsGradBoks(true); // bare nytt regelverk
    }

    private Date calculateFirstDayInMonthAfterPersonTurns67(Date dateOfBirth) {
        Date dateOf67m = DateUtil.getRelativeDateByYear(dateOfBirth, 67);
        dateOf67m = DateUtil.getRelativeDateByMonth(dateOf67m, 1);
        dateOf67m = DateUtil.getFirstDayOfMonth(dateOf67m);
        return dateOf67m;
    }

    private int getMonthsBetweenDates(Calendar one, Calendar two) {
        int monthsBeforeBirthdayAP = DateUtil.getMonthBetween(one.getTime(), two.getTime());
        int sperrefrist = fetchSperrefrist();
        return sperrefrist - monthsBeforeBirthdayAP;
    }

    /**
     * Find the first legal date for applying for Alderspensjon. This date is the first day in the month after the user turns
     * 67.
     */
    private Calendar getFirstLegalAPDate(Date fdato) {
        Date firstLegalDate = DateUtil.getRelativeDateByYear(fdato, PensjonsKalkulatorConstants.AP_PENSJONSALDER_START);
        firstLegalDate = DateUtil.getFirstDayOfMonth(firstLegalDate);
        firstLegalDate = DateUtil.getRelativeDateByMonth(firstLegalDate, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.setTimeToZero(firstLegalDate));
        return calendar;
    }

    /**
     * Find the first legal date for applying for Alderspensjon. This date is whatever comes last of the first day in the month after the user turns
     * 62, and the date for nytt regelverk (01.01.2011).
     */
    private Calendar getFirstLegalAPNyttRegelverkDate(Date fdato) {
        Date firstLegalDate = DateUtil.getRelativeDateByYear(fdato, PensjonsKalkulatorConstants.AP_PENSJONSALDER_START_NY);
        firstLegalDate = DateUtil.getFirstDayOfMonth(firstLegalDate);
        firstLegalDate = DateUtil.getRelativeDateByMonth(firstLegalDate, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.setTimeToZero(firstLegalDate));

        if (DateUtil.isBeforeByDay(calendar.getTime(), PselvConstants.DATO_NYTT_REGELVERK, false)) {
            calendar.setTime(DateUtil.setTimeToZero(PselvConstants.DATO_NYTT_REGELVERK));
        }

        return calendar;
    }

    private Calendar getFirstDayOfNextMonth() {
        Date date = DateUtil.getFirstDayOfMonth(now());
        date = DateUtil.getRelativeDateByMonth(date, 1);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(DateUtil.setTimeToZero(date));
        return calendar;
    }

    protected Date now() {
        return new Date();
    }

    private List<SelectItem> populatePensjonsTidspkt(int antMnd, Calendar dato) {
        SimpleDateFormat sdf = new SimpleDateFormat(SkjemaInnledningConstants.DATE_FORMAT);
        List<SelectItem> aarList = new ArrayList<>();

        for (int index = 1; index <= antMnd; index++) {
            String aar = sdf.format(dato.getTime());
            SelectItem item = new SelectItem(aar);
            aarList.add(item);
            dato.add(Calendar.MONTH, 1);
        }

        return aarList;
    }

    private boolean isUserTooYoung(Date fdato) {
        Calendar foersteMuligeVirkFOM = getFoersteMuligeVirkFOM(fdato);
        int sperrefrist = fetchSperrefrist();
        Date fourMonthsFromToday = DateUtil.getRelativeDateByMonth(new Date(), sperrefrist);
        return DateUtil.isBeforeByDay(fourMonthsFromToday, foersteMuligeVirkFOM.getTime(), false);
    }

    private Calendar getFoersteMuligeVirkFOM(Date fdato) {
        Calendar foersteMuligeVirk = Calendar.getInstance();
        foersteMuligeVirk.setTime(fdato);
        foersteMuligeVirk.add(Calendar.YEAR, PensjonsKalkulatorConstants.AP_PENSJONSALDER_START_NY);
        foersteMuligeVirk.add(Calendar.MONTH, 1);
        foersteMuligeVirk.set(Calendar.DATE, 1);
        return foersteMuligeVirk;
    }

    private boolean userIs67(Date fdato) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fdato);
        calendar.add(Calendar.YEAR, PensjonsKalkulatorConstants.AP_PENSJONSALDER_START);
        calendar.set(Calendar.DATE, 1);
        calendar.getTime();
        Calendar now = Calendar.getInstance();
        return now.after(calendar);
    }

    private boolean userIs62(Date fdato) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fdato);
        calendar.add(Calendar.YEAR, PensjonsKalkulatorConstants.AFP_PENSJONSALDER_START);
        calendar.set(Calendar.DATE, 1);
        calendar.getTime();
        Calendar now = Calendar.getInstance();
        return now.after(calendar);
    }

    public boolean isUserBetween62And67(Person person) {
        PersonUtil personUtil = new PersonUtil();
        Calendar now = Calendar.getInstance();
        int age = personUtil.calculateAge(person.getPid().getFodselsdato(), now.getTime());

        return age >= PensjonsKalkulatorConstants.AFP_PENSJONSALDER_START
                && age <= PensjonsKalkulatorConstants.AFP_PENSJONSALDER_SLUTT;
    }

    private boolean userTooOld70(Date fdato) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fdato);
        calendar.add(Calendar.YEAR, PensjonsKalkulatorConstants.AP_PENSJONSALDER_SLUTT);
        calendar.set(Calendar.DATE, 1);
        Calendar now = Calendar.getInstance();
        return now.after(calendar);
    }

    private boolean isUser75(Date fdato) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fdato);
        calendar.add(Calendar.YEAR, PensjonsKalkulatorConstants.PENSJONSALDER_MAX);
        calendar.set(Calendar.DATE, 1);
        calendar.getTime();
        Calendar now = Calendar.getInstance();
        return now.after(calendar);
    }

    private List<SelectItem> createPensjoneringsgradDropdown(SkjemaInnledningForm form) {
        SelectItem item;
        CodesTablePeriodic<UttaksgradCti, UttaksgradCode, String> ctp = codesTableManager
                .getCodesTablePeriodic(UttaksgradCti.class);
        List<SelectItem> uttaksgradList = new ArrayList<>();

        if (form.getUforeGrad() != null && form.getUforeGrad() <= 80 && !form.canChooseAgeOver67And1Month(form.getBruker().getPid())) {
            for (UttaksgradCti cti : ctp.getCodesTableItems()) {
                Integer code = Integer.valueOf(cti.getCodeAsString());
                if (!cti.getDecode().equalsIgnoreCase("0 %") && !(code + form.getUforeGrad() > 100)) {
                    item = new SelectItem(cti.getDecode(), cti.getDecode());
                    uttaksgradList.add(item);
                }
            }
        } else {
            for (UttaksgradCti cti : ctp.getCodesTableItems()) {
                if (!cti.getDecode().equalsIgnoreCase("0 %")) {
                    item = new SelectItem(cti.getDecode(), cti.getDecode());
                    uttaksgradList.add(item);
                }
            }
        }

        return uttaksgradList;
    }

    /**
     * Alderspensjon nytt regelverk: User is only allowed to apply for forsorgingstillegg if he/she has a lopende alderspensjon
     * sak where uttaksgrad is 100% and user is 67 years old or older at time of retirment. Time of retirment for a
     * forsorgertillegg sak is the first in next month.
     */
    private boolean isNotAllowedToSokeOmForsorgerTilleggNyttRegelverk(SkjemaInnledningForm form) {
        boolean notAllowedToSokeForsorgerTillegg = true;

        if (!form.isReceivingAP()) {
            return notAllowedToSokeForsorgerTillegg;
        }

        Date iverksettelsesDate = getFirstDayOfNextMonth().getTime();
        Integer uttaksgrad = form.getUttaksgradExistingAlderpsensjonSak();
        Date fdato = form.getBruker().getPid().getFodselsdato();
        Date userIs67 = getFirstLegalAPDate(fdato).getTime();
        notAllowedToSokeForsorgerTillegg = !(uttaksgrad != null && uttaksgrad == 100 && (iverksettelsesDate.equals(userIs67) || iverksettelsesDate.after(userIs67)));
        return notAllowedToSokeForsorgerTillegg;
    }

    /**
     * Populates the given form with uttaksgrad of the given krav. Krav has a list of uttaksgrad, and the uttaksgrad valid at
     * the time of first day of next month.
     */
    public void populateGjeldendeUttaksgradFromKrav(SkjemaInnledningForm form, KravHode krav) {
        Integer gjeldendeUttaksgrad = null;
        Date date = getFirstDayOfNextMonth().getTime();

        for (Uttaksgrad uttaksgrad : krav.getUttaksgradListe()) {
            if (uttaksgrad.getFomDato().before(date)
                    && (uttaksgrad.getTomDato() == null || uttaksgrad.getTomDato().after(date))) {
                gjeldendeUttaksgrad = uttaksgrad.getUttaksgrad();
            }
        }

        form.setUttaksgradExistingAlderpsensjonSak(gjeldendeUttaksgrad);
    }

    public String setTypedKeyInformation(SkjemaInnledningForm form) {
        StringBuilder typedKeyInformation = new StringBuilder();
        String tidspunkt = form.getValgtPensjoneringstidspunkt();
        String grad = form.getValgtPensjoneringsGrad();
        String januar2011 = getMsg(SkjemaInnledningForm.JANUARY_2011);

        if (form.isAldersPensjonSoknad()) {
            boolean tidspunktIsJan2011 = tidspunkt != null && tidspunkt.equals(januar2011);
            if (form.isBornBefore1943() || (form.isBornBetweenJanNov1943() && !tidspunktIsJan2011)) {
                typedKeyInformation.append(getMsg(SkjemaInnledningConstants.PENSJON_DAGENS_REGELVERK,
                        new String[]{tidspunkt}));
            }

            if (form.isBornFomDec1943() || (form.isBornBetweenJanNov1943() && tidspunktIsJan2011)) {
                typedKeyInformation.append(getMsg(SkjemaInnledningConstants.PENSJON, new String[]{grad, tidspunkt}));
            }
        }

        if (form.isForsorgningstilleggSoknad()) {
            typedKeyInformation.append(getMsg(SkjemaInnledningConstants.INFO_FORSORGERTILLEGG, new String[]{tidspunkt}));
        }

        return typedKeyInformation.toString();
    }

    private String addPercentCharToString(String percent) {
        return "" + percent + " %";
    }

    private int fetchSperrefrist() {
        return PselvUtil.fetchSperreVedtakFremITid();
    }

    private String getMsgWithLink(String key, String link) {
        String contextPath = getContextPath();
        String param = contextPath + link;
        return getMsg(key, new String[]{param});
    }

    private String getMessage(String key, String param) {
        return getMsg(key, new String[]{param});
    }

    private String getMsgWithLinkFromMessageSource(String key, String link) {
        String param = pselvPropertyMessageSource.getMessage(link, new String[]{}, getLocale());
        return getMsg(key, new String[]{param});
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String key, String[] params) {
        return messageSource.getMessage(key, params, getLocale());
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String key) {
        return messageSource.getMessage(key, new String[]{}, getLocale());
    }

    private String getConfigValue(String key) {
        return pselvConfigSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    private String getMessageWhenAdresseMangler() {
        String manglerAdresseMessage = getMsg(SkjemaInnledningConstants.MANGLER_ADRESSE_MSG);
        boolean isSelvbetjeningssone = "true".equalsIgnoreCase(getConfigValue(IS_SELVBETJENINGSSONE_KEY));
        return manglerAdresseMessage + (isSelvbetjeningssone ? " " + getMessage(SkjemaInnledningConstants.REGISTRER_ADRESSE_MSG, getPersonopplysningerUrl()) : "");
    }

    String getContextPath() {
        return RequestContextHolder.getRequestContext().getExternalContext().getContextPath();
    }

    Locale getLocale() {
        return LocaleContextHolder.getLocale();
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public void setPselvConfigSource(MessageSource source) {
        pselvConfigSource = source;
    }

    private String getPersonopplysningerUrl() {
        return getConfigValue(PERSONOPPLYSNINGER_URL_KEY) + "/skjema/innledning/" + getEncodedCurrentUrl();
    }

    private String getEncodedCurrentUrl() {
        HttpServletRequest request = getCurrentHttpServletRequest();
        String url = String.format("%s?%s", request.getRequestURL(), request.getQueryString());

        try {
            return URLEncoder.encode(url, URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    protected HttpServletRequest getCurrentHttpServletRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }
}

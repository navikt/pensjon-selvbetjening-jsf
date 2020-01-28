package no.nav.presentation.pensjon.pselv.rightcolumn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.InntektServiceBi;
import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektRequest;
import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektResponse;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.BeholdningServiceBi;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.HentBeholdningListeRequest;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.HentBeholdningListeResponse;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.HentOpptjeningsGrunnlagRequest;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.HentOpptjeningsGrunnlagResponse;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.OpptjeningsGrunnlagServiceBi;
import no.nav.consumer.pensjon.pselv.utbetaling.UtbetalingServiceBi;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.oppdrag.Utbetaling;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningHoveddel;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningsresultatUforetrygd;
import no.nav.domain.pensjon.kjerne.grunnlag.Inntektsgrunnlag;
import no.nav.domain.pensjon.kjerne.grunnlag.Utbetalingsdato;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeholdningsTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektHendelseTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektPensjonsopptjeningTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstandTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.UtbetOmradeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.UtbetOmradeCti;
import no.nav.domain.pensjon.kjerne.opptjening.Beholdning;
import no.nav.domain.pensjon.kjerne.opptjening.Inntekt;
import no.nav.domain.pensjon.kjerne.opptjening.OpptjeningsGrunnlag;
import no.nav.domain.pensjon.kjerne.opptjening.ServiceDirectiveTPOPP006;
import no.nav.domain.pensjon.kjerne.vedtak.Beregningsperiode;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.comparators.UtbDatoComparator;
import no.nav.presentation.pensjon.pselv.common.delegate.UtbetalingCommonActionDelegate;
import no.nav.repository.pensjon.batch.util.DateProvider; // TODO fix dep'y on batch
import no.nav.service.pensjon.grunnlag.GrunnlagServiceBi;
import no.nav.service.pensjon.grunnlag.HentFremtidigeUtbetalingsdatoerRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakResponse;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * Used by PUS002 "DinPensjon" and Pensjonskalkulatoren (SIS016 "Innledning") to populate the content of FES001
 * høyrekolonna.
 */
public class RightColumnHelper {

    private static final String AFP_OFFENTLIG = "pselv.fes001.statisk_tekst.afpoffentlig";
    private static final String AFP_PRIVAT = "pselv.fes001.statisk_tekst.afpprivat";
    private static final String ALDER_GAMMEL = "pselv.fes001.statisk_tekst.alderspensjonkap19";
    private static final String ALDER_FLEKSIBEL = "pselv.fes001.statisk_tekst.pensjon";
    private static final String UFORE = "pselv.fes001.statisk_tekst.ufore";
    private static final String UFORETRYGD = "pselv.fes001.statisk_tekst.uforetrygd";
    private static final int BIRTH_YEAR_1953 = 1953;
    private static final Integer TWO_MONTHS_BACK = 2;
    private VedtakServiceBi vedtakService;
    private GrunnlagServiceBi grunnlagService;
    private OpptjeningsGrunnlagServiceBi opptjeningsGrunnlagService;
    private BeholdningServiceBi beholdningService;
    private UtbetalingServiceBi utbetalingService;
    private InntektServiceBi inntektskomponentService;
    private CodesTableManager codesTableManager;
    private MessageSource messageSource;
    private UtbetalingCommonActionDelegate utbetalingActionDelegate;

    public RightColumnHelper(MessageSource messageSource, CodesTableManager codesTableManager, VedtakServiceBi vedtakService,
                             GrunnlagServiceBi grunnlagService, OpptjeningsGrunnlagServiceBi opptjeningsGrunnlagService, UtbetalingServiceBi utbetalingService,
                             UtbetalingCommonActionDelegate utbetalingActionDelegate, InntektServiceBi inntektskomponentService, BeholdningServiceBi beholdningService) {
        this.messageSource = messageSource;
        this.codesTableManager = codesTableManager;
        this.vedtakService = vedtakService;
        this.grunnlagService = grunnlagService;
        this.utbetalingService = utbetalingService;
        this.utbetalingActionDelegate = utbetalingActionDelegate;
        this.inntektskomponentService = inntektskomponentService;
        this.opptjeningsGrunnlagService = opptjeningsGrunnlagService;
        this.beholdningService = beholdningService;
    }

    public RightColumnUforetrygdFormData fetchRightColumnUforetrygdFormData(Person user, int year) {
        RightColumnUforetrygdFormData formData = new RightColumnUforetrygdFormData();

        if (formData.isNotBegrensetFullmakt() && formData.isNotLavSikkerhet()) {
            List<Vedtak> lopendeYtelser = fetchLopendeYtelser(user.getPid(), formData);
            Date nextUtbetalingsDate = null;

            if (lopendeYtelser != null) {
                List<String> typeAndUttaksgradOfLopendeYtelser = getTypeAndUttaksgradOfLopendeYtelser(lopendeYtelser, formData);
                populateBlockLopendeYtelser(lopendeYtelser, typeAndUttaksgradOfLopendeYtelser, formData);

                if (formData.isLopendeUforetrygd()) {
                    populateBlockInformasjonNavHarOmDegUforetrygd(user.getPid(), year, lopendeYtelser, formData);
                }

                nextUtbetalingsDate = fetchNextUtbetalingsDateIfUserHasLopendeYtelseWithUtbetaling(lopendeYtelser, formData);
            }

            Utbetaling mostRecentUtbetaling = fetchLatestPensjonsutbetaling(user.getPid(), formData);
            populateBlockPensjonsutbetalinger(mostRecentUtbetaling, nextUtbetalingsDate, formData);
        }

        return formData;
    }

    public RightColumnPensjonFormData fetchRightColumnFormData(Person user) {
        RightColumnPensjonFormData formData = new RightColumnPensjonFormData();

        /*
         * The blocks 'løpende ytelser' and 'pensjonsutbetalinger' are NOT visible for users with begrenset fullmakt and low
         * security.
         */
        if (formData.isNotBegrensetFullmakt() && formData.isNotLavSikkerhet()) {
            /* 10.2.1. Hent løpende ytelser. */
            List<Vedtak> lopendeYtelser = fetchLopendeYtelser(user.getPid(), formData);

            Date nextUtbetalingsDate = null;

            if (lopendeYtelser != null) {
                List<String> typeAndUttaksgradOfLopendeYtelser = getTypeAndUttaksgradOfLopendeYtelser(lopendeYtelser, formData);

                /* Populate blokk 'Løpende ytelser'. */
                populateBlockLopendeYtelser(lopendeYtelser, typeAndUttaksgradOfLopendeYtelser, formData);

                /* 10.2.3. Neste pensjonsutbetaling. */
                nextUtbetalingsDate = fetchNextUtbetalingsDateIfUserHasLopendeYtelseWithUtbetaling(lopendeYtelser, formData);
            }

            /* 10.2.2. Hent forrgie pensjonsutbetaling. */
            Utbetaling mostRecentUtbetaling = fetchLatestPensjonsutbetaling(user.getPid(), formData);

            /* Populate blokk 'Pensjonsutbetalinger'. */
            populateBlockPensjonsutbetalinger(mostRecentUtbetaling, nextUtbetalingsDate, formData);
        }
        /* 10.2.4. Hent pensjonsbeholdning for bruker. */
        Beholdning pensjonsBeholdning = fetchPensjonsBeholdning(user.getPid(), formData);

        /* 10.2.5. Hent bruksers pensjonsgivende inntekt. */
        Inntekt pensjonsGivendeInntekt = fetchLatestPensionableIncome(user.getPid(), formData);

        /* Parameters for blokk 'Informasjon NAV har om deg'. */
        populateBlockInformasjonNavHarOmDegPensjon(user.getPid(), pensjonsBeholdning, pensjonsGivendeInntekt, user.getSivilstand(), formData);
        return formData;
    }

    /**
     * Updates the RightColumnPensjonFormData with skjermbildetekst for all løpende ytelser.
     */
    private void populateBlockLopendeYtelser(List<Vedtak> lopendeYtelser, List<String> lopendeYtelserDecode,
                                             RightColumnCommonFormData formData) {
        for (Vedtak ytelse : lopendeYtelser) {
            if (isAlderspensjon(ytelse.getSakstype())) {
                formData.setLopendeAlder(true);
            } else if (ytelse.getKravhode().isUforetrygd()) {
                formData.setLopendeUforetrygd(true);
            }
        }

        if (lopendeYtelserDecode != null && !lopendeYtelserDecode.isEmpty()) {
            formData.setLopendeYtelser(lopendeYtelserDecode);
        }
    }

    /**
     * Updates the RightColumnPensjonFormData with data of latest utbetaling and next utbetaling.
     */
    private void populateBlockPensjonsutbetalinger(Utbetaling lastUtbetaling, Date nextUtbetalingsDate,
                                                   RightColumnCommonFormData formData) {
        if (lastUtbetaling != null) {
            formData.setLatestPensjonsutbetalingDate(lastUtbetaling.getDatoUtbetaling().getTime());
            formData.setLatestPensjonsutbetalingValue(lastUtbetaling.getNetto());
        }

        formData.setNextPensjonsutbetalingDate(nextUtbetalingsDate);
    }

    /**
     * Populate the block 'Informasjon NAV har om deg' for the uforetrygd context
     */
    private void populateBlockInformasjonNavHarOmDegUforetrygd(Pid pid, Integer year, List<Vedtak> lopendeYtelser, RightColumnUforetrygdFormData formData) {
        List<Inntektsgrunnlag> inntektsgrunnlagList = hentForventetInntekt(pid, year);
        formData.setSisteRegistrerteForventetInntekt(summarizeForventedeInntekter(inntektsgrunnlagList, shouldIncludeBarnetilleggInntekter(lopendeYtelser)));
        formData.setSisteRegistrerteForventetInntektAr(year.toString());
    }

    private boolean shouldIncludeBarnetilleggInntekter(List<Vedtak> lopendeYtelser) {
        boolean isAfter2015 = DateUtil.isAfterByDay(DateProvider.getToday(), DateUtil.createDate(2016, Calendar.JANUARY, 1), true);
        return hasBarnetillegg(lopendeYtelser) && isAfter2015;
    }

    private boolean hasBarnetillegg(List<Vedtak> lopendeYtelser) {
        for (Vedtak ytelse : lopendeYtelser) {
            if (ytelse.getKravhode().isUforetrygd() && ytelse.getKravhode().hasKravlinjeOfTypeBT()) {
                return true;
            }
        }

        return false;
    }

    /**
     * Summarize the newest registered forventede inntekter of type FORINTARB, FORINTNAE and FORINUTL
     */
    private Long summarizeForventedeInntekter(List<Inntektsgrunnlag> inntektsgrunnlagList, boolean includeBarnetilleggInntekter) {
        Map<InntektTypeCode, Inntektsgrunnlag> inntekterToBeSummarized = new EnumMap<>(InntektTypeCode.class);

        for (Inntektsgrunnlag grunnlag : inntektsgrunnlagList) {
            InntektTypeCode inntektTypeCode = grunnlag.getInntektType().getCode();
            if (!isValidInntektsType(inntektTypeCode, includeBarnetilleggInntekter)) {
                continue;
            }

            InntektHendelseTypeCode inntektHendelseTypeCode = grunnlag.getInntektHendelseType().getCode();
            if (inntektHendelseTypeCode.equals(InntektHendelseTypeCode.VARSLET)) {
                continue;
            }

            if (isPeriodInEffectNow(grunnlag.getFomDato(), grunnlag.getTomDato())) {
                if (!inntekterToBeSummarized.containsKey(inntektTypeCode)) {
                    inntekterToBeSummarized.put(inntektTypeCode, grunnlag);
                } else if (DateUtil.isBeforeDay(inntekterToBeSummarized.get(inntektTypeCode).getEndringstidspunkt(), grunnlag.getEndringstidspunkt())) {
                    inntekterToBeSummarized.put(inntektTypeCode, grunnlag);
                }
            }
        }
        if (inntekterToBeSummarized.isEmpty()) {
            return null;
        }

        Long sum = 0L;
        for (Map.Entry<InntektTypeCode, Inntektsgrunnlag> entry : inntekterToBeSummarized.entrySet()) {
            sum += entry.getValue().getBelop();
        }
        return sum;
    }

    private boolean isValidInntektsType(InntektTypeCode inntektTypeCode, boolean includeBarnetilleggInntekter) {
        if (inntektTypeCode.equals(InntektTypeCode.FORINTARB)
                || inntektTypeCode.equals(InntektTypeCode.FORINTNAE)
                || inntektTypeCode.equals(InntektTypeCode.FORINTUTL)) {
            return true;
        }

        return includeBarnetilleggInntekter &&
                (inntektTypeCode.equals(InntektTypeCode.FORINTAND) || inntektTypeCode.equals(InntektTypeCode.FORPENUTL));
    }

    /**
     * Fetch a List of forventede inntekter from inntektskomponenten
     */
    public List<Inntektsgrunnlag> hentForventetInntekt(Pid pid, Integer year) {
        HentForventetInntektRequest request = new HentForventetInntektRequest(pid.getPid(), Arrays.asList(year));
        HentForventetInntektResponse response = inntektskomponentService.hentForventetInntekt(request);
        return response.getInntektsgrunnlagListe();
    }

    /**
     * Updates the RightColumnPensjonFormData with pension stock, civilstatus and latest registered income.
     */
    private void populateBlockInformasjonNavHarOmDegPensjon(Pid pid, Beholdning pensjonsBeholdning,
                                                            Inntekt pensjonsGivendeInntekt, String sivilstand, RightColumnPensjonFormData formData) {
        if (isUserBorn1954OrLater(pid)) {
            formData.setBorn1954OrLater(true);
        }

        if (pensjonsBeholdning != null) {
            formData.setPensjonsbeholdningValue(pensjonsBeholdning.getBelop());
            formData.setPensjonsbeholdningDate(pensjonsBeholdning.getFomDato());
        }

        if (pensjonsGivendeInntekt != null) {
            formData.setLatestRegisteredInntekt(pensjonsGivendeInntekt.getBelop());
            formData.setLatestRegisteredInntektYear(pensjonsGivendeInntekt.getInntektAr().toString());
        }

        if (sivilstand == null) {
            formData.setErrorOpptjeningOrSivilStand(true);
        } else {
            SivilstandTypeCti sivilstandCti = codesTableManager.getCodesTablePeriodic(SivilstandTypeCti.class).getCodesTableItem(sivilstand);
            formData.setSivilStatus(sivilstandCti.getDecode());
        }
    }

    /**
     * Iterates through the list of løpende ytelser/vedtak on user. And creates a list of skjermbildetekst for the block
     * 'løpende ytelser'.
     */
    private List<String> getTypeAndUttaksgradOfLopendeYtelser(List<Vedtak> lopendeYtelser, RightColumnCommonFormData formData) {
        List<String> typeAndUttaksgradOfLopendeYtelser = new ArrayList<>();

        for (Vedtak ytelse : lopendeYtelser) {
            if (ytelse.getKravhode().isUforetrygd()) {
                String lopendeUTMessage = fetchTypeAndGradOfUforetrygd(ytelse);

                if (lopendeUTMessage != null) {
                    typeAndUttaksgradOfLopendeYtelser.add(lopendeUTMessage);
                }
            } else if (isUforepensjon(ytelse.getSakstype())) {
                String lopendeUPMessage = fetchTypeAndGradOfUforepensjon(ytelse);

                if (lopendeUPMessage != null) {
                    typeAndUttaksgradOfLopendeYtelser.add(lopendeUPMessage);
                }
            } else if (isAfpOffentlig(ytelse.getSakstype())) {
                typeAndUttaksgradOfLopendeYtelser.add(getMsg(AFP_OFFENTLIG, null));
            } else if (isAfpPrivat(ytelse.getSakstype())) {
                typeAndUttaksgradOfLopendeYtelser.add(getMsg(AFP_PRIVAT, null));
            } else if (isAlderspensjon(ytelse.getSakstype())) {
                String lopendeAPMessage = fetchTypeAndGradOfAlderspensjon(ytelse, formData);

                if (lopendeAPMessage != null) {
                    typeAndUttaksgradOfLopendeYtelser.add(lopendeAPMessage);
                }
            }
        }

        return typeAndUttaksgradOfLopendeYtelser;
    }

    private String fetchTypeAndGradOfUforetrygd(Vedtak lopendeUforetrygd) {
        for (Beregningsperiode beregningsperiode : lopendeUforetrygd.getBeregningsperiodeListe()) {
            if (isPeriodInEffectNow(beregningsperiode.getFomDato(), beregningsperiode.getTomDato())) {
                Integer uforegrad;

                try {
                    uforegrad = ((BeregningsresultatUforetrygd) beregningsperiode.getBeregningsresultat()).getUforetrygdberegning().getUforegrad();
                } catch (NullPointerException e) {
                    return null;
                }

                return getMsg(UFORETRYGD, String.valueOf(uforegrad));
            }
        }

        return null;
    }

    /**
     * Creates the skjermbildetekst (with param/uttaksgrad) of an løpende alderspensjon and sets lopendeAlderWithUttaksgradZero
     * to true on form if the uttaksgrad is greater than zero.
     *
     * @return skjermbilde element 'fleksibel alderspenjon' if vedtak is on nytt regelverk, OR skjermbilde element 'gammel
     * alderspensjon' if vedtak is on gammelt regelverk.
     */
    private String fetchTypeAndGradOfAlderspensjon(Vedtak lopendeAp, RightColumnCommonFormData formData) {
        if (!isOnNyttRegelverk(lopendeAp)) {
            return getMsg(ALDER_GAMMEL, null);
        }

        String message = null;

        for (Beregningsperiode periode : lopendeAp.getBeregningsperiodeListe()) {
            if (isPeriodInEffectNow(periode.getFomDato(), periode.getTomDato())) {
                /* If uttaksgrad is 0, lopendeAlderWithUttaksgradZero is set. */
                if (periode.getBeregningsresultat().getUttaksgrad() == 0) {
                    formData.setLopendeAlderWithUttaksgradZero(true);
                }

                message = getMsg(ALDER_FLEKSIBEL, periode.getBeregningsresultat().getUttaksgrad().toString());
            }
        }

        return message;
    }

    /**
     * Creates the skjermbildetekst with param/uføregrad of an løpende uførepensjon if the uttaksgrad is greater than zero.
     */
    private String fetchTypeAndGradOfUforepensjon(Vedtak lopendeUp) {
        String message = null;

        for (Beregning beregning : lopendeUp.getBeregningListe()) {
            if (isPeriodInEffectNow(beregning.getFomDato(), beregning.getTomDato())) {
                /* If uføregrad is null, it does not count as a løpende ytelse. */
                if (beregning.getUfg() != 0) {
                    message = getMsg(UFORE, beregning.getUfg().toString());
                }
            }
        }

        return message;
    }

    /**
     * Looks up message from properties file, i.e. resources.properties.
     */
    private String getMsg(String tekst, String param1) {
        String[] param = new String[1];
        param[0] = param1;
        return messageSource.getMessage(tekst, param, LocaleContextHolder.getLocale());
    }

    /**
     * Checks if there exists a løpende ytelse with netto payment greater than zero, and returns the utbetalingsDato of this
     * ytelse.
     */
    private Date fetchNextUtbetalingsDateIfUserHasLopendeYtelseWithUtbetaling(List<Vedtak> lopendeYtelser,
                                                                              RightColumnCommonFormData formData) {
        Date nextPayment;

        for (Vedtak ytelse : lopendeYtelser) {
            if (!ytelse.erAvTypenUforetrygd() && isOnGammeltRegelverk(ytelse)) {
                nextPayment = getNextPaymentForFormDataIfBeregningHasNetto(ytelse, formData);
                if (nextPayment != null) {
                    return nextPayment;
                }
            } else {
                nextPayment = getNextPaymentForFormDataIfTotalbelopAarExists(ytelse, formData);
                if (nextPayment != null) {
                    return nextPayment;
                }
            }
        }

        return null;
    }

    private Date getNextPaymentForFormDataIfBeregningHasNetto(Vedtak lopendeYtelse, RightColumnCommonFormData formData) {
        for (Beregning beregning : lopendeYtelse.getBeregningListe()) {
            if (beregning.getNetto() > 0) {
                return fetchTheNextUtbetaling(formData).getUtbetDato();
            }
        }

        return null;
    }

    private Date getNextPaymentForFormDataIfTotalbelopAarExists(Vedtak lopendeYtelse, RightColumnCommonFormData formData) {
        for (BeregningHoveddel beregningsResultat : lopendeYtelse.getBeregningsresultatListe()) {
            if (beregningsResultat.getPensjonUnderUtbetaling().getTotalbelopNettoAr() > 0) {
                Utbetalingsdato nextUtbetaling = fetchTheNextUtbetaling(formData);

                if (nextUtbetaling == null) {
                    throw new ImplementationUnrecoverableException(
                            "No date for next utbetaling found. Verify the contents of T_K_UTBET_DATO versus the current time: "
                                    + new Date());
                }

                return nextUtbetaling.getUtbetDato();
            }
        }

        return null;
    }

    private Utbetaling fetchLatestPensjonsutbetaling(Pid pid, RightColumnCommonFormData formData) {
        utbetalingActionDelegate.setUtbetalingService(utbetalingService);
        utbetalingActionDelegate.setCodesTableManager(codesTableManager);
        Utbetaling mostRecentUtbetaling = null;
        List<Utbetaling> utbetTwoMonthsBack;

        try {
            utbetTwoMonthsBack = utbetalingActionDelegate.hentUtbetalingsListe(pid, TWO_MONTHS_BACK);
        } catch (Exception e) {
            formData.setErrorLatestPensjonsutbetaling(true);
            return null;
        }

        List<Utbetaling> pensjonsutbetTwoMonthsBack = utbetalingActionDelegate.getPensjonsutbetalinger(utbetTwoMonthsBack);

        for (Utbetaling utbetaling : pensjonsutbetTwoMonthsBack) {
            if (utbetaling.getNetto() == 0) {
                continue;
            }

            if (mostRecentUtbetaling == null
                    || DateUtil.isBeforeDay(mostRecentUtbetaling.getDatoUtbetaling().getTime(), utbetaling.getDatoUtbetaling().getTime())) {
                mostRecentUtbetaling = utbetaling;
            }
        }

        return mostRecentUtbetaling;
    }

    private Inntekt fetchLatestPensionableIncome(Pid pid, RightColumnPensjonFormData formData) {
        OpptjeningsGrunnlag grunnlag = hentOpptjeningsgrunnlag(pid, formData);
        int mostRecentIncomeYear = 0;

        if (grunnlag == null || grunnlag.getInntektListe() == null) {
            return null;
        }

        Inntekt mostRecentPensionableIncome = null;

        for (Inntekt income : grunnlag.getInntektListe()) {
            if (isInntektOfTypePensjonsgivendeInntekt(income) && mostRecentIncomeYear < income.getInntektAr() && income.getBelop().intValue() != 0) {
                mostRecentPensionableIncome = income;
                mostRecentIncomeYear = income.getInntektAr();
            }
        }

        return mostRecentPensionableIncome;
    }

    private boolean isInntektOfTypePensjonsgivendeInntekt(Inntekt income) {
        return income.getInntektType().equals(InntektPensjonsopptjeningTypeCode.SUM_PI.name());
    }

    private OpptjeningsGrunnlag hentOpptjeningsgrunnlag(Pid pid, RightColumnPensjonFormData formData) {
        HentOpptjeningsGrunnlagRequest request = new HentOpptjeningsGrunnlagRequest();
        request.setFnr(pid);
        OpptjeningsGrunnlag opptjening = null;

        try {
            HentOpptjeningsGrunnlagResponse response = opptjeningsGrunnlagService.hentOpptjeningsGrunnlag(request);
            opptjening = response.getOpptjeningsGrunnlag();
        } catch (Exception e) {
            /* Avvikshåndering 'feil i henting av sivilstand, pensjonsbeholding eller siste kjente ligning'. */
            formData.setErrorOpptjeningOrSivilStand(true);
        }

        return opptjening;
    }

    private Utbetalingsdato fetchTheNextUtbetaling(RightColumnCommonFormData formData) {
        HentFremtidigeUtbetalingsdatoerRequest request = new HentFremtidigeUtbetalingsdatoerRequest();
        UtbetOmradeCti pensjon = codesTableManager.getCodesTablePeriodic(UtbetOmradeCti.class).getCodesTableItem(UtbetOmradeCode.PE);
        request.setUtbetOmradeCti(pensjon);
        Utbetalingsdato utbetalingsdato = null;
        List<Utbetalingsdato> utbetalingsdatoer;

        try {
            utbetalingsdatoer = grunnlagService.hentFremtidigeUtbetalingsdatoer(request).getFremtidigeUtbetalingsdatoer();
        } catch (Exception e) {
            /* Avvikshåndering 'feil i henting av neste pensjonsutbetaling'. */
            formData.setErrorNestePensjonsutbetaling(true);
            return null;
        }
        /* Sort the utbetalingsdatoer by utbetalingsdato. */
        Collections.sort(utbetalingsdatoer, new UtbDatoComparator(true));

        /* Get the first "utbetalingsdato". */
        if (!utbetalingsdatoer.isEmpty()) {
            utbetalingsdato = utbetalingsdatoer.get(0);
        }

        return utbetalingsdato;
    }

    private Beholdning fetchPensjonsBeholdning(Pid pid, RightColumnPensjonFormData formData) {
        Beholdning mostRecentBeholdning = null;

        if (isUserBorn1954OrLater(pid)) {
            HentBeholdningListeRequest request = new HentBeholdningListeRequest();
            request.setFnr(pid);
            request.setServiceDirectiveTPOPP006(ServiceDirectiveTPOPP006.ENTITY_ONLY);
            request.setBeholdningType(BeholdningsTypeCode.PEN_B.name());

            try {
                HentBeholdningListeResponse response = beholdningService.hentBeholdningListe(request);
                mostRecentBeholdning = fetchMostRecentBeholdning(response.getBeholdninger());
            } catch (Exception e) {
                /* Avvikshåndering 'feil i henting av sivilstand, pensjonsbeholding eller siste kjente ligning'. */
                formData.setErrorOpptjeningOrSivilStand(true);
            }
        }

        return mostRecentBeholdning;
    }

    private boolean isUserBorn1954OrLater(Pid pid) {
        return Pid.get4DigitYearOfBirth(pid.getPid()) > BIRTH_YEAR_1953;
    }

    private List<Vedtak> fetchLopendeYtelser(Pid pid, RightColumnCommonFormData formData) {
        Date today = DateProvider.getToday();
        BestemGjeldendeVedtakRequest request = new BestemGjeldendeVedtakRequest();
        request.setPid(pid);
        request.setFomDato(today);
        request.setTomDato(today);
        List<Vedtak> lopendeYtelser = null;

        try {
            BestemGjeldendeVedtakResponse response = vedtakService.bestemGjeldendeVedtak(request);
            lopendeYtelser = response.getVedtakListe();
        } catch (Exception e) {
            /* Avvikshåndering 'feil i henting av vedtak'. */
            formData.setErrorNestePensjonsutbetaling(true);
        }

        return lopendeYtelser;
    }

    private Beholdning fetchMostRecentBeholdning(List<Beholdning> beholdningListe) {
        Beholdning mostRecentBeholdning = null;

        for (Beholdning beholdning : beholdningListe) {
            if (mostRecentBeholdning == null
                    || DateUtil.isBeforeByDay(mostRecentBeholdning.getFomDato(), beholdning.getFomDato(), true)) {
                mostRecentBeholdning = beholdning;
            }
        }

        return mostRecentBeholdning;
    }

    private boolean isPeriodInEffectNow(Date fom, Date tom) {
        return DateUtil.isDateInPeriod(DateProvider.getToday(), fom, tom);
    }

    private boolean isAlderspensjon(SakTypeCti sakstype) {
        return sakstype.isCodeEqualTo(SakTypeCode.ALDER);
    }

    private boolean isAfpOffentlig(SakTypeCti sakstype) {
        return sakstype.isCodeEqualTo(SakTypeCode.AFP);
    }

    private boolean isAfpPrivat(SakTypeCti sakstype) {
        return sakstype.isCodeEqualTo(SakTypeCode.AFP_PRIVAT);
    }

    private boolean isUforepensjon(SakTypeCti sakstype) {
        return sakstype.isCodeEqualTo(SakTypeCode.UFOREP);
    }

    private boolean isOnNyttRegelverk(Vedtak lopendeAp) {
        return !isOnGammeltRegelverk(lopendeAp);
    }

    private boolean isOnGammeltRegelverk(Vedtak lopendeAp) {
        return lopendeAp.getKravhode().getRegelverkType() == null
                || lopendeAp.getKravhode().getRegelverkType().equals(RegelverkTypeCode.G_REG);
    }
}

package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import org.apache.commons.lang.time.DateUtils;

import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;
import no.nav.presentation.pensjon.pselv.common.PselvConstants;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonForm;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning.support.SkjemaInnledningConstants;

public class SkjemaInnledningForm extends SkjemaAlderspensjonCommonForm {

    private static final long serialVersionUID = 4587778727973913294L;
    private static final String PAGE_TITLE_FT = "pselv.sks001.skjermbildetittel.tittelft";
    private static final String PAGE_TITLE_AP = "pselv.sks001.skjermbildetittel.tittelap";

    /**
     * The nytt regelverk date 01.01.2001
     */
    public static final String JANUARY_2011 = "pselv.sks001.statisk_tekst.januar2011";

    public static final String FULL_GRAD = "100 %";
    private Long skjemaId;
    private Boolean forsorgningstilleggEPS;
    private Boolean forsorgningstilleggBarn;
    private List<SelectItem> pensjoneringsArListe;
    private List<SelectItem> pensjoneringsgradListe;
    private String valgtPensjoneringsGrad = "100 %";
    private ElektroniskSkjemaTypeCode forsorgingsTilleggCode;
    private boolean receivingAP;
    private boolean soknadInne;
    private boolean uforepensjonTilBehandling;
    private boolean lopendeAFP;
    private boolean lopendeGjenlevende;
    private boolean lopendeUforepensjon;
    private boolean hasLopendeUforeGjenlevOrFamplAt67M = false;
    private boolean hasLopendeUforeAt67M = false;
    private boolean showLiteBox = false;

    /**
     * CR 147415 A message is shown to the user if we suspect that the user has offentlig AFP vedtak
     */
    private boolean showMessageLopendeAFP;

    private Boolean svarAFP;
    private boolean visPensjoneringsGradBoks;
    private boolean fylt67Aar;
    private Integer uforeGrad;
    private String valgtPensjoneringstidspunkt;
    private SkjemaDataForPreComplementing skjemaData;

    /**
     * Variables used when user applies for Forsorgertillegg
     */
    private Integer uttaksgradExistingAlderpsensjonSak;
    private boolean userHasAlderspensjonNyttRegelverk;
    private boolean userHasUforeVedtakFremITid;
    private boolean userHasManglendeEpsInformasjon;

    // SIR 153326

    /**
     * The key to the error message to display if the user can't apply for the requested type of ytelse, found in
     * resources.properties
     */
    private String errorMessageUserCantApply;

    /**
     * The key to the information message to display if the user has another ytelse, found in resources.properties
     */
    private String infoUserHasOtherYtelse;

    private String innledendeText;
    private SimpleDateFormat sdf = new SimpleDateFormat(SkjemaInnledningConstants.DATE_FORMAT);

    // Helper methods

    /**
     * Check if user has aldready applied for alderspensjon. This is the case if user has a alderspensjon sak with status
     * lopende (receives AP) or til behandling.
     */
    public boolean userHasAlreadyAppliedAlderspensjon() {
        return receivingAP || soknadInne;
    }

    /**
     * @return true if the type of application is alderspensjon and the user has a sak of type 'UFOREP' with status 'LOPENDE'
     * and uføregrad is greater than 80%.
     */
    public boolean isIkkeSokePgaUforeOver80(Pid pid) {
        if (canChooseAgeOver67And1Month(pid)) {
            return false;
        }

        return uforeGrad != null && uforeGrad > 80;
    }

    /**
     * Check if user can choose a date which is over the users age of 67 + one month
     */
    public boolean canChooseAgeOver67And1Month(Pid pid) {
        Date fodselsDato = pid.getFodselsdato();
        Date dateWhenUserIs67And1Month = DateUtils.addYears(fodselsDato, 67);
        dateWhenUserIs67And1Month = DateUtils.addMonths(dateWhenUserIs67And1Month, 1);
        Date latestPossibleDate = DateUtils.addMonths(new Date(), PselvConstants.SPERREFRIST);
        return latestPossibleDate.after(dateWhenUserIs67And1Month);
    }

    /**
     * If an error message is visible in view, the user can not apply, and so the move to next view button should not be
     * visible.
     */
    @Override
    public boolean isShowGaaVidereKnapp() {
        return !isShowErrorMessage();
    }

    public Boolean getForsorgningstilleggBarn() {
        return forsorgningstilleggBarn;
    }

    public void setForsorgningstilleggBarn(Boolean forsorgningstilleggBarn) {
        this.forsorgningstilleggBarn = forsorgningstilleggBarn;
    }

    public Boolean getForsorgningstilleggEPS() {
        return forsorgningstilleggEPS;
    }

    public void setForsorgningstilleggEPS(Boolean forsorgningstilleggEPS) {
        this.forsorgningstilleggEPS = forsorgningstilleggEPS;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }

    public void setSkjemaId(Long skjemaInnledningId) {
        skjemaId = skjemaInnledningId;
    }

    public List<SelectItem> getPensjoneringsArListe() {
        return pensjoneringsArListe;
    }

    public void setPensjoneringsArListe(List<SelectItem> pensjoneringsArListe) {
        this.pensjoneringsArListe = pensjoneringsArListe;
    }

    public String getValgtPensjoneringsGrad() {
        return valgtPensjoneringsGrad;
    }

    public String getValgtPensjoneringsGradWithoutPercentageSign() {
        return valgtPensjoneringsGrad.replaceAll("[^0-9]", "");
    }

    public void setValgtPensjoneringsGrad(String valgtPensjoneringsGrad) {
        this.valgtPensjoneringsGrad = valgtPensjoneringsGrad;
    }

    public ElektroniskSkjemaTypeCode getForsorgingsTilleggCode() {
        return forsorgingsTilleggCode;
    }

    public boolean isReceivingAP() {
        return receivingAP;
    }

    public void setReceivingAP(boolean receivingAP) {
        this.receivingAP = receivingAP;
    }

    public boolean isSoknadInne() {
        return soknadInne;
    }

    public void setSoknadInne(boolean soknadInne) {
        this.soknadInne = soknadInne;
    }

    public void setForsorgingsTilleggCode(ElektroniskSkjemaTypeCode forsorgingsTilleggCode) {
        this.forsorgingsTilleggCode = forsorgingsTilleggCode;
    }

    @Override
    public String getPageTitle() {
        return isForsorgningstilleggSoknad() ? PAGE_TITLE_FT : PAGE_TITLE_AP;
    }

    @Override
    public String getSkjemaStepHeader() {
        // Not implemented, as we're not using ramme-skjema.xhtml.
        return null;
    }

    public boolean isVisNedtrekksliste() {
        return pensjoneringsArListe != null && pensjoneringsArListe.size() > 1;
    }

    /**
     * Set visPensjoneringsGradBoks to true if valgtPensjoneringsAr is 01.01.2011
     */
    public void visBlokker(AjaxBehaviorEvent event) {
        Calendar valgtAar = Calendar.getInstance();

        try {
            valgtAar.setTime(sdf.parse(getValgtPensjoneringstidspunkt()));
        } catch (ParseException e) {
            throw new ImplementationUnrecoverableException(e);
        }

        if (!isBornFomDec1943() &&
                (isBornBefore1943() || DateUtil.isBeforeDay(valgtAar.getTime(), PselvConstants.DATO_NYTT_REGELVERK))) {
            visPensjoneringsGradBoks = false;
        } else {
            visPensjoneringsGradBoks = true;
        }
    }

    public boolean isLopendeAFP() {
        return lopendeAFP;
    }

    public void setLopendeAFP(boolean lopendeAFP) {
        this.lopendeAFP = lopendeAFP;
    }

    public boolean isLopendeGjenlevende() {
        return lopendeGjenlevende;
    }

    public void setLopendeGjenlevende(boolean lopendeGjenlevende) {
        this.lopendeGjenlevende = lopendeGjenlevende;
    }

    public boolean isLopendeUforepensjon() {
        return lopendeUforepensjon;
    }

    public void setLopendeUforepensjon(boolean lopendeUforepensjon) {
        this.lopendeUforepensjon = lopendeUforepensjon;
    }

    public void setHasLopendeUforeGjenlevOrFamplAt67M(boolean lopendeUforeGjenlevOrFamplAfter67M) {
        this.hasLopendeUforeGjenlevOrFamplAt67M = lopendeUforeGjenlevOrFamplAfter67M;
    }

    public boolean getHasLopendeUforeGjenlevOrFamplAt67M() {
        return hasLopendeUforeGjenlevOrFamplAt67M;
    }

    public List<SelectItem> getPensjoneringsgradListe() {
        return pensjoneringsgradListe;
    }

    public void setPensjoneringsgradListe(List<SelectItem> pensjoneringsgradListe) {
        this.pensjoneringsgradListe = pensjoneringsgradListe;
    }

    public Boolean getSvarAFP() {
        return svarAFP;
    }

    public void setSvarAFP(Boolean svarAFP) {
        this.svarAFP = svarAFP;
    }

    public boolean isFylt67Aar() {
        return fylt67Aar;
    }

    public void setFylt67Aar(boolean fylt67Aar) {
        this.fylt67Aar = fylt67Aar;
    }

    public boolean isVisPensjoneringsGradBoks() {
        return visPensjoneringsGradBoks;
    }

    public void setVisPensjoneringsGradBoks(boolean visPensjoneringsGradBoks) {
        this.visPensjoneringsGradBoks = visPensjoneringsGradBoks;
    }

    public boolean isDelvisPensjon() {
        return valgtPensjoneringsGrad != null && !valgtPensjoneringsGrad.equals(FULL_GRAD);
    }

    public String getValgtPensjoneringstidspunkt() {
        return valgtPensjoneringstidspunkt;
    }

    public void setValgtPensjoneringstidspunkt(String valgtPensjoneringstidspunkt) {
        this.valgtPensjoneringstidspunkt = valgtPensjoneringstidspunkt;
    }

    public Integer getUforeGrad() {
        return uforeGrad;
    }

    public void setUforeGrad(Integer uforeGrad) {
        this.uforeGrad = uforeGrad;
    }

    public boolean isUforepensjonTilBehandling() {
        return uforepensjonTilBehandling;
    }

    public void setUforepensjonTilBehandling(boolean uforepensjonTilBehandling) {
        this.uforepensjonTilBehandling = uforepensjonTilBehandling;
    }

    public boolean isShowInfoTextForsorgingstillegg() {
        return isFylt67Aar() && isAldersPensjonSoknad();
    }

    public boolean isGammeltRegelverk() {
        return isBornBefore1943() || isBornBetweenJanNov1943() && !isPensjoneringstidspunktStartDateNyttRegelverk();
    }

    public SkjemaDataForPreComplementing getSkjemaData() {
        return skjemaData;
    }

    public void setSkjemaData(SkjemaDataForPreComplementing skjemaData) {
        this.skjemaData = skjemaData;
    }

    public String getErrorMessageUserCantApply() {
        return errorMessageUserCantApply;
    }

    public void setErrorMessageUserCantApply(String errorMessageUserCantApply) {
        this.errorMessageUserCantApply = errorMessageUserCantApply;
    }

    public String getInfoUserHasOtherYtelse() {
        return infoUserHasOtherYtelse;
    }

    public void setInfoUserHasOtherYtelse(String infoUserHasOtherYtelse) {
        this.infoUserHasOtherYtelse = infoUserHasOtherYtelse;
    }

    public boolean isShowErrorMessage() {
        return errorMessageUserCantApply != null;
    }

    public boolean isShowInfoMessage() {
        return infoUserHasOtherYtelse != null;
    }

    public boolean isShowMessageLopendeAFP() {
        return showMessageLopendeAFP;
    }

    public void setHasLopendeOffentligAFP(boolean hasLopendeOffentligAFP) {
        showMessageLopendeAFP = hasLopendeOffentligAFP;
    }

    public String getInnledendeText() {
        return innledendeText;
    }

    public void setInnledendeText(String innledendeText) {
        this.innledendeText = innledendeText;
    }

    public Integer getUttaksgradExistingAlderpsensjonSak() {
        return uttaksgradExistingAlderpsensjonSak;
    }

    public void setUttaksgradExistingAlderpsensjonSak(Integer uttaksgradExistingAlderpsensjonSak) {
        this.uttaksgradExistingAlderpsensjonSak = uttaksgradExistingAlderpsensjonSak;
    }

    public boolean isUserHasAlderspensjonNyttRegelverk() {
        return userHasAlderspensjonNyttRegelverk;
    }

    public void setUserHasAlderspensjonNyttRegelverk(boolean isNyttRegevelverkExistingAlderspenjonSak) {
        userHasAlderspensjonNyttRegelverk = isNyttRegevelverkExistingAlderspenjonSak;
    }

    public boolean getShowLiteBox() {
        return showLiteBox;
    }

    public void setShowLiteBox(boolean showLiteBox) {
        this.showLiteBox = showLiteBox;
    }

    public boolean getHasLopendeUforeAt67M() {
        return hasLopendeUforeAt67M;
    }

    public void setHasLopendeUforeAt67M(boolean hasLopendeUforeAt67M) {
        this.hasLopendeUforeAt67M = hasLopendeUforeAt67M;
    }

    public void setUserHasUforeVedtakFremITid(boolean userHasUforeVedtakFremITid) {
        this.userHasUforeVedtakFremITid = userHasUforeVedtakFremITid;
    }

    public boolean isUserHasUforeVedtakFremITid() {
        return userHasUforeVedtakFremITid;
    }

    public boolean isUserHasManglendeEpsInformasjon() {
        return userHasManglendeEpsInformasjon;
    }

    public void setUserHasManglendeEpsInformasjon(boolean userHasManglendeEpsInformasjon) {
        this.userHasManglendeEpsInformasjon = userHasManglendeEpsInformasjon;
    }
}

package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import org.apache.commons.lang.StringUtils;

import no.stelvio.common.util.DateUtil;

import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;
import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.SkjemaEndringAlderspensjonCommonForm;

/**
 * The form for endring alderspensjon SKS016
 *
 */
public class EndringAlderspensjonForm extends SkjemaEndringAlderspensjonCommonForm {
    private static final long serialVersionUID = -8441271984427397034L;

    /**
     * String representing the choice 'Endre uttaksgrad og inkluder ny opptjening'
     */
    private static final String INKLUDER_OPPTJENING_OG_ENDRE_UTTAK = "inkluderOgEndre";

    /**
     * String representing the choice 'Inkluder kun ny opptjening'
     */
    private static final String INKLUDER_OPPTJENING = "inkluder";

    /**
     * String representing the choice made by user
     */
    private String handlingsValg;

    /**
     * The helptext for popup endre uttaksgrad
     */
    private String endreUttaksgradInfoTekst;

    /**
     * The helptext for popup endre uttaksgrad og inkluder opptjening
     */
    private String inkluderOpptjeningInfoTekst;

    /**
     * The list of uttaksgrader
     */
    private List<SelectItem> uttaksgrader = new ArrayList<SelectItem>();

    /**
     * The list of uttakstidspunkter
     */
    private List<SelectItem> uttakstidspunkter = new ArrayList<SelectItem>();

    /**
     * The indicator of whether bruker kan søke
     */
    private boolean brukerKanSoke = false;

    /**
     * The status melding
     */
    private String statusMelding;

    /**
     * The indicator of whether to show inkluder ny opptjening
     */
    private Boolean visInkluderNyOpptjening;

    /**
     * The date for when to begin including opptjening
     */
    private Date inkluderOpptjeningstidspunkt;

    /**
     * The melding
     */
    private String melding;

    /**
     * The gjelende uttaksgrad
     */
    private Integer gjeldendeUttaksgrad;

    /**
     * The gjeldende uttakstidspunkt
     */
    private Date gjeldendeUttakstidspunkt;

    private Date fomFramITid;

    /**
     * Skjema for pre complementing
     */
    private SkjemaDataForPreComplementing skjema;

    private Date endringOpptjeningAldersvedtakFom;

    private Integer uforegrad;

    private String ytelseFramITid;

    private boolean hasLopendeUforeGjenlevOrFamplAt67M = false;

    private boolean isLopendeVedtakAt67mOfSakstypUfore = false;

    private boolean showLiteBox = false;

    /**
     * @return the endringOpptjeningAldersvedtakFom
     */
    public Date getEndringOpptjeningAldersvedtakFom() {
        return endringOpptjeningAldersvedtakFom;
    }

    /**
     * Show dropdown list with uttaksgrader in view, if it exists.
     *
     * @return boolean
     */
    public boolean isShowUttaksgradDropdown() {
        return uttaksgrader != null && uttaksgrader.size() > 1;
    }

    /**
     * Return true if the user has chosen 'Endre uttaksgrad og inkluder ny opptjening'
     *
     * @return boolean
     */
    public boolean hasUserChosenEndreUttaksgradAndInkluderNyOpptjening() {
        return !getVisInkluderNyOpptjening()
                || handlingsValg != null && handlingsValg.equals(INKLUDER_OPPTJENING_OG_ENDRE_UTTAK);
    }

    /**
     * Return true if the user has chosen 'Inkluder ny opptjening'
     *
     * @return boolean if user has chosen ny opptjening
     */
    public boolean hasUserChosenInkluderNyOpptjening() {
        return handlingsValg != null && handlingsValg.equals(INKLUDER_OPPTJENING);
    }

    /**
     * Returns whether the form requires handlingsvalg
     *
     * @return whether handlingsvalg is required
     */
    public boolean kreverHandlingsvalg() {
        return getVisInkluderNyOpptjening();
    }

    /**
     * Sets the uttaksgrad on the inputData.
     *
     * @param uttaksgrad the uttaksgrad to set
     */
    public void setUttaksgrad(String uttaksgrad) {
        if (StringUtils.isNotBlank(uttaksgrad)) {
            getInputData().setUttaksgrad(Integer.parseInt(uttaksgrad));
        } else {
            getInputData().setUttaksgrad(null);
        }
    }

    /**
     * Returns the uttaksgrad stored on inputData.
     *
     * @return the uttaksgrad
     */
    public String getUttaksgrad() {
        if (getInputData().getUttaksgrad() != null) {
            return getInputData().getUttaksgrad().toString();
        }

        return null;
    }

    /**
     * Sets the uttakstidspunkt on the skjemaCommonInputData.
     *
     * @param uttakstidspunkt the uttakstidspunkt to set
     */
    public void setUttakstidspunkt(String uttakstidspunkt) {
        if (StringUtils.isNotBlank(uttakstidspunkt)) {
            getInputData().setUttakstidspunkt(DateUtil.parse(uttakstidspunkt));
        } else {
            getInputData().setUttakstidspunkt(null);
        }
    }

    /**
     * Return the uttakstidspunkt from skjemaCommonInputdata.
     *
     * @return the uttakstidspunkt
     */
    public String getUttakstidspunkt() {
        return DateUtil.format(getInputData().getUttakstidspunkt());
    }

    /**
     * Return the string representing the choice 'Inkluder opptjening og endre uttaksgrad'.
     *
     * @return String
     */
    public String getInkluderOpptjeningOgEndreUttakValue() {
        return INKLUDER_OPPTJENING_OG_ENDRE_UTTAK;
    }

    /**
     * Return the string representing the choice 'Inkluder kun ny opptjening'.
     *
     * @return String
     */
    public String getInkluderOpptjeningValue() {
        return INKLUDER_OPPTJENING;
    }

    /**
     * Returns whether bruker kan søke
     *
     * @return whether bruker kan søke
     */
    public boolean isBrukerKanSoke() {
        return brukerKanSoke;
    }

    /**
     * Sets whether bruker kan søke
     *
     * @param brukerKanSoke indicates whether bruker kan søke
     */
    public void setBrukerKanSoke(boolean brukerKanSoke) {
        this.brukerKanSoke = brukerKanSoke;
    }

    /**
     * Returns the list of uttaksgrader
     *
     * @return the list of uttaksgrader
     */
    public List<SelectItem> getUttaksgrader() {
        return uttaksgrader;
    }

    /**
     * Sets the list of uttaksgrader
     *
     * @param uttaksgrader the list of uttaksgrader
     */
    public void setUttaksgrader(List<SelectItem> uttaksgrader) {
        this.uttaksgrader = uttaksgrader;
    }

    /**
     * Gets whether to show inkluder ny opptjening
     *
     * @return the indicator for whether to vis inkluder ny opptjening
     */
    public Boolean getVisInkluderNyOpptjening() {
        return visInkluderNyOpptjening;
    }

    /**
     * Sets whether to show inkluder ny opptjening
     *
     * @param visInkluderNyOpptjening the indicator for whether to vis inkluder ny opptjening
     */
    public void setVisInkluderNyOpptjening(Boolean visInkluderNyOpptjening) {
        this.visInkluderNyOpptjening = visInkluderNyOpptjening;
    }

    /**
     * Returns list of uttakstidspunkter
     *
     * @return list of uttakstidspunkter
     */
    public List<SelectItem> getUttakstidspunkter() {
        return uttakstidspunkter;
    }

    /**
     * Sets list of uttakstidspunkter
     *
     * @param uttakstidspunkter list of uttakstidspunkter
     */
    public void setUttakstidspunkter(List<SelectItem> uttakstidspunkter) {
        this.uttakstidspunkter = uttakstidspunkter;
    }

    /**
     * Returns Inkluder Opptjeningstidspunkt
     *
     * @return date for the inkluder opptjeningstidspunkt
     */
    public Date getInkluderOpptjeningstidspunkt() {
        return inkluderOpptjeningstidspunkt;
    }

    /**
     * Sets Inkluder Opptjeningstidspunkt
     *
     * @param date date for the inkluder opptjeningstidspunkt
     */
    public void setInkluderOpptjeningstidspunkt(Date date) {
        inkluderOpptjeningstidspunkt = date;
    }

    /**
     * Returns whether Melding Exists
     *
     * @return the indicator whether melding exist
     */
    public boolean isMeldingExist() {
        return melding != null && !melding.isEmpty();
    }

    /**
     * Returns the melding
     *
     * @return the melding
     */
    public String getMelding() {
        return melding;
    }

    /**
     * Sets the melding
     *
     * @param melding the melding
     */
    public void setMelding(String melding) {
        this.melding = melding;
    }

    /**
     * Returns the status melding
     *
     * @return the status melding
     */
    public String getStatusMelding() {
        return statusMelding;
    }

    /**
     * Sets the status melding
     *
     * @param statusMelding the status melding
     */
    public void setStatusMelding(String statusMelding) {
        this.statusMelding = statusMelding;
    }

    /**
     * Sets the gjeldende uttaksgrad
     *
     * @param gjeldendeUttaksgrad the gjeldende uttaksgrad
     */
    public void setGjeldendeUttaksgrad(Integer gjeldendeUttaksgrad) {
        this.gjeldendeUttaksgrad = gjeldendeUttaksgrad;
    }

    /**
     * Returns the gjeldende uttaksgrad
     *
     * @return the gjeldende uttaksgrad
     */
    public Integer getGjeldendeUttaksgrad() {
        return gjeldendeUttaksgrad;
    }

    /**
     * Sets the gjeldende uttakstidspunkt
     *
     * @param gjeldendeUttakstidspunkt the gjeldende uttakstidspunkt
     */
    public void setGjeldendeUttakstidspunkt(Date gjeldendeUttakstidspunkt) {
        this.gjeldendeUttakstidspunkt = gjeldendeUttakstidspunkt;
    }

    /**
     * Returns the gjeldende uttakstidspunkt
     *
     * @return the gjeldende uttakstidspunkt
     */
    public Date getGjeldendeUttakstidspunkt() {
        return gjeldendeUttakstidspunkt;
    }

    /**
     * Returns the endre uttaksgrad infotekst
     *
     * @return the endre uttaksgrad infotekst
     */
    public String getEndreUttaksgradInfoTekst() {
        return endreUttaksgradInfoTekst;
    }

    /**
     * Sets the endre uttaksgrad infotekst
     *
     * @param endreUttaksgradInfoTekst the infotekst
     */
    public void setEndreUttaksgradInfoTekst(String endreUttaksgradInfoTekst) {
        this.endreUttaksgradInfoTekst = endreUttaksgradInfoTekst;
    }

    /**
     * Returns the inkluder opptjening infotekst
     *
     * @return the inkluder opptjening infotekst
     */
    public String getInkluderOpptjeningInfoTekst() {
        return inkluderOpptjeningInfoTekst;
    }

    /**
     * Sets the inkluder opptjening infotekst
     *
     * @param inkluderOpptjeningInfoTekst the infotekst
     */
    public void setInkluderOpptjeningInfoTekst(String inkluderOpptjeningInfoTekst) {
        this.inkluderOpptjeningInfoTekst = inkluderOpptjeningInfoTekst;
    }

    public String getHandlingsValg() {
        return handlingsValg;
    }

    public void setHandlingsValg(String handlingsValg) {
        this.handlingsValg = handlingsValg;
    }

    /**
     * Gets the skjema data for pre complementing
     *
     * @return the skjema for pre complementing
     */
    public SkjemaDataForPreComplementing getSkjema() {
        return skjema;
    }

    /**
     * Sets the skjema for pre complementing
     *
     * @param skjema the skjema for pre complementing
     */
    public void setSkjema(SkjemaDataForPreComplementing skjema) {
        this.skjema = skjema;
    }

    /**
     * @param endringOpptjeningAldersvedtakFom
     */
    public void setEndringOpptjeningAldersvedtakFom(Date endringOpptjeningAldersvedtakFom) {
        this.endringOpptjeningAldersvedtakFom = endringOpptjeningAldersvedtakFom;
    }

    /**
     * @param uforegrad the uforegrad to set
     */
    public void setUforegrad(Integer uforegrad) {
        this.uforegrad = uforegrad;
    }

    /**
     * @return
     */
    public Integer getUforegrad() {
        return uforegrad;
    }

    /**
     * @param ytelseFramITid the ytelseFramITid to set
     */
    public void setYtelseFramITid(String ytelseFramITid) {
        this.ytelseFramITid = ytelseFramITid;
    }

    /**
     * @return the ytelseFramITid
     */
    public String getYtelseFramITid() {
        return ytelseFramITid;
    }

    /**
     * @param fomFramITid the fomFramITid to set
     */
    public void setFomFramITid(Date fomFramITid) {
        this.fomFramITid = fomFramITid;
    }

    /**
     * @return the fomFramITid
     */
    public Date getFomFramITid() {
        return fomFramITid;
    }

    public void setHasLopendeUforeGjenlevOrFamplAt67M(boolean hasLopendeUforeGjenlevOrFamplAt67M) {
        this.hasLopendeUforeGjenlevOrFamplAt67M = hasLopendeUforeGjenlevOrFamplAt67M;
    }

    public boolean getHasLopendeUforeGjenlevOrFamplAt67M() {
        return this.hasLopendeUforeGjenlevOrFamplAt67M;
    }

    public boolean getShowLiteBox() {
        return showLiteBox;
    }

    public void setShowLiteBox(boolean showLiteBox) {
        this.showLiteBox = showLiteBox;
    }

    public boolean getHasLopendeUforeAt67M() {
        return isLopendeVedtakAt67mOfSakstypUfore;
    }

    public void setHasLopendeUforeAt67M(boolean lopendeVedtakOfSakstypUfore) {
        isLopendeVedtakAt67mOfSakstypUfore = lopendeVedtakOfSakstypUfore;
    }
}

package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import java.io.Serializable;
import java.util.Date;

import no.stelvio.common.util.DateUtil;

import no.nav.presentation.pensjon.pselv.common.enums.EndringAlderspensjonState;
import no.nav.presentation.pensjon.pselv.common.utils.DateFormatUtil;

/**
 * Holds data that will is passed through the 'endring av alderspensjon' flow, i.e. SKS016-SKS017-SKS018. The fields
 * Uttaksgrad and Uttakstidspunkt will only be set if the user chooses to change uttaksgrad. The field ForsteVirkningstidspunkt
 * will only be set if the use chooses only to include new opptjening.
 */
public class SkjemaEndringAlderspensjonCommonInputData implements Serializable {

    private static final long serialVersionUID = -3558219051212010133L;
    private Long journalpostId;
    private Long kravId;
    private Long sakId;
    private EndringAlderspensjonState endringAlderspensjonState;
    private Integer uttaksgrad;
    private Date uttakstidspunkt;
    private Date forsteVirkningstidspunkt;
    private Integer lopendeUttaksgrad;
    private Date lopendeVirkFom;
    private Integer lopendeUforegrad;

    /**
     * Checks if user has chosen the option 'Endre uttaksgrad og inkluder ny opptjening'.
     */
    public boolean getUttaksgradChange() {
        return endringAlderspensjonState.equals(EndringAlderspensjonState.KAN_SOKE_ENDRE_UTTAKSGRAD);
    }

    public Integer getUttaksgrad() {
        return uttaksgrad;
    }

    public void setUttaksgrad(Integer uttaksgrad) {
        this.uttaksgrad = uttaksgrad;
    }

    public Date getForsteVirkningstidspunkt() {
        return forsteVirkningstidspunkt;
    }

    public void setForsteVirkningstidspunkt(Date forsteVirkningstidspunkt) {
        this.forsteVirkningstidspunkt = forsteVirkningstidspunkt;
    }

    public String getFormattedUttakstidspunkt() {
        return new DateFormatUtil().getFormattedDateNullSafe(getUttakstidspunkt(), "dd.MM.yyyy");
    }

    public String getFormattedForsteVirkningstidspunkt() {
        return DateUtil.format(getForsteVirkningstidspunkt());
    }

    public Date getUttakstidspunkt() {
        return uttakstidspunkt;
    }

    public void setUttakstidspunkt(Date uttakstidspunkt) {
        this.uttakstidspunkt = uttakstidspunkt;
    }

    public Integer getLopendeUttaksgrad() {
        return lopendeUttaksgrad;
    }

    public void setLopendeUttaksgrad(Integer lopendeUttaksgrad) {
        this.lopendeUttaksgrad = lopendeUttaksgrad;
    }

    public Date getLopendeVirkFom() {
        return lopendeVirkFom;
    }

    public void setLopendeVirkFom(Date lopendeVirkFom) {
        this.lopendeVirkFom = lopendeVirkFom;
    }

    public EndringAlderspensjonState getEndringAlderspensjonState() {
        return endringAlderspensjonState;
    }

    public void setEndringAlderspensjonState(EndringAlderspensjonState endringAlderspensjonState) {
        this.endringAlderspensjonState = endringAlderspensjonState;
    }

    public Integer getLopendeUforegrad() {
        return lopendeUforegrad;
    }

    public void setLopendeUforegrad(Integer lopendeUforegrad) {
        this.lopendeUforegrad = lopendeUforegrad;
    }

    public void setKravId(Long kravId) {
        this.kravId = kravId;
    }

    public Long getKravId() {
        return kravId;
    }

    public Long getJournalpostId() {
        return journalpostId;
    }

    public void setJournalpostId(Long journalpostId) {
        this.journalpostId = journalpostId;
    }

    public Long getSakId() {
        return sakId;
    }

    public void setSakId(Long sakId) {
        this.sakId = sakId;
    }
}

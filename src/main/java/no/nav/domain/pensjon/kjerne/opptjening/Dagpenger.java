package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.time.ChangeStamp;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
//import no.nav.domain.pensjon.annotations.IgnoreOnCopyReason;
//import no.nav.domain.pensjon.common.Copyable;

/**
 * Non-persistent class used to represent Dagpenger. This class is used to map the DagpengerDto object in the request to
 * TPOPP007 hentOpptjeningsgrunnlag service.
 */
public class Dagpenger implements Serializable /*, Copyable*/ {
//TODO need to restore copyable?
    private static final long serialVersionUID = -2380699536090247014L;

    //    @IgnoreOnCopy(reason = IgnoreOnCopyReason.PRIMARY_KEY)
    private Long dagpengerId;

    private Pid fnr;
    private String dagpengerType;
    private String rapportType;
    private String kilde;
    private Integer ar;
    private Integer utbetalteDagpenger;
    private Integer uavkortetDagpengegrunnlag;
    private Integer ferietillegg;
    private Integer barnetillegg;
    private ChangeStamp changeStamp;

    public Dagpenger() {
    }

    //    @Override
    public Dagpenger copy() {
        Dagpenger dagpenger = new Dagpenger();
        dagpenger.setFnr(fnr != null ? new Pid(fnr.getPid()) : null);
        dagpenger.setDagpengerType(dagpengerType);
        dagpenger.setRapportType(rapportType);
        dagpenger.setKilde(kilde);
        dagpenger.setAr(ar);
        dagpenger.setUtbetalteDagpenger(utbetalteDagpenger);
        dagpenger.setUavkortetDagpengegrunnlag(uavkortetDagpengegrunnlag);
        dagpenger.setFerietillegg(ferietillegg);
        dagpenger.setBarnetillegg(barnetillegg);
        if (changeStamp != null) {
            ChangeStamp cs = changeStamp;
            dagpenger.setChangeStamp(new ChangeStamp(cs.getCreatedBy(), cs.getCreatedDate(), cs.getUpdatedBy(), cs.getUpdatedDate()));
        }
        return dagpenger;
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getBarnetillegg() {
        return barnetillegg;
    }

    public void setBarnetillegg(Integer barnetillegg) {
        this.barnetillegg = barnetillegg;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public Long getDagpengerId() {
        return dagpengerId;
    }

    public void setDagpengerId(Long dagpengerId) {
        this.dagpengerId = dagpengerId;
    }

    public Integer getFerietillegg() {
        return ferietillegg;
    }

    public void setFerietillegg(Integer ferietillegg) {
        this.ferietillegg = ferietillegg;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public String getRapportType() {
        return rapportType;
    }

    public void setRapportType(String rapportType) {
        this.rapportType = rapportType;
    }

    public Integer getUtbetalteDagpenger() {
        return utbetalteDagpenger;
    }

    public void setUtbetalteDagpenger(Integer utbetalteDagpenger) {
        this.utbetalteDagpenger = utbetalteDagpenger;
    }

    public Integer getUavkortetDagpengegrunnlag() {
        return uavkortetDagpengegrunnlag;
    }

    public void setUavkortetDagpengegrunnlag(Integer uavkortetDagpengegrunnlag) {
        this.uavkortetDagpengegrunnlag = uavkortetDagpengegrunnlag;
    }

    public String getDagpengerType() {
        return dagpengerType;
    }

    public void setDagpengerType(String dagpengerType) {
        this.dagpengerType = dagpengerType;
    }

    public String getKilde() {
        return kilde;
    }

    public void setKilde(String kilde) {
        this.kilde = kilde;
    }
}

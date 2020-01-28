package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.time.ChangeStamp;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
//import no.nav.domain.pensjon.annotations.IgnoreOnCopyReason;
//import no.nav.domain.pensjon.common.Copyable;

/**
 * Non-persistent class used to represent Inntekt.
 */
public class Inntekt implements Serializable/*, Copyable*/ {
    //TODO need to restore copyable?
    private static final long serialVersionUID = -2136847548272793125L;

    //    @IgnoreOnCopy(reason = IgnoreOnCopyReason.PRIMARY_KEY)
    private Long inntektId;

    private Pid fnr;
    private String kilde;
    private String kommune;
    private String piMerke;
    private Integer inntektAr;
    private Long belop;
    private ChangeStamp changeStamp;
    private String inntektType;

    public Inntekt() {
    }

    //    @Override
    public Inntekt copy() {
        Inntekt inntekt = new Inntekt();
        inntekt.setFnr(fnr != null ? new Pid(fnr.getPid()) : null);
        inntekt.setKilde(kilde);
        inntekt.setKommune(kommune);
        inntekt.setPiMerke(piMerke);
        inntekt.setInntektType(inntektType);
        inntekt.setInntektAr(inntektAr);
        inntekt.setBelop(belop);
        if (changeStamp != null) {
            ChangeStamp cs = changeStamp;
            inntekt.setChangeStamp(new ChangeStamp(cs.getCreatedBy(), cs.getCreatedDate(), cs.getUpdatedBy(), cs.getUpdatedDate()));
        }
        return inntekt;
    }

    public Long getBelop() {
        return belop;
    }

    public void setBelop(Long belop) {
        this.belop = belop;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public Integer getInntektAr() {
        return inntektAr;
    }

    public void setInntektAr(Integer inntektAr) {
        this.inntektAr = inntektAr;
    }

    public Long getInntektId() {
        return inntektId;
    }

    public void setInntektId(Long inntektId) {
        this.inntektId = inntektId;
    }

    public String getKilde() {
        return kilde;
    }

    public void setKilde(String kilde) {
        this.kilde = kilde;
    }

    public String getKommune() {
        return kommune;
    }

    public void setKommune(String kommune) {
        this.kommune = kommune;
    }

    public String getPiMerke() {
        return piMerke;
    }

    public void setPiMerke(String piMerke) {
        this.piMerke = piMerke;
    }

    public String getInntektType() {
        return inntektType;
    }

    public void setInntektType(String inntektType) {
        this.inntektType = inntektType;
    }
}

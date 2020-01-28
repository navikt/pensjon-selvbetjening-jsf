package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;

//import org.codehaus.jackson.annotate.JsonIgnore;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.time.ChangeStamp;

public class Omsorg implements Serializable {

    private static final long serialVersionUID = -7616646189361324702L;
    private Long omsorgId;
    private Pid fnr;
    private Pid fnrOmsorgFor;
    private String omsorgType;
    private String kilde;
    private Integer ar;
    private ChangeStamp changeStamp;

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    //    @JsonIgnore //TODO
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

    public Pid getFnrOmsorgFor() {
        return fnrOmsorgFor;
    }

    public void setFnrOmsorgFor(Pid fnrOmsorgFor) {
        this.fnrOmsorgFor = fnrOmsorgFor;
    }

    public Long getOmsorgId() {
        return omsorgId;
    }

    public void setOmsorgId(Long omsorgId) {
        this.omsorgId = omsorgId;
    }

    public String getOmsorgType() {
        return omsorgType;
    }

    public void setOmsorgType(String omsorgType) {
        this.omsorgType = omsorgType;
    }

    public String getKilde() {
        return kilde;
    }

    public void setKilde(String kilde) {
        this.kilde = kilde;
    }
}

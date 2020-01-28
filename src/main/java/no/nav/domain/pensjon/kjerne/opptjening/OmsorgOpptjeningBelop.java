package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent OmsorgOpptjeningBelop. This class is used to map the OmsorgOpptjeningBelopDto object
 * in the request to service.
 */
public class OmsorgOpptjeningBelop implements Serializable {

    private static final long serialVersionUID = 4218176415562528700L;
    private Long omsorgOpptjeningBelopId;
    private Integer ar;
    private Double belop;
    private Double omsorgOpptjeningInnskudd;
    private List<Omsorg> omsorgListe = new ArrayList<Omsorg>();
    private ChangeStamp changeStamp;

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Double getBelop() {
        return belop;
    }

    public void setBelop(Double belop) {
        this.belop = belop;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public List<Omsorg> getOmsorgListe() {
        return omsorgListe;
    }

    public void setOmsorgListe(List<Omsorg> omsorgListe) {
        this.omsorgListe = omsorgListe;
    }

    public Long getOmsorgOpptjeningBelopId() {
        return omsorgOpptjeningBelopId;
    }

    public void setOmsorgOpptjeningBelopId(Long omsorgOpptjeningBelopId) {
        this.omsorgOpptjeningBelopId = omsorgOpptjeningBelopId;
    }

    public Double getOmsorgOpptjeningInnskudd() {
        return omsorgOpptjeningInnskudd;
    }

    public void setOmsorgOpptjeningInnskudd(Double omsorgOpptjeningInnskudd) {
        this.omsorgOpptjeningInnskudd = omsorgOpptjeningInnskudd;
    }
}

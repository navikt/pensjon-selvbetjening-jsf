package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.Date;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent Lonnsvekstregulering. This class is used to map the LonnsvekstreguleringDto object in
 * the request to service.
 */
public class Lonnsvekstregulering implements Serializable {

    private static final long serialVersionUID = 164872103285305063L;
    private Long lonnsvekstreguleringId;
    private Double reguleringsbelop;
    private Date reguleringsDato;
    private ChangeStamp changeStamp;

    public Long getLonnsvekstreguleringId() {
        return lonnsvekstreguleringId;
    }

    public void setLonnsvekstreguleringId(Long lonnsvekstreguleringId) {
        this.lonnsvekstreguleringId = lonnsvekstreguleringId;
    }

    public Double getReguleringsbelop() {
        return reguleringsbelop;
    }

    public void setReguleringsbelop(Double reguleringsbelop) {
        this.reguleringsbelop = reguleringsbelop;
    }

    public Date getReguleringsDato() {
        return reguleringsDato;
    }

    public void setReguleringsDato(Date reguleringsDato) {
        this.reguleringsDato = reguleringsDato;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }
}

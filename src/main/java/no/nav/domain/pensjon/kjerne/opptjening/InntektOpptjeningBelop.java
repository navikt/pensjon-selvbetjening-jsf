package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent InntektOpptjeningBelop. This class is used to map the InntektOpptjeningBelopDto object
 * in the request to service.
 */
public class InntektOpptjeningBelop implements Serializable {

    private static final long serialVersionUID = 4335415529785091094L;
    private Long inntektOpptjeningBelopId;
    private Integer ar;
    private Double belop;
    private Inntekt sumPensjonsgivendeInntekt;
    private List<Inntekt> inntektListe = new ArrayList<Inntekt>();
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

    public List<Inntekt> getInntektListe() {
        return inntektListe;
    }

    public void setInntektListe(List<Inntekt> inntektListe) {
        this.inntektListe = inntektListe;
    }

    public Long getInntektOpptjeningBelopId() {
        return inntektOpptjeningBelopId;
    }

    public void setInntektOpptjeningBelopId(Long inntektOpptjeningBelopId) {
        this.inntektOpptjeningBelopId = inntektOpptjeningBelopId;
    }

    public Inntekt getSumPensjonsgivendeInntekt() {
        return sumPensjonsgivendeInntekt;
    }

    public void setSumPensjonsgivendeInntekt(Inntekt sumPensjonsgivendeInntekt) {
        this.sumPensjonsgivendeInntekt = sumPensjonsgivendeInntekt;
    }
}

package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.List;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent ForstegangstjenesteOpptjeningBelop. This class is used to map the
 * ForstegangstjenesteOpptjeningBelopDto object in the request to service.
 */
public class ForstegangstjenesteOpptjeningBelop implements Serializable {

    private static final long serialVersionUID = 4640795582244802873L;
    private Long forstegangstjenesteOpptjeningBelopId;
    private Integer ar;
    private Double belop;
    private Forstegangstjeneste forstegangstjeneste;
    private List<ForstegangstjenestePeriode> anvendtForstegangstjenestePeriodeListe;
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

    public Forstegangstjeneste getForstegangstjeneste() {
        return forstegangstjeneste;
    }

    public void setForstegangstjeneste(Forstegangstjeneste forstegangstjeneste) {
        this.forstegangstjeneste = forstegangstjeneste;
    }

    public Long getForstegangstjenesteOpptjeningBelopId() {
        return forstegangstjenesteOpptjeningBelopId;
    }

    public void setForstegangstjenesteOpptjeningBelopId(Long forstegangstjenesteOpptjeningBelopId) {
        this.forstegangstjenesteOpptjeningBelopId = forstegangstjenesteOpptjeningBelopId;
    }

    public void setAnvendtForstegangstjenestePeriodeListe(List<ForstegangstjenestePeriode> anvendtForstegangstjenestePeriodeListe) {
        this.anvendtForstegangstjenestePeriodeListe = anvendtForstegangstjenestePeriodeListe;
    }

    public List<ForstegangstjenestePeriode> getAnvendtForstegangstjenestePeriodeListe() {
        return anvendtForstegangstjenestePeriodeListe;
    }
}

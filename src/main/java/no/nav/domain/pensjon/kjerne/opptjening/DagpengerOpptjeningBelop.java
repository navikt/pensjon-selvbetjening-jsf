package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent DagpengerOpptjeningBelop. This class is used to map the DagpengerOpptjeningBelopDto
 * object in the request to service.
 */
public class DagpengerOpptjeningBelop implements Serializable {

    private static final long serialVersionUID = 3073931506379550209L;
    private Long dagpengerOpptjeningBelopId;
    private Integer ar;
    private Double belopOrdinar;
    private Double belopFiskere;
    private List<Dagpenger> dagpengerListe = new ArrayList<>();
    private ChangeStamp changeStamp;

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public ChangeStamp getChangeStamp() {
        return changeStamp;
    }

    public void setChangeStamp(ChangeStamp changeStamp) {
        this.changeStamp = changeStamp;
    }

    public Long getDagpengerOpptjeningBelopId() {
        return dagpengerOpptjeningBelopId;
    }

    public void setDagpengerOpptjeningBelopId(Long dagpengerOpptjeningBelopId) {
        this.dagpengerOpptjeningBelopId = dagpengerOpptjeningBelopId;
    }

    public Double getBelopOrdinar() {
        return belopOrdinar;
    }

    public void setBelopOrdinar(Double belopOrdinar) {
        this.belopOrdinar = belopOrdinar;
    }

    public Double getBelopFiskere() {
        return belopFiskere;
    }

    public void setBelopFiskere(Double belopFiskere) {
        this.belopFiskere = belopFiskere;
    }

    public List<Dagpenger> getDagpengerListe() {
        return dagpengerListe;
    }

    public void setDagpengerListe(List<Dagpenger> dagpengerListe) {
        this.dagpengerListe = dagpengerListe;
    }

    public void addDagpengerListe(Dagpenger dagpenger) {
        if (dagpengerListe == null) {
            dagpengerListe = new ArrayList<Dagpenger>();
        }

        dagpengerListe.add(dagpenger);
    }
}

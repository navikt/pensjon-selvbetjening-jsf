package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;
import java.util.List;

import no.stelvio.domain.person.Pid;

public class OpptjeningsGrunnlag implements Serializable {

    private static final long serialVersionUID = -1857770826401757298L;
    private Pid fnr;
    private List<Inntekt> inntektListe;
    private List<Omsorg> omsorgListe;
    private List<Dagpenger> dagpengerListe;
    private Forstegangstjeneste forstegangstjeneste;

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public List<Inntekt> getInntektListe() {
        return inntektListe;
    }

    public void setInntektListe(List<Inntekt> inntektListe) {
        this.inntektListe = inntektListe;
    }

    public List<Dagpenger> getDagpengerListe() {
        return dagpengerListe;
    }

    public void setDagpengerListe(List<Dagpenger> dagpengerListe) {
        this.dagpengerListe = dagpengerListe;
    }

    public Forstegangstjeneste getForstegangstjeneste() {
        return forstegangstjeneste;
    }

    public void setForstegangstjeneste(Forstegangstjeneste forstegangstjeneste) {
        this.forstegangstjeneste = forstegangstjeneste;
    }

    public List<Omsorg> getOmsorgListe() {
        return omsorgListe;
    }

    public void setOmsorgListe(List<Omsorg> omsorgListe) {
        this.omsorgListe = omsorgListe;
    }
}

package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.List;

public class UtlandHistorikk implements Serializable {

    private static final long serialVersionUID = 7359980525762077165L;
    private Person person;
    private List<HistorikkInfo> statsborgerskapListe;
    private List<HistorikkInfo> oppholdstillatelseListe;
    private List<HistorikkInfo> arbeidstillatelseListe;
    private List<HistorikkInfo> innvandringListe;
    private List<HistorikkInfo> utvandringListe;

    public List<HistorikkInfo> getArbeidstillatelseListe() {
        return arbeidstillatelseListe;
    }

    public void setArbeidstillatelseListe(List<HistorikkInfo> arbeidstillatelseListe) {
        this.arbeidstillatelseListe = arbeidstillatelseListe;
    }

    public List<HistorikkInfo> getInnvandringListe() {
        return innvandringListe;
    }

    public void setInnvandringListe(List<HistorikkInfo> innvandringListe) {
        this.innvandringListe = innvandringListe;
    }

    public List<HistorikkInfo> getOppholdstillatelseListe() {
        return oppholdstillatelseListe;
    }

    public void setOppholdstillatelseListe(List<HistorikkInfo> oppholdstillatelseListe) {
        this.oppholdstillatelseListe = oppholdstillatelseListe;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<HistorikkInfo> getStatsborgerskapListe() {
        return statsborgerskapListe;
    }

    public void setStatsborgerskapListe(List<HistorikkInfo> statsborgerskapListe) {
        this.statsborgerskapListe = statsborgerskapListe;
    }

    public List<HistorikkInfo> getUtvandringListe() {
        return utvandringListe;
    }

    public void setUtvandringListe(List<HistorikkInfo> utvandringListe) {
        this.utvandringListe = utvandringListe;
    }
}

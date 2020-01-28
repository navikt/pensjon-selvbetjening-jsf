package no.nav.domain.pensjon.common.oppdrag;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Utbetaling implements Serializable {
    private static final long serialVersionUID = 1486782199564340702L;

    private String utbetalingsId;

    private String delNokkel;

    private String utbetalingsAr;

    private String bilagsnrSerie;

    private String bilagsnummer;

    /**
     * The date when utbetaling is send from oppdrag to UR
     */
    private Calendar datoUtbetaling;

    /**
     * The date when utbetaling got the status stored by the property status
     */
    private Calendar statusDato;

    private String kodeYtelse;

    private String tekstYtelse;

    private String utbetalingsKode;

    private String bilagsKode;

    /**
     * datoFom and datoTom will only be set when utbetaling is not periodisert
     */
    private Calendar datoFOM;

    private Calendar datoTOM;

    /**
     * The status of the utbetaling
     */
    private String status;

    private String statusTekst;

    private String gironr;

    private String mottakerFnr;

    private String mottakerNavn;

    private Float brutto;

    private Float trekk;

    private Float netto;

    private String valuta;

    private List<Posteringsdetaljer> posteringsdetaljer;

    private String melding;

    private List<Bilag> bilagListe;

    /**
     * @return the bilagsKode
     */
    public java.lang.String getBilagsKode() {
        return bilagsKode;
    }

    /**
     * @param bilagsKode the bilagsKode to set
     */
    public void setBilagsKode(java.lang.String bilagsKode) {
        this.bilagsKode = bilagsKode;
    }

    /**
     * @return the bilagsnrSerie
     */
    public java.lang.String getBilagsnrSerie() {
        return bilagsnrSerie;
    }

    /**
     * @param bilagsnrSerie the bilagsnrSerie to set
     */
    public void setBilagsnrSerie(java.lang.String bilagsnrSerie) {
        this.bilagsnrSerie = bilagsnrSerie;
    }

    /**
     * @return the bilagsnummer
     */
    public java.lang.String getBilagsnummer() {
        return bilagsnummer;
    }

    /**
     * @param bilagsnummer the bilagsnummer to set
     */
    public void setBilagsnummer(java.lang.String bilagsnummer) {
        this.bilagsnummer = bilagsnummer;
    }

    /**
     * @return the brutto
     */
    public java.lang.Float getBrutto() {
        return brutto;
    }

    /**
     * @param brutto the brutto to set
     */
    public void setBrutto(java.lang.Float brutto) {
        this.brutto = brutto;
    }

    /**
     * @return the datoFOM
     */
    public java.util.Calendar getDatoFOM() {
        return datoFOM;
    }

    /**
     * @param datoFOM the datoFOM to set
     */
    public void setDatoFOM(java.util.Calendar datoFOM) {
        this.datoFOM = datoFOM;
    }

    /**
     * @return the datoTOM
     */
    public java.util.Calendar getDatoTOM() {
        return datoTOM;
    }

    /**
     * @param datoTOM the datoTOM to set
     */
    public void setDatoTOM(java.util.Calendar datoTOM) {
        this.datoTOM = datoTOM;
    }

    /**
     * @return the datoUtbetaling
     */
    public java.util.Calendar getDatoUtbetaling() {
        return datoUtbetaling;
    }

    /**
     * @param datoUtbetaling the datoUtbetaling to set
     */
    public void setDatoUtbetaling(java.util.Calendar datoUtbetaling) {
        this.datoUtbetaling = datoUtbetaling;
    }

    /**
     * @return the delNokkel
     */
    public java.lang.String getDelNokkel() {
        return delNokkel;
    }

    /**
     * @param delNokkel the delNokkel to set
     */
    public void setDelNokkel(java.lang.String delNokkel) {
        this.delNokkel = delNokkel;
    }

    /**
     * @return the gironr
     */
    public java.lang.String getGironr() {
        return gironr;
    }

    /**
     * @param gironr the gironr to set
     */
    public void setGironr(java.lang.String gironr) {
        this.gironr = gironr;
    }

    /**
     * @return the kodeYtelse
     */
    public java.lang.String getKodeYtelse() {
        return kodeYtelse;
    }

    /**
     * @param kodeYtelse the kodeYtelse to set
     */
    public void setKodeYtelse(java.lang.String kodeYtelse) {
        this.kodeYtelse = kodeYtelse;
    }

    /**
     * @return the melding
     */
    public java.lang.String getMelding() {
        return melding;
    }

    /**
     * @param melding the melding to set
     */
    public void setMelding(java.lang.String melding) {
        this.melding = melding;
    }

    /**
     * @return the mottakerFnr
     */
    public java.lang.String getMottakerFnr() {
        return mottakerFnr;
    }

    /**
     * @param mottakerFnr the mottakerFnr to set
     */
    public void setMottakerFnr(java.lang.String mottakerFnr) {
        this.mottakerFnr = mottakerFnr;
    }

    /**
     * @return the mottakerNavn
     */
    public java.lang.String getMottakerNavn() {
        return mottakerNavn;
    }

    /**
     * @param mottakerNavn the mottakerNavn to set
     */
    public void setMottakerNavn(java.lang.String mottakerNavn) {
        this.mottakerNavn = mottakerNavn;
    }

    /**
     * @return the netto
     */
    public java.lang.Float getNetto() {
        return netto;
    }

    /**
     * @param netto the netto to set
     */
    public void setNetto(java.lang.Float netto) {
        this.netto = netto;
    }

    /**
     * @return the posteringsdetaljer
     */
    public List<Posteringsdetaljer> getPosteringsdetaljer() {
        return posteringsdetaljer;
    }

    /**
     * @param posteringsdetaljer the posteringsdetaljer to set
     */
    public void setPosteringsdetaljer(List<Posteringsdetaljer> posteringsdetaljer) {
        this.posteringsdetaljer = posteringsdetaljer;
    }

    /**
     * @return the status
     */
    public java.lang.String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(java.lang.String status) {
        this.status = status;
    }

    /**
     * @return the statusDato
     */
    public java.util.Calendar getStatusDato() {
        return statusDato;
    }

    /**
     * @param statusDato the statusDato to set
     */
    public void setStatusDato(java.util.Calendar statusDato) {
        this.statusDato = statusDato;
    }

    /**
     * @return the statusTekst
     */
    public java.lang.String getStatusTekst() {
        return statusTekst;
    }

    /**
     * @param statusTekst the statusTekst to set
     */
    public void setStatusTekst(java.lang.String statusTekst) {
        this.statusTekst = statusTekst;
    }

    /**
     * @return the tekstYtelse
     */
    public java.lang.String getTekstYtelse() {
        return tekstYtelse;
    }

    /**
     * @param tekstYtelse the tekstYtelse to set
     */
    public void setTekstYtelse(java.lang.String tekstYtelse) {
        this.tekstYtelse = tekstYtelse;
    }

    /**
     * @return the trekk
     */
    public java.lang.Float getTrekk() {
        return trekk;
    }

    /**
     * @param trekk the trekk to set
     */
    public void setTrekk(java.lang.Float trekk) {
        this.trekk = trekk;
    }

    /**
     * @return the utbetalingsAr
     */
    public java.lang.String getUtbetalingsAr() {
        return utbetalingsAr;
    }

    /**
     * @param utbetalingsAr the utbetalingsAr to set
     */
    public void setUtbetalingsAr(java.lang.String utbetalingsAr) {
        this.utbetalingsAr = utbetalingsAr;
    }

    /**
     * @return the utbetalingsId
     */
    public java.lang.String getUtbetalingsId() {
        return utbetalingsId;
    }

    /**
     * @param utbetalingsId the utbetalingsId to set
     */
    public void setUtbetalingsId(java.lang.String utbetalingsId) {
        this.utbetalingsId = utbetalingsId;
    }

    /**
     * @return the utbetalingsKode
     */
    public java.lang.String getUtbetalingsKode() {
        return utbetalingsKode;
    }

    /**
     * @param utbetalingsKode the utbetalingsKode to set
     */
    public void setUtbetalingsKode(java.lang.String utbetalingsKode) {
        this.utbetalingsKode = utbetalingsKode;
    }

    /**
     * @return the valuta
     */
    public java.lang.String getValuta() {
        return valuta;
    }

    /**
     * @param valuta the valuta to set
     */
    public void setValuta(java.lang.String valuta) {
        this.valuta = valuta;
    }

    /**
     * @return the bilagListe
     */
    public List<Bilag> getBilagListe() {
        return bilagListe;
    }

    /**
     * @param bilagListe the bilagListe to set
     */
    public void setBilagListe(List<Bilag> bilagListe) {
        this.bilagListe = bilagListe;
    }
}

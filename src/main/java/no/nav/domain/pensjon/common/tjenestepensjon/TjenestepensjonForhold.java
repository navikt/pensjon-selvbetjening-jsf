package no.nav.domain.pensjon.common.tjenestepensjon;

import java.io.Serializable;
import java.util.Calendar;

import java.util.List;

/**
 * The mirror domain object of the ASBOPenTjenestepensjonForhold ESB object.
 */
public class TjenestepensjonForhold implements Serializable {

    private static final long serialVersionUID = -333255566007702215L;
    private String forholdId;
    private String tssEksternId;
    private String navn;
    private String tpNr;
    private Boolean harUtlandPensjon;
    private String samtykkeSimuleringKode;
    private Calendar samtykkeDato;
    private Boolean harSimulering;
    private List<TjenestepensjonsYtelse> tjenestepensjonYtelseListe;
    private TjenestepensjonSimulering tjenestepensjonSimulering;
    private no.nav.domain.pensjon.common.Endringsinfo endringsInfo;
    private String avdelingType;

    public String getForholdId() {
        return forholdId;
    }

    public void setForholdId(String forholdId) {
        this.forholdId = forholdId;
    }

    public Boolean getHarSimulering() {
        return harSimulering;
    }

    public void setHarSimulering(Boolean harSimulering) {
        this.harSimulering = harSimulering;
    }

    public Boolean getHarUtlandPensjon() {
        return harUtlandPensjon;
    }

    public void setHarUtlandPensjon(Boolean harUtlandPensjon) {
        this.harUtlandPensjon = harUtlandPensjon;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public Calendar getSamtykkeDato() {
        return samtykkeDato;
    }

    public void setSamtykkeDato(Calendar samtykkeDato) {
        this.samtykkeDato = samtykkeDato;
    }

    public String getSamtykkeSimuleringKode() {
        return samtykkeSimuleringKode;
    }

    public void setSamtykkeSimuleringKode(String samtykkeSimuleringKode) {
        this.samtykkeSimuleringKode = samtykkeSimuleringKode;
    }

    public List<TjenestepensjonsYtelse> getTjenestepensjonYtelseListe() {
        return tjenestepensjonYtelseListe;
    }

    public void setTjenestepensjonYtelseListe(List<TjenestepensjonsYtelse> tjenestepensjonYtelseListe) {
        this.tjenestepensjonYtelseListe = tjenestepensjonYtelseListe;
    }

    public String getTpNr() {
        return tpNr;
    }

    public void setTpNr(String tpNr) {
        this.tpNr = tpNr;
    }

    public String getTssEksternId() {
        return tssEksternId;
    }

    public void setTssEksternId(String tssEksternId) {
        this.tssEksternId = tssEksternId;
    }

    public String getAvdelingType() {
        return avdelingType;
    }

    public void setAvdelingType(String avdelingType) {
        this.avdelingType = avdelingType;
    }

    public no.nav.domain.pensjon.common.Endringsinfo getEndringsInfo() {
        return endringsInfo;
    }

    public void setEndringsInfo(no.nav.domain.pensjon.common.Endringsinfo endringsInfo) {
        this.endringsInfo = endringsInfo;
    }

    public TjenestepensjonSimulering getTjenestepensjonSimulering() {
        return tjenestepensjonSimulering;
    }

    public void setTjenestepensjonSimulering(TjenestepensjonSimulering tjenestepensjonSimulering) {
        this.tjenestepensjonSimulering = tjenestepensjonSimulering;
    }
}

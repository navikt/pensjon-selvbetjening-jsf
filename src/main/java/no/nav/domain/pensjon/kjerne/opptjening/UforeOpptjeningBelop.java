package no.nav.domain.pensjon.kjerne.opptjening;

import java.io.Serializable;

import no.stelvio.domain.time.ChangeStamp;

/**
 * Non-persistent class used to represent UforeOpptjeningBelop. This class is used to map the UforeOpptjeningBelopDto object in
 * the request to service.
 */
public class UforeOpptjeningBelop implements Serializable {

    private static final long serialVersionUID = 4663590584589277673L;
    private Long uforeOpptjeningBelopId;
    private Integer ar;
    private Double belop;
    private ChangeStamp changeStamp;
    private Boolean proRataBeregnetUp;
    private Double poengtall;
    private Integer uforegrad;
    private Double antattInntekt;
    private Double antattInntektProRata;
    private Double andelProrata;
    private Integer poengarTellerProRata;
    private Integer poengarNevnerProRata;
    private Integer antFremtidigArProRata;
    private Double poengAntattArligInntekt;
    private Integer yrkesskadegrad;
    private Double antattInntektYrke;
    private Boolean uforear;
    private Boolean konvertertUFT;
    private Integer veietGrunnbelop;
    private Boolean uforetrygd;
    private Boolean yrkesskade;

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

    public Long getUforeOpptjeningBelopId() {
        return uforeOpptjeningBelopId;
    }

    public void setUforeOpptjeningBelopId(Long uforeOpptjeningBelopId) {
        this.uforeOpptjeningBelopId = uforeOpptjeningBelopId;
    }

    public Boolean getProRataBeregnetUp() {
        return proRataBeregnetUp;
    }

    public void setProRataBeregnetUp(Boolean proRataBeregnetUp) {
        this.proRataBeregnetUp = proRataBeregnetUp;
    }

    public Double getPoengtall() {
        return poengtall;
    }

    public void setPoengtall(Double poengtall) {
        this.poengtall = poengtall;
    }

    public Integer getUforegrad() {
        return uforegrad;
    }

    public void setUforegrad(Integer uforegrad) {
        this.uforegrad = uforegrad;
    }

    public Double getAntattInntektProRata() {
        return antattInntektProRata;
    }

    public void setAntattInntektProRata(Double antattInntektProRata) {
        this.antattInntektProRata = antattInntektProRata;
    }

    public Double getAndelProrata() {
        return andelProrata;
    }

    public void setAndelProrata(Double andelProrata) {
        this.andelProrata = andelProrata;
    }

    public Integer getPoengarTellerProRata() {
        return poengarTellerProRata;
    }

    public void setPoengarTellerProRata(Integer poengarTellerProRata) {
        this.poengarTellerProRata = poengarTellerProRata;
    }

    public Integer getPoengarNevnerProRata() {
        return poengarNevnerProRata;
    }

    public void setPoengarNevnerProRata(Integer poengarNevnerProRata) {
        this.poengarNevnerProRata = poengarNevnerProRata;
    }

    public Integer getAntFremtidigArProRata() {
        return antFremtidigArProRata;
    }

    public void setAntFremtidigArProRata(Integer antFremtidigArProRata) {
        this.antFremtidigArProRata = antFremtidigArProRata;
    }

    public Double getPoengAntattArligInntekt() {
        return poengAntattArligInntekt;
    }

    public void setPoengAntattArligInntekt(Double poengAntattArligInntekt) {
        this.poengAntattArligInntekt = poengAntattArligInntekt;
    }

    public Integer getYrkesskadegrad() {
        return yrkesskadegrad;
    }

    public void setYrkesskadegrad(Integer yrkesskadegrad) {
        this.yrkesskadegrad = yrkesskadegrad;
    }

    public Double getAntattInntektYrke() {
        return antattInntektYrke;
    }

    public void setAntattInntektYrke(Double antattInntektYrke) {
        this.antattInntektYrke = antattInntektYrke;
    }

    public Double getAntattInntekt() {
        return antattInntekt;
    }

    public void setAntattInntekt(Double antattInntekt) {
        this.antattInntekt = antattInntekt;
    }

    public Boolean getUforear() {
        return uforear;
    }

    public void setUforear(Boolean uforear) {
        this.uforear = uforear;
    }

    public Boolean getKonvertertUFT() {
        return konvertertUFT;
    }

    public void setKonvertertUFT(Boolean konvertertUFT) {
        this.konvertertUFT = konvertertUFT;
    }

    public Integer getVeietGrunnbelop() {
        return veietGrunnbelop;
    }

    public void setVeietGrunnbelop(Integer veietGrunnbelop) {
        this.veietGrunnbelop = veietGrunnbelop;
    }

    public Boolean getUforetrygd() {
        return uforetrygd;
    }

    public void setUforetrygd(Boolean uforetrygd) {
        this.uforetrygd = uforetrygd;
    }

    public Boolean getYrkesskade() {
        return yrkesskade;
    }

    public void setYrkesskade(Boolean yrkesskade) {
        this.yrkesskade = yrkesskade;
    }
}

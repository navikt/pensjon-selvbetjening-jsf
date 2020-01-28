package no.nav.domain.pensjon.common.samhandler;

import java.io.Serializable;
import java.util.List;

public class Samhandler implements Serializable {

    private static final long serialVersionUID = 9180151364719528826L;
    private String navn;
    private String sprak;
    private String samhandlerType;
    private String offentligId;
    private String idType;
    private List<Avdeling> avdelinger;
    private List<AlternativeIder> alternativeIder;

    public List<Avdeling> getAvdelinger() {
        return avdelinger;
    }

    public void setAvdelinger(List<Avdeling> avdelinger) {
        this.avdelinger = avdelinger;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getOffentligId() {
        return offentligId;
    }

    public void setOffentligId(String offentligId) {
        this.offentligId = offentligId;
    }

    public String getSamhandlerType() {
        return samhandlerType;
    }

    public void setSamhandlerType(String samhandlerType) {
        this.samhandlerType = samhandlerType;
    }

    public String getSprak() {
        return sprak;
    }

    public void setSprak(String sprak) {
        this.sprak = sprak;
    }

    public List<AlternativeIder> getAlternativeIder() {
        return alternativeIder;
    }

    public void setAlternativeIder(List<AlternativeIder> alternativeIder) {
        this.alternativeIder = alternativeIder;
    }
}

package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class BehovKode implements Serializable {

    private static final long serialVersionUID = 1L;
    private String spesielleBehovKode;
    private String spesielleBehovBeskrivelse;

    public String getSpesielleBehovBeskrivelse() {
        return spesielleBehovBeskrivelse;
    }

    public void setSpesielleBehovBeskrivelse(String spesielleBehovBeskrivelse) {
        this.spesielleBehovBeskrivelse = spesielleBehovBeskrivelse;
    }

    public String getSpesielleBehovKode() {
        return spesielleBehovKode;
    }

    public void setSpesielleBehovKode(String spesielleBehovKode) {
        this.spesielleBehovKode = spesielleBehovKode;
    }
}

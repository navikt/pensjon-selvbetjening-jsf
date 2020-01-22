package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class TPSSvarStatus implements Serializable {

    private static final long serialVersionUID = 6603180292496184693L;
    private String statusKode;
    private String meldingKode;
    private String meldingBeskrivelse;

    public String getStatusKode() {
        return statusKode;
    }

    public void setStatusKode(String statusKode) {
        this.statusKode = statusKode;
    }

    public String getMeldingKode() {
        return meldingKode;
    }

    public void setMeldingKode(String meldingKode) {
        this.meldingKode = meldingKode;
    }

    public String getMeldingBeskrivelse() {
        return meldingBeskrivelse;
    }

    public void setMeldingBeskrivelse(String meldingBeskrivelse) {
        this.meldingBeskrivelse = meldingBeskrivelse;
    }
}

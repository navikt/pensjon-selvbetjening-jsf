package no.nav.service.pensjon.avvik;

/**
 * Response object for <b>TPEN911 - kategoriserAvvik</b>.
 */
public class KategoriserAvvikResponse {
    private Long avviksinfoId;
    private String meldingTilBruker;
    private String status;

    public KategoriserAvvikResponse(Long avviksinfoId, String meldingTilBruker, String status) {
        this.avviksinfoId = avviksinfoId;
        this.meldingTilBruker = meldingTilBruker;
        this.status = status;
    }

    public Long getAvviksinfoId() {
        return avviksinfoId;
    }

    public String getMeldingTilBruker() {
        return meldingTilBruker;
    }

    public String getStatus() {
        return status;
    }
}

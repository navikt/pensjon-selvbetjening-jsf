package no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2;

public class FinnNAVKontorRequest {

    private String geografiskTilknytning;
    private String diskresjonskode;

    public FinnNAVKontorRequest(String geografiskTilknytning, String diskresjonskode) {
        this.geografiskTilknytning = geografiskTilknytning;
        this.diskresjonskode = diskresjonskode;
    }

    public String getGeografiskTilknytning() {
        return geografiskTilknytning;
    }

    public String getDiskresjonskode() {
        return diskresjonskode;
    }
}

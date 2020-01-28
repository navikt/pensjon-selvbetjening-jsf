package no.nav.consumer.pensjon.pen.person3.to;

public class HentGeografiskTilknytningResponse {
    private String geografiskTilknytning;
    private String diskresjonskoder;

    public HentGeografiskTilknytningResponse() {
    }

    public HentGeografiskTilknytningResponse(String geografiskTilknytning, String diskresjonskoder) {
        this.geografiskTilknytning = geografiskTilknytning;
        this.diskresjonskoder = diskresjonskoder;
    }

    public String getGeografiskTilknytning() {
        return geografiskTilknytning;
    }

    public void setGeografiskTilknytning(String geografiskTilknytning) {
        this.geografiskTilknytning = geografiskTilknytning;
    }

    public String getDiskresjonskoder() {
        return diskresjonskoder;
    }

    public void setDiskresjonskoder(String diskresjonskoder) {
        this.diskresjonskoder = diskresjonskoder;
    }
}

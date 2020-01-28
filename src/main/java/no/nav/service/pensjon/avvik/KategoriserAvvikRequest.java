package no.nav.service.pensjon.avvik;

import java.util.Collections;
import java.util.Map;

/**
 * Request object for <b>TPEN911 - kategoriserAvvik</b>.
 */
public class KategoriserAvvikRequest {
    private final Throwable exception;
    private final Map<String, String> kontekstData;
    private final Map<String, String> serverData;
    private final String applikasjon;

    public KategoriserAvvikRequest(Throwable exception, Map<String, String> kontekstData, Map<String, String> serverData, String applikasjon) {
        this.exception = exception;
        this.kontekstData = kontekstData;
        this.serverData = serverData;
        this.applikasjon = applikasjon;
    }

    public KategoriserAvvikRequest(Throwable exception, String applikasjon) {
        this.exception = exception;
        this.kontekstData = null;
        this.serverData = null;
        this.applikasjon = applikasjon;
    }

    public String getApplikasjon() {
        return applikasjon;
    }

    public Throwable getException() {
        return exception;
    }

    public Map<String, String> getKontekstData() {
        return kontekstData != null ? Collections.unmodifiableMap(kontekstData) : null;
    }

    public Map<String, String> getServerData() {
        return serverData != null ? Collections.unmodifiableMap(serverData) : null;
    }
}

package no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.decorator;

import no.nav.innholdshenter.common.EnonicContentRetriever;
import no.nav.innholdshenter.tools.InnholdshenterTools;

import javax.annotation.PostConstruct;

/**
 * Wrapper to circumvent the EnonicContentRetriever's baseUrl having an extra '/' appended to the end.
 * This caused the URL to the decorator resource to be unreachable.
 */
public class PENonicContentRetriever extends EnonicContentRetriever {

    private String baseUrl;

    public PENonicContentRetriever(int httpTimeoutMillis) {
        super(httpTimeoutMillis);
    }

    @Override
    public String getPageContent(String path) {
        return super.getPageContentFullUrl(concatUrl(path));
    }

    private String concatUrl(String path) {
        return InnholdshenterTools.sanitizeUrlCacheKey(baseUrl + path);
    }

    @Override
    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @PostConstruct
    public void configureSSL() {
        //TODO Restore SSL
        //SSLConnectionSocketFactory sslConnectionSocketFactory = SSLConnectionSocketFactory.getSystemSocketFactory();
        //HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
        //setHttpClient(httpClient);
    }
}

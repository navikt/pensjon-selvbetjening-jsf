package no.nav.pensjon.selv.config;

import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.decorator.PENonicContentRetriever;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ContentContext {
    static PENonicContentRetriever peNonicContentRetriever;

    //TODO wrong place?
    @Bean //(name = "todo.contentRetriever")
    public PENonicContentRetriever contentRetriever() {
        return peNonicContentRetriever == null ? (peNonicContentRetriever = contentRetriever1()) : peNonicContentRetriever;
    }

    private PENonicContentRetriever contentRetriever1() {
        PENonicContentRetriever retriever = new PENonicContentRetriever(10000); //TODO ${appres.cms.httpTimeoutMillis}
        retriever.setBaseUrl("https://appres-t4.nav.no/"); // ${appres.cms.url}
//        retriever.setCacheManager(ehCacheManagerFactoryBean);
//        retriever.setCacheManager(CacheManager.create()); //TODO cns.pselv.ehcacheManager (ehCacheManagerFactoryBean)
        retriever.setRefreshIntervalSeconds(60000); // ${appres.cms.refreshIntervalSeconds}
        return retriever;
    }

//    //TODO wrong place?
//    @Bean //(name = "todo.contentRetriever")
//    public PENonicContentRetriever contentRetriever() {
//        PENonicContentRetriever retriever = new PENonicContentRetriever(10000); //TODO ${appres.cms.httpTimeoutMillis}
//        retriever.setBaseUrl("https://appres-t4.nav.no/"); // ${appres.cms.url}
////        retriever.setCacheManager(ehCacheManagerFactoryBean);
////        retriever.setCacheManager(CacheManager.create()); //TODO cns.pselv.ehcacheManager (ehCacheManagerFactoryBean)
//        retriever.setRefreshIntervalSeconds(60000); // ${appres.cms.refreshIntervalSeconds}
//        return retriever;
//    }
}

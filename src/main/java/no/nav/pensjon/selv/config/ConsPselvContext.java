package no.nav.pensjon.selv.config;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.pensjon.selv.service.fake.FakePersonService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Replaces cons-pselv-context.xml.
 */
@Configuration
public class ConsPselvContext {

    @Bean(name = "personBi")
    public PersonServiceBi personService() {
        return new FakePersonService();
    }
}

package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FakeContext {

    @Bean(name = "personBi")
    public PersonServiceBi personService() {
        return new FakePersonService();
    }
}

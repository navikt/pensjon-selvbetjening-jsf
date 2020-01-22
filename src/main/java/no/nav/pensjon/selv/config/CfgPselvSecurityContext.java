package no.nav.pensjon.selv.config;

import no.nav.pensjon.selv.service.fake.FakeLogoutService;
import no.stelvio.presentation.security.logout.LogoutService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Replaces cfg-pselv-security-context.xml.
 */
@Configuration
public class CfgPselvSecurityContext {

    @Bean(name = "cfg.security.tilgangNektetService")
    public LogoutService logoutService() {
        return new FakeLogoutService();
    }
}

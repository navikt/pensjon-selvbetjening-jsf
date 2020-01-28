package no.nav.repository.pensjon.avvik;

import java.util.List;

import no.nav.domain.pensjon.avvik.HentAvvikFilter;
import no.nav.domain.pensjon.kjerne.avvik.AvvikFilter;
import no.nav.domain.pensjon.kjerne.avvik.Avviksgruppe;
import no.nav.domain.pensjon.kjerne.avvik.Avviksinformasjon;

public interface AvvikRepository {

    List<Avviksinformasjon> hentAvvik(HentAvvikFilter filter);

    Avviksinformasjon hentAvviksinformasjon(Long id);

    List<Avviksgruppe> hentAvviksgrupper(String hash, String hashRedusert);

    void saveOrUpdateAvviksinformasjon(Avviksinformasjon avviksinformasjon);

    List<AvvikFilter> hentAvvikFilter(String applikasjon);
}
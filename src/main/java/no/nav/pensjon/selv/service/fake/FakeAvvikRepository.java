package no.nav.pensjon.selv.service.fake;

import no.nav.domain.pensjon.avvik.HentAvvikFilter;
import no.nav.domain.pensjon.kjerne.avvik.AvvikFilter;
import no.nav.domain.pensjon.kjerne.avvik.Avviksgruppe;
import no.nav.domain.pensjon.kjerne.avvik.Avviksinformasjon;
import no.nav.repository.pensjon.avvik.AvvikRepository;

import java.util.ArrayList;
import java.util.List;

public class FakeAvvikRepository implements AvvikRepository {

    @Override
    public List<Avviksinformasjon> hentAvvik(HentAvvikFilter filter) {
        return new ArrayList<>();
    }

    @Override
    public Avviksinformasjon hentAvviksinformasjon(Long id) {
        return new Avviksinformasjon();
    }

    @Override
    public List<Avviksgruppe> hentAvviksgrupper(String hash, String hashRedusert) {
        return new ArrayList<>();
    }

    @Override
    public void saveOrUpdateAvviksinformasjon(Avviksinformasjon avviksinformasjon) {
    }

    @Override
    public List<AvvikFilter> hentAvvikFilter(String applikasjon) {
        return new ArrayList<>();
    }
}

package no.nav.presentation.pensjon.pselv.simulering.personopplysninger.support;

import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;

import java.util.Optional;

public class Uforeberegninger {
    public void addBeregningerFor(Vedtak vedtak) {

    }

    public Optional<Integer> getGjeldendeUforegrad() {
        return Optional.of(10);
    }
}

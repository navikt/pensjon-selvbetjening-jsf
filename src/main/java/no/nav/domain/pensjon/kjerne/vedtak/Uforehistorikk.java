package no.nav.domain.pensjon.kjerne.vedtak;

import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.kodetabeller.UforeTypeCti;

import java.util.Date;

public class Uforehistorikk {
    public void setPenPerson(PenPerson penPerson) {

    }

    public Uforeperiode hentUforeperiodeMedGyldigUforegradFor(Date dato, UforeTypeCti[] uforetyper) {
        return new Uforeperiode();
    }
}

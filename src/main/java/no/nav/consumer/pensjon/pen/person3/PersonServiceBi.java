package no.nav.consumer.pensjon.pen.person3;

import no.nav.consumer.pensjon.pen.person3.to.HentEkteskapshistorikkRequest;
import no.nav.consumer.pensjon.pen.person3.to.HentEkteskapshistorikkResponse;
import no.nav.consumer.pensjon.pen.person3.to.HentGeografiskTilknytningRequest;
import no.nav.consumer.pensjon.pen.person3.to.HentGeografiskTilknytningResponse;
import no.nav.consumer.pensjon.pen.person3.to.HentPersonerMedSammeAdresseRequest;
import no.nav.consumer.pensjon.pen.person3.to.HentPersonerMedSammeAdresseResponse;
//import no.nav.tjeneste.virksomhet.person.v3.HentGeografiskTilknytningPersonIkkeFunnet;
//import no.nav.tjeneste.virksomhet.person.v3.HentGeografiskTilknytningSikkerhetsbegrensing;
//import no.nav.tjeneste.virksomhet.person.v3.HentPersonerMedSammeAdresseIkkeFunnet;

public interface PersonServiceBi {
    HentGeografiskTilknytningResponse hentGeografiskTilknytning(HentGeografiskTilknytningRequest hentGeografiskTilknytningRequest);
//            throws HentGeografiskTilknytningSikkerhetsbegrensing, HentGeografiskTilknytningPersonIkkeFunnet;

    HentEkteskapshistorikkResponse hentEkteskapshistorikk(HentEkteskapshistorikkRequest hentEkteskapshistorikkRequest);

    HentPersonerMedSammeAdresseResponse hentPersonerMedSammeAdresse(HentPersonerMedSammeAdresseRequest hentPersonerMedSammeAdresseRequest);
//            throws HentPersonerMedSammeAdresseIkkeFunnet;
}

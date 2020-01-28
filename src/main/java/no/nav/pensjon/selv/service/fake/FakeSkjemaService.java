package no.nav.pensjon.selv.service.fake;

import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SkjemaStatusCti;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.skjema.SkjemaInnledning;
import no.nav.service.pensjon.skjema.*;
import no.stelvio.domain.time.ChangeStamp;

import java.util.ArrayList;

import static no.nav.pensjon.selv.service.fake.FakeCodesTableRepository.setDecode;

public class FakeSkjemaService implements SkjemaServiceBi {

    @Override
    public HentSkjemaListeResponse hentSkjemaListe(HentSkjemaListeRequest request) {
        ArrayList<Skjema> skjemaListe = new ArrayList<>();
        skjemaListe.add(newSkjema());
        return new HentSkjemaListeResponse(skjemaListe);
    }

    @Override
    public HentSkjemaResponse hentSkjema(HentSkjemaRequest request) {
        return new HentSkjemaResponse(newSkjema());
    }

    @Override
    public HentSkjemaAfpPrivatResponse hentSkjemaAfpPrivat(HentSkjemaAfpPrivatRequest request) {
        return new HentSkjemaAfpPrivatResponse();
    }

    @Override
    public LagreSkjemaResponse lagreSkjema(LagreSkjemaRequest request) {
        return new LagreSkjemaResponse(newSkjema());
    }

    @Override
    public SlettSkjemaResponse slettSkjema(SlettSkjemaRequest request) {
        return new SlettSkjemaResponse();
    }

    @Override
    public SendSkjemaResponse sendSkjema(SendSkjemaRequest request) {
        return new SendSkjemaResponse();
    }

    @Override
    public SendBrevOmsorgspoengResponse sendBrevOmsorgspoeng(SendBrevOmsorgspoengRequest request) {
        return new SendBrevOmsorgspoengResponse();
    }

    @Override
    public OpprettKravForEndretUttaksgradResponse opprettKravForEndretUttaksgrad(OpprettKravForEndretUttaksgradRequest request) {
        return new OpprettKravForEndretUttaksgradResponse();
    }

    private static Skjema newSkjema() {
        Skjema skjema = new Skjema();
        skjema.setSkjemaId(321L);
        skjema.setSkjemaPselvStatus(newSkjemaStatusCti());
        skjema.setSkjemaPselvType(newElektroniskSkjemaTypeCti());
        skjema.setSkjemaInnledning(newSkjemaInnledning());
        skjema.setChangeStamp(new ChangeStamp("ChangeStamp-user"));
        return skjema;
    }

    private static SkjemaInnledning newSkjemaInnledning() {
        SkjemaInnledning innledning = new SkjemaInnledning();
        innledning.setBucId("BUC-ID");
        return innledning;
    }

    private static ElektroniskSkjemaTypeCti newElektroniskSkjemaTypeCti() {
        ElektroniskSkjemaTypeCti cti = new ElektroniskSkjemaTypeCti();
        cti.setCodeAsString("AP");
        setDecode(cti);
        return cti;
    }

    private static SkjemaStatusCti newSkjemaStatusCti() {
        SkjemaStatusCti cti = new SkjemaStatusCti();
        cti.setCodeAsString("OPPRETTET");
        setDecode(cti);
        return cti;
    }
}

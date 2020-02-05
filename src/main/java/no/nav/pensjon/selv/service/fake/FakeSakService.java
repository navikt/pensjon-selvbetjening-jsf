package no.nav.pensjon.selv.service.fake;

import no.nav.domain.pensjon.kjerne.Applikasjonsparameter;
import no.nav.domain.pensjon.kjerne.exception.PEN029PersonIkkeFunnetLokaltException;
import no.nav.domain.pensjon.kjerne.exception.PEN031SakManglerEierenhetException;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.service.pensjon.sak.*;
import no.stelvio.domain.time.ChangeStamp;

import java.util.ArrayList;
import java.util.List;

import static no.nav.pensjon.selv.service.fake.FakeCodesTableRepository.setDecode;

public class FakeSakService implements SakServiceBi {

    @Override
    public HentSakListeResponse hentSakListe(HentSakListeRequest request) {
        List<Sak> saker = new ArrayList<>();
        saker.add(newSak());  // hvis bruker har en eksisterende sak (løpende/til behandling)
        return new HentSakListeResponse(saker);
    }

    @Override
    public HentApplikasjonsparameterResponse hentApplikasjonsparameter(HentApplikasjonsparameterRequest hentApplikasjonsparameterRequest) {
        HentApplikasjonsparameterResponse response = new HentApplikasjonsparameterResponse();
        response.setApplikasjonsparameter(new Applikasjonsparameter());
        return response;
    }

    @Override
    public HentSakSammendragListeResponse hentSakSammendragListe(HentSakSammendragListeRequest request) throws PEN029PersonIkkeFunnetLokaltException, PEN031SakManglerEierenhetException {
        return new HentSakSammendragListeResponse(new ArrayList<>());
    }

    static Sak newSak() {
        List<KravHode> kravHoder = new ArrayList<>();
        KravHode kravHode = new KravHode();
        kravHoder.add(kravHode);
        Sak sak = new Sak(status("LOPENDE"), type("ALDER"), kravHoder);
        sak.setChangeStamp(new ChangeStamp("ch-u"));
        kravHode.setSak(sak);
        return sak;
    }

    private static SakTypeCti type(String value) {
        SakTypeCti cti = new SakTypeCti();
        cti.setCodeAsString(value);
        setDecode(cti);
        return cti;
    }

    private static SakStatusCti status(String value) {
        SakStatusCti cti = new SakStatusCti();
        cti.setCodeAsString(value);
        setDecode(cti);
        return cti;
    }
}

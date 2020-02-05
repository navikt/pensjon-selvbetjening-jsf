package no.nav.service.pensjon.sak;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.sak.SakSammendrag;

public class HentSakSammendragListeResponse extends ServiceResponse {

    private static final long serialVersionUID = 619055485339959548L;
    private List<SakSammendrag> sakSammendragListe;

    public HentSakSammendragListeResponse(List<SakSammendrag> sakSammendragListe) {
        this.sakSammendragListe = sakSammendragListe;
    }

    public List<SakSammendrag> getSakSammendragListe() {
        return sakSammendragListe;
    }
}

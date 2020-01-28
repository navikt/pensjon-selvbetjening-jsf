package no.nav.service.pensjon.grunnlag;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.grunnlag.SatsResultat;

public class HentGrunnbelopListeResponse extends ServiceResponse {

    private static final long serialVersionUID = -7808801711144708452L;
    private List<SatsResultat> satsResultatList;

    public List<SatsResultat> getSatsResultatList() {
        return satsResultatList;
    }

    public void setSatsResultatList(List<SatsResultat> satsResultatList) {
        this.satsResultatList = satsResultatList;
    }
}

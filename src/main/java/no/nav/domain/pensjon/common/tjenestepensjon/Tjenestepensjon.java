package no.nav.domain.pensjon.common.tjenestepensjon;

import static org.apache.commons.collections.CollectionUtils.isEmpty;
import static org.apache.commons.lang.BooleanUtils.isTrue;

import java.io.Serializable;
import java.util.List;

import no.nav.domain.pensjon.common.Endringsinfo;

/**
 * Mirror domain object for ESB domin - ASBOPenTjenestepensjon.
 */
public class Tjenestepensjon implements Serializable {

    private static final long serialVersionUID = -3676934239161665900L;
    private String fnr;
    private String personId;
    private List<TjenestepensjonForhold> tjenestepensjonForholdene;
    private Endringsinfo endringsInfo;

    public Endringsinfo getEndringsInfo() {
        return endringsInfo;
    }

    public void setEndringsInfo(Endringsinfo endringsInfo) {
        this.endringsInfo = endringsInfo;
    }

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public List<TjenestepensjonForhold> getTjenestepensjonForholdene() {
        return tjenestepensjonForholdene;
    }

    public void setTjenestepensjonForholdene(List<TjenestepensjonForhold> tjenestepensjonForholdene) {
        this.tjenestepensjonForholdene = tjenestepensjonForholdene;
    }

    public boolean hasUtlandPensjon() {
        if (isEmpty(tjenestepensjonForholdene)) {
            return false;
        }

        for (TjenestepensjonForhold tjenestepensjonForhold : tjenestepensjonForholdene) {
            if (isTrue(tjenestepensjonForhold.getHarUtlandPensjon())) {
                return true;
            }
        }

        return false;
    }
}

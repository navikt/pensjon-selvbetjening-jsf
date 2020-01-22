/**
 * Utbetalingsinformasjon.java
 * <p>
 * This file was auto-generated from WSDL
 * by the IBM Web services WSDL2Java emitter.
 * cf10631.06 v81706232132
 */

package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class Utbetalingsinformasjon implements Serializable {

    private static final long serialVersionUID = -1479418648162687403L;
    private String utbetalingsType;
    private NorskKonto norskKonto;
    private Utlandskonto utlandsKonto;

    public NorskKonto getNorskKonto() {
        return norskKonto;
    }

    public void setNorskKonto(NorskKonto norskKonto) {
        this.norskKonto = norskKonto;
    }

    public String getUtbetalingsType() {
        return utbetalingsType;
    }

    public void setUtbetalingsType(String utbetalingsType) {
        this.utbetalingsType = utbetalingsType;
    }

    public Utlandskonto getUtlandsKonto() {
        return utlandsKonto;
    }

    public void setUtlandsKonto(Utlandskonto utlandsKonto) {
        this.utlandsKonto = utlandsKonto;
    }
}

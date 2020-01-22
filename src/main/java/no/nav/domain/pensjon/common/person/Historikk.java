package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.List;

public class Historikk implements Serializable {

    private static final long serialVersionUID = 5724025483441516694L;
    private List<BostedsAdresse> bostedsadresser;
    private List<AnnenAdresse> adresseLinjer;
    private List<HistoriskFnr> historiskeFnr;
    private List<NavnEndring> navnEndringer;

    public List<AnnenAdresse> getAdresseLinjer() {
        return adresseLinjer;
    }

    public void setAdresseLinjer(List<AnnenAdresse> adresseLinjer) {
        this.adresseLinjer = adresseLinjer;
    }

    public List<BostedsAdresse> getBostedsadresser() {
        return bostedsadresser;
    }

    public void setBostedsadresser(List<BostedsAdresse> bostedsadresser) {
        this.bostedsadresser = bostedsadresser;
    }

    public List<HistoriskFnr> getHistoriskeFnr() {
        return historiskeFnr;
    }

    public void setHistoriskeFnr(List<HistoriskFnr> historiskeFnr) {
        this.historiskeFnr = historiskeFnr;
    }

    public List<NavnEndring> getNavnEndringer() {
        return navnEndringer;
    }

    public void setNavnEndringer(List<NavnEndring> navnEndringer) {
        this.navnEndringer = navnEndringer;
    }
}

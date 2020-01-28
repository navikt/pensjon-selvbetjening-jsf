package no.nav.domain.pensjon.kjerne;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import no.nav.domain.AbstractPersistentDomainObject;

/**
 * Styringsparamtere for PESYS.
 */
//@Entity
//@Table(name = "T_APPL_PARAMETER")
//@NamedQueries({@NamedQuery(name = "Applikasjonsparameter.findApplikasjonsparameterByName", query = "select u from Applikasjonsparameter u where u.navn = :name")})
public class Applikasjonsparameter extends AbstractPersistentDomainObject {

    private static final long serialVersionUID = -8878034165278469776L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "APPL_PARAMETER_ID")
    private Long applikasjonsparameterId;

    //@Column(name = "NAVN")
    private String navn;

    //@Column(name = "VERDI")
    private String verdi;

    public Long getApplikasjonsparameterId() {
        return applikasjonsparameterId;
    }

    public void setApplikasjonsparameterId(Long applikasjonsparameterId) {
        this.applikasjonsparameterId = applikasjonsparameterId;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }

    public String getVerdi() {
        return verdi;
    }

    public void setVerdi(String verdi) {
        this.verdi = verdi;
    }
}

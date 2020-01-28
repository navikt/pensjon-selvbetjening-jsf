package no.nav.domain.pensjon.kjerne.sak;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

//import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.kjerne.krav.KravHode;

/**
 * Domeneobjekt som inneholder informasjon om uttaksgraden for bruker. NB: Må
 * ikke forveksles med {no.nav.domain.pensjon.kjerne.simulering.Uttaksgrad}, så egentlig skal
 * hete PSELVUttakgrad.
 */
//@Entity
//@Table(name = "T_UTTAKSGRAD")
//public class Uttaksgrad extends AbstractVersionedPersistentDomainObject implements PeriodisertInformasjon {
public class Uttaksgrad implements PeriodisertInformasjon, Serializable {
    private static final long serialVersionUID = 34652788L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "UTTAKSGRAD_ID")
    private Long uttaksgradId;

    //@Column(name = "DATO_VIRK_FOM", nullable = false)
    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date fomDato;

    //@Column(name = "DATO_VIRK_TOM", nullable = true)
    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date tomDato;

    //@Column(name = "UTTAKSGRAD", nullable = false)
    private Integer uttaksgrad;

    /**
     * Markerer at uttaksgrad er kopiert inn i tabellen
     */
    //@Column(name = "UTTAKSGRADKOPIERT", nullable = true)
    private Boolean uttaksgradKopiert;

    // Inverse mapping
    //@ManyToOne()
    //@JoinColumn(name = "KRAVHODE_ID")
    private KravHode kravHode;

    // Inverse mapping
    //@ManyToOne()
    //@JoinColumn(name = "SAK_ID")
    private Sak sak;

    public Uttaksgrad() {
    }

    /**
     * Copy constructor. Reference to krav and sak is not copied
     */
    public Uttaksgrad(Uttaksgrad src) {
        if (src.getFomDato() != null) {
            setFomDato(new Date(src.getFomDato().getTime()));
        }

        if (src.getTomDato() != null) {
            setTomDato(new Date(src.getTomDato().getTime()));
        }

        setUttaksgrad(src.getUttaksgrad());
    }

    @Override
    public Date getFomDato() {
        return fomDato;
    }

    @Override
    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    public KravHode getKravHode() {
        return kravHode;
    }

    public void setKravHode(KravHode kravHode) {
        this.kravHode = kravHode;
    }

    @Override
    public Date getTomDato() {
        return tomDato;
    }

    @Override
    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato;
    }

    public Integer getUttaksgrad() {
        return uttaksgrad;
    }

    public void setUttaksgrad(Integer uttaksgrad) {
        this.uttaksgrad = uttaksgrad;
    }

    public Long getUttaksgradId() {
        return uttaksgradId;
    }

    public void setUttaksgradId(Long uttaksgradId) {
        this.uttaksgradId = uttaksgradId;
    }

    public Sak getSak() {
        return sak;
    }

    public void setSak(Sak sak) {
        this.sak = sak;
    }

    public Boolean getUttaksgradKopiert() {
        return uttaksgradKopiert;
    }

    public void setUttaksgradKopiert(Boolean uttaksgradKopiert) {
        this.uttaksgradKopiert = uttaksgradKopiert;
    }
}

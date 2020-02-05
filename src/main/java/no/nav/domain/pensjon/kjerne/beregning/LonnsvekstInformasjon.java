package no.nav.domain.pensjon.kjerne.beregning;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import no.nav.domain.AbstractVersionedPersistentDomainObject;

/**
 * Informasjon i forbindelse med regulering.
 */
//@Entity
//@Table(name = "T_LONNSVEKST_INFO")
public class LonnsvekstInformasjon extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = 1682957175583824771L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "LONNSVEKST_INFO_ID")
    private Long lonnsvekstInfoId;

    /**
     * Lønnsveksten (sats) benyttet ved regulering av verdi til opptjening.
     */
    @Column(name = "LONNSVEKST")
    private Double lonnsvekst;

    @Column(name = "REGULERING_DATO")
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date reguleringsDato;

    @Column(name = "UTTAKSGRAD_V_REG")
    private Integer uttaksgradVedRegulering;

    public LonnsvekstInformasjon() {
        super();
    }

    public LonnsvekstInformasjon(LonnsvekstInformasjon info) {
        if (info == null) {
            return;
        }

        setLonnsvekst(info.getLonnsvekst());
        setReguleringsDato(info.getReguleringsDato());
        setUttaksgradVedRegulering(info.getUttaksgradVedRegulering());
    }

    public Double getLonnsvekst() {
        return lonnsvekst;
    }

    public void setLonnsvekst(Double lonnsvekst) {
        this.lonnsvekst = lonnsvekst;
    }

    public Long getLonnsvekstInfoId() {
        return lonnsvekstInfoId;
    }

    public void setLonnsvekstInfoId(Long lonnsvekstInfoId) {
        this.lonnsvekstInfoId = lonnsvekstInfoId;
    }

    public Date getReguleringsDato() {
        return reguleringsDato;
    }

    public void setReguleringsDato(Date reguleringsDato) {
        this.reguleringsDato = reguleringsDato;
    }

    public Integer getUttaksgradVedRegulering() {
        return uttaksgradVedRegulering;
    }

    public void setUttaksgradVedRegulering(Integer uttaksgradVedRegulering) {
        this.uttaksgradVedRegulering = uttaksgradVedRegulering;
    }
}

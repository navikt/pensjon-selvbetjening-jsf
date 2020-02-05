package no.nav.domain.pensjon.kjerne.grunnlag;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import no.nav.domain.AbstractVersionedPersistentDomainObject;

/**
 * Dette objektet representerer detaljer knyttet til en opptjening av type uføre med yrkesskade.
 * Er null dersom det ikke foreligger en yrkesskade.
 */
//@Entity
//@Table(name = "T_YRKSK_OPPTJENING")
public class Yrkesskadeopptjening extends AbstractVersionedPersistentDomainObject {

    private static final long serialVersionUID = 1820289764506397302L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "YRKESSKADE_OPPTJN_ID")
    private Long yrkesskadeopptjeningId;

    /**
     * Poeng etter antatt årlig inntekt.
     */
    @Column(name = "PAA")
    private Double paa;

    /**
     * Yrkesskadegrad.
     */
    @Column(name = "YUG")
    private Integer yug;

    /**
     * Antatt Inntekt for yrkesskade.
     */
    @Column(name = "ANTATT_INNTEKT_YRKE")
    private Double antattInntektYrke;

    public Yrkesskadeopptjening() {
        super();
    }

    public Yrkesskadeopptjening(Yrkesskadeopptjening opptjening) {
        if (opptjening == null) {
            return;
        }

        setPaa(opptjening.getPaa());
        setYug(opptjening.getYug());
        setAntattInntektYrke(opptjening.getAntattInntektYrke());
    }

    public Double getAntattInntektYrke() {
        return antattInntektYrke;
    }

    public void setAntattInntektYrke(Double antattInntektYrke) {
        this.antattInntektYrke = antattInntektYrke;
    }

    public Double getPaa() {
        return paa;
    }

    public void setPaa(Double paa) {
        this.paa = paa;
    }

    public Long getYrkesskadeopptjeningId() {
        return yrkesskadeopptjeningId;
    }

    public void setYrkesskadeopptjeningId(Long yrkesskadeopptjeningId) {
        this.yrkesskadeopptjeningId = yrkesskadeopptjeningId;
    }

    public Integer getYug() {
        return yug;
    }

    public void setYug(Integer yug) {
        this.yug = yug;
    }
}

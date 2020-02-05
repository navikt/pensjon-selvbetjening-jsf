package no.nav.domain.pensjon.kjerne.grunnlag;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.kjerne.beregning2011.Beregning2011;

//@Entity
//@DiscriminatorValue("PEN_B")
public class Pensjonsbeholdning extends Beholdning implements PeriodisertInformasjon {
    private static final long serialVersionUID = 7461455666504186252L;

    public Pensjonsbeholdning() {
    }

    /**
     * Copy constructor. Copy the src object except ChangeStamp, versjon and beholdningId. <b>persongrunnlag will not be copied.
     * However, the copy constructor of Persongrunnlag will copy Beholdning of this type.
     */
    public Pensjonsbeholdning(Beholdning src) {
        copyFields(src);
    }

    @Override
    public Beholdning copy() {
        Pensjonsbeholdning copy = new Pensjonsbeholdning();
        copy.copyFields(this);
        return copy;
    }

    /**
     * Hibernate workaround - always return null
     */
    @Override
    public Beregning2011 getBeregning2011() {
        return null;
    }
}

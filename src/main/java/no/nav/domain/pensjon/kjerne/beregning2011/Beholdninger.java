package no.nav.domain.pensjon.kjerne.beregning2011;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.grunnlag.Beholdning;
import no.nav.domain.pensjon.kjerne.grunnlag.Pensjonsbeholdning;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeholdningsTypeCode;

//@Entity
//@Table(name = "T_BEHOLDNINGER")
public class Beholdninger extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = -9201842530351739648L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BEHOLDNINGER_ID", nullable = false)
    private Long beholdningerId;

    @OneToMany(mappedBy = "beholdninger", fetch = FetchType.LAZY)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Beholdning> beholdningliste = new ArrayList<Beholdning>();

    public List<Beholdning> getBeholdninger() {
        return beholdningliste;
    }

    /**
     * Convenience method for retrieval of Beholdning-entries with a positive totalbelop
     */
    public List<Beholdning> findBeholdningerHavingPositiveTotalBelop() {
        return getBeholdninger().stream()
                .filter(input -> input.getTotalbelop() != null && input.getTotalbelop() > 0)
                .collect(Collectors.toList());
    }

    public void setBeholdninger(List<Beholdning> beholdninger) {
        beholdningliste = beholdninger;
    }

    public Long getBeholdningerId() {
        return beholdningerId;
    }

    public void setBeholdningerId(Long beholdningerId) {
        this.beholdningerId = beholdningerId;
    }

    public void addBeholdning(Beholdning beholdning) {
        if (beholdning == null) {
            return;
        }

        beholdningliste.add(beholdning);
        beholdning.setBeholdninger(this);
    }

    public Beholdning findBeholdningAvType(final BeholdningsTypeCode beholdningsTypeCode) {
        return getBeholdninger().stream()
                .filter(input -> beholdningsTypeCode == input.getBeholdningsType())
                .findAny()
                .orElse(null);
    }

    public List<Pensjonsbeholdning> getPensjonsbeholdninger() {
        List<Pensjonsbeholdning> pensjonsbeholdningList = new ArrayList<>();

        for (Beholdning beholdning : getBeholdninger()) {
            if (beholdning instanceof Pensjonsbeholdning) {
                pensjonsbeholdningList.add((Pensjonsbeholdning) beholdning);
            }
        }

        return pensjonsbeholdningList;
    }
}

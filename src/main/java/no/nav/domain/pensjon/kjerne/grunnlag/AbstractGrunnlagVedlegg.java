package no.nav.domain.pensjon.kjerne.grunnlag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.common.TypedInformation;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagVedleggType;

//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "K_GRUNNLAG_VEDLEGG_T", discriminatorType = DiscriminatorType.STRING, length = 20)
//@Table(name = "T_GRUNNLAG_VEDLEGG")
public abstract class AbstractGrunnlagVedlegg<T extends AbstractGrunnlag> extends AbstractVersionedPersistentDomainObject
        implements TypedInformation<GrunnlagVedleggType> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "GRUNNLAG_VEDLEGG_ID")
    private Long grunnlagVedleggId;

    @SuppressWarnings("unused") // used as MapKey in Persongrunnlag
    @Enumerated(EnumType.STRING)
    @Column(name = "K_GRUNNLAG_VEDLEGG_T", nullable = false, insertable = false, updatable = false)
    private GrunnlagVedleggType grunnlagVedleggType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_GRUNNLAG_ID", nullable = true)
    private Persongrunnlag persongrunnlag;

    public Long getGrunnlagVedleggId() {
        return grunnlagVedleggId;
    }

    protected abstract List<T> getGrunnlagList();

    protected List<T> getGrunnlagKap20List() {
        return Collections.emptyList();
    }

    public void addToClearedListFrom(List<T> grunnlagList) {
        List<T> copy = new ArrayList<>(grunnlagList);
        clear();

        for (T grunnlag : copy) {
            addGrunnlag(grunnlag);
        }
    }

    public void addToClearedKap20ListFrom(List<T> grunnlagList) {
        List<T> copy = new ArrayList<>(grunnlagList);
        clearKap20();

        for (T grunnlag : copy) {
            addGrunnlagKap20(grunnlag);
        }
    }

    public void addGrunnlag(T grunnlag) {
        associateWithVedlegg(grunnlag);
        associateWithPersongrunnlag(grunnlag);
        getGrunnlagList().add(grunnlag);
    }

    public void addGrunnlagKap20(T grunnlag) {
        // nop
    }

    @SuppressWarnings("unchecked")
    protected void associateWithVedlegg(T grunnlag) {
        grunnlag.setGrunnlagVedlegg(this);
    }

    protected void associateWithPersongrunnlag(T grunnlag) {
        grunnlag.setPersongrunnlag(getPersongrunnlag());
    }

    @SuppressWarnings("unchecked")
    public boolean removeGrunnlag(T grunnlag) {
        grunnlag.setGrunnlagVedlegg(null);
        return getGrunnlagList().remove(grunnlag);
    }

    public void removeIf(Predicate<T> predicate) {
        getGrunnlagList().removeIf(predicate);
    }

    protected Persongrunnlag getPersongrunnlag() {
        return persongrunnlag;
    }

    protected void setPersongrunnlag(Persongrunnlag persongrunnlag) {
        this.persongrunnlag = persongrunnlag;
    }

    public GrunnlagVedleggType getTypeCode() {
        return grunnlagVedleggType;
    }

    /**
     * Convenience method - use if there should not be more than one of this type of grunnlag
     */
    public T getEntry() {
        return CollectionUtils.isNotEmpty(getGrunnlagList()) ? getGrunnlagList().get(0) : null;
    }

    protected boolean isEmpty() {
        return CollectionUtils.isEmpty(getGrunnlagList());
    }

    protected void clear() {
        getGrunnlagList().clear();
    }

    protected void clearKap20() {
        // NOP
    }

    @Override
    public String toString() {
        return "grunnlagVedleggId=" + grunnlagVedleggId +
                ", persongrunnlag=" + getPersongrunnlag();
    }
}

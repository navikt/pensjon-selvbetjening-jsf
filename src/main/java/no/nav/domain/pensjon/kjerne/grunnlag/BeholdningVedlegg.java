package no.nav.domain.pensjon.kjerne.grunnlag;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

//@Entity
//@DiscriminatorValue("BEHOLDNING")
public class BeholdningVedlegg extends AbstractGrunnlagVedlegg<Beholdning> {

    @OneToMany(mappedBy = "grunnlagVedlegg", cascade = CascadeType.ALL)
    private List<Beholdning> grunnlagList = new ArrayList<>();

    @Override
    protected List<Beholdning> getGrunnlagList() {
        return grunnlagList;
    }
}

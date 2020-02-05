package no.nav.domain.pensjon.kjerne.grunnlag;

import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagVedleggType;

/**
 * Common type for grunnlag-types that are attached to Persongrunnlag via vedlegg
 */
public interface AbstractGrunnlag<T extends AbstractGrunnlagVedlegg> {

    GrunnlagVedleggType getGrunnlagVedleggType();

    void setGrunnlagVedlegg(T grunnlagVedlegg);

    void setPersongrunnlag(Persongrunnlag persongrunnlag);
}

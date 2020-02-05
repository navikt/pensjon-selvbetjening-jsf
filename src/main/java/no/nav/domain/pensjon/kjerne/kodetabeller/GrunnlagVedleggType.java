package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum GrunnlagVedleggType implements CtiConvertable<GrunnlagVedleggCti, GrunnlagVedleggType> {

    AFPEO,
    ARBEIDSFORHOLD_IEU,
    BARNEKULL,
    BEHOLDNING,
    BT_VURDERINGSPERIODE,
    DAGPENGE,
    ENDRET_OPPTJENINGSAR,
    FORSTEGANGSTJENESTE,
    INNTEKT,
    INNGANG_OG_EKSPORT,
    INST_OPPH_FASTE_U,
    INST_OPPH_RED,
    INST_OPPH_REF,
    KLAGE_ANKE,
    OMSORG,
    OMSORG_OVERF,
    OMSORG_GODSKR,
    OPPTJENING,
    PERSON_DET,
    SIMULERT_UTBETALING,
    TJENESTEPENSJON,
    TRYGDETID,
    TT_GRUNNLAG,
    TRYGDEAVTALE_DET,
    TRYGDEAVTALE,
    UTENLANDSOPPHOLD,
    VERNEPLIKTSAR,
    UFORE,
    UFORETRYGD_EO,
    YRKESKADE,
    AFP_TPO_UP;

    @Override
    public Class<GrunnlagVedleggCti> getCti() {
        return GrunnlagVedleggCti.class;
    }
}

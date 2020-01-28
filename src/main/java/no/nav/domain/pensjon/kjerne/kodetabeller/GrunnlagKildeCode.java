package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/** Generated class, do not edit. */
public enum GrunnlagKildeCode implements CtiConvertable<GrunnlagKildeCti, GrunnlagKildeCode> {
    /** AA-registeret */
    AA,
    /** Batch */
    BATCH,
    /** Beregnet */
    BEREGNET,
    /** Bruker */
    BRUKER,
    /** Brukeroppgitt */
    BRUKER_OPP,
    /** G-omregning */
    GOMR,
    /** Inntektskomponenten */
    IK,
    /** Inntektsregister */
    INNT,
    /** Inntekt satt ifm. konvertering */
    INNT_KONV,
    /** Institusjon */
    INSTOPP,
    /** Infotrygd */
    IT,
    /** Konvertering DSF */
    KONV_DSF,
    /** Data er konvertert inn fra b&aring;de DSF og IT */
    KONV_DSF_IT,
    /** Konvertering Infotrygd */
    KONV_IT,
    /** L&oslash;nnsvekstomregn inntekter */
    LVOMR,
    /** Medlemsunntaksregister */
    MEDL,
    /** Minste Pensjonsniv&aring; - Omregning */
    MPNOMR,
    /** &Oslash;vrig */
    OVRIG,
    /** PEN fellesdata */
    PEN,
    /** Pensjonsopptjeningsregister */
    POPP,
    /** Prosess */
    PROSESS,
    /** Regulering av pensjon */
    REGULERING,
    /** PEN */
    SAKSB,
    /** Simulering */
    SIMULERING,
    /** TPS - Personregister */
    TPS;


    @Override
    public Class<GrunnlagKildeCti> getCti() {
        return GrunnlagKildeCti.class;
    }
}

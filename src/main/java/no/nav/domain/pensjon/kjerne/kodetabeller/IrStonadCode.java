package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;
import no.stelvio.common.codestable.support.IllegalCodeEnum;

/**
 * Generated class, do not edit.
 */
public enum IrStonadCode implements CtiConvertable<IrStonadCti, IrStonadCode>, IllegalCodeEnum {
    /** Gjenlevende pensjon overgansst&oslash;nad */
    P_1("1"),
    /** Gjenlevende pensjon overgansst&oslash;nad */
    P_10("10"),
    /** Gjenlevende pensjon, st&oslash;nad til barnetilsyn */
    P_11("11"),
    /** Gjenlevende pensjon, utdanningsst&oslash;nad */
    P_12("12"),
    /** Barnepensjon */
    P_13("13"),
    /** Barnepensjon, en foreldres d&oslash;d */
    P_14("14"),
    /** Barnepensjon ved begge foreldres d&oslash;d */
    P_15("15"),
    /** Enslig fors&oslash;rger, overgangst&oslash;nad */
    P_16("16"),
    /** Enslig fors&oslash;rger, st&oslash;nad til barnetilsyn */
    P_17("17"),
    /** Enslig fors&oslash;rger, utdanningsst&oslash;nad */
    P_18("18"),
    /** Avtalefestet pensjon */
    P_19("19"),
    /** Alderspensjon */
    P_2("2"),
    /** Avtalefestet pensjon, fors&oslash;rgingstillegg */
    P_20("20"),
    /** Familiepleier, pensjon */
    P_21("21"),
    /** Familiepleier, overgansst&oslash;nad */
    P_22("22"),
    /** Familiepleier, utdanningsst&oslash;nad */
    P_23("23"),
    /** Grunnst&oslash;nad */
    P_24("24"),
    /** Hjelpest&oslash;nad */
    P_25("25"),
    /** Omsorgspoeng */
    P_26("26"),
    /** Grunnblankett */
    P_27("27"),
    /** Generell */
    P_28("28"),
    /** Gammel yrkesskade */
    P_29("29"),
    /** Alderspensjon, fors&oslash;rgingstillegg barn */
    P_3("3"),
    /** Krigspensjon */
    P_30("30"),
    /** Krigspensjon, sivil */
    P_31("31"),
    /** Krigspensjon, milit&aelig;r */
    P_32("32"),
    /** Yrkesskadetrygd gml. lov */
    P_33("33"),
    /** Feilutbetalt tjenestepensjon, Statens Pensj.kasse */
    P_34("34"),
    /** Feilutbetalt tjenestepensjon, Pensj.tr for sj&oslash;menn */
    P_35("35"),
    /** Uf&oslash;retrygd */
    P_36("36"),
    /** Alderspensjon, fors&oslash;rgingstillegg ektefelle */
    P_4("4"),
    /** Uf&oslash;repensjon */
    P_5("5"),
    /** Uf&oslash;repensjon, fors&oslash;rgingstillegg barn */
    P_6("6"),
    /** Uf&oslash;repensjon, fors&oslash;rgingstillegg ektefelle */
    P_7("7"),
    /** Forel&oslash;pig uf&oslash;rest&oslash;nad */
    P_8("8"),
    /** Gjenlevende pensjon */
    P_9("9");

    private String value = null;

    /**
     * Value constructor for illegal enums.
     *
     * @param value the illegal value
     */
    private IrStonadCode(String value) {
        this.value = value;
    }

    /**
     * Default constructor.
     */
    private IrStonadCode() {
    }

    /**
     * @return enum value
     */
    @Override
    public String getIllegalCode() {
        return value == null ? name() : value;
    }

    /**
     * Make toString behave the same for normal code enums and illegal code enums.
     * This makes the toString method synonymous with the getIllegalCode() method.
     */
    @Override
    public String toString() {
        return getIllegalCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<IrStonadCti> getCti() {
        return IrStonadCti.class;
    }
}

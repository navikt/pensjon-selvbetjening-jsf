package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;
import no.stelvio.common.codestable.support.IllegalCodeEnum;

/**
 * Generated class, do not edit.
 */
public enum UttaksgradCode implements CtiConvertable<UttaksgradCti, UttaksgradCode>, IllegalCodeEnum {
    /**
     * 0 %
     */
    P_0("0"),
    /**
     * 100 %
     */
    P_100("100"),
    /**
     * 20 %
     */
    P_20("20"),
    /**
     * 40 %
     */
    P_40("40"),
    /**
     * 50 %
     */
    P_50("50"),
    /**
     * 60 %
     */
    P_60("60"),
    /**
     * 80 %
     */
    P_80("80");

    private String value = null;

    /**
     * Value constructor for illegal enums.
     *
     * @param value the illegal value
     */
    UttaksgradCode(String value) {
        this.value = value;
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
    public Class<UttaksgradCti> getCti() {
        return UttaksgradCti.class;
    }
}

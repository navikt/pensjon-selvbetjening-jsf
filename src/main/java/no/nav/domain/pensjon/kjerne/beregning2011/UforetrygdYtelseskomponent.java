package no.nav.domain.pensjon.kjerne.beregning2011;

import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;

public interface UforetrygdYtelseskomponent {

    Integer getNettoAkk();

    void setNettoAkk(Integer nettoAkk);

    Integer getNettoRestAr();

    void setNettoRestAr(Integer nettoRestAr);

    Integer getAvkortningsbelopPerAr();

    void setAvkortningsbelopPerAr(Integer avkortningsbelopPerAr);

    YtelseKomponentTypeCode getYtelseKomponentType();

    Double getBruttoPerAr();

    Integer getTidligereBelopAr();

    void setTidligereBelopAr(Integer tidligereBelopAr);
}

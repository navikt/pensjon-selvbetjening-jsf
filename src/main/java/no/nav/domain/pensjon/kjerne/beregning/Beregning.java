package no.nav.domain.pensjon.kjerne.beregning;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Beregning {
    //TODO complete
    public Date getVirkDatoFom() {
        return new Date();
    }

    public Date getFomDato() {
        return new Date();
    }

    public Date getTomDato() {
        return new Date();
    }

    public Integer getUfg() {
        return 10;
    }

    public int getNetto() {
        return 2;
    }

    public List<BarnetilleggFellesbarn> getBarnetilleggFellesbarnListe() {
        return new ArrayList<>();
    }

    public List<BarnetilleggSerkullsbarn> getBarnetilleggSerkullsbarnListe() {
        return new ArrayList<>();
    }

    public List<Ektefelletillegg> getEktefelletilleggListe() {
        return new ArrayList<>();
    }
}

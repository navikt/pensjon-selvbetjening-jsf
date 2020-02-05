package no.nav.domain.pensjon.kjerne.beregning2011;

import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;

import java.util.ArrayList;
import java.util.List;

public class AbstraktBeregningsresultat {
    //TODO complete
//    @Override
    public List<Ytelseskomponent> hentYtelseskomponentListe(boolean hentKunYtelseskomponenterIBruk) {
        List<Ytelseskomponent> ytelseskomponenter = new ArrayList<>();
//        PensjonUnderUtbetaling puu = getPensjonUnderUtbetaling();
//
//        if (puu != null && puu.getYtelseskomponenter() != null) {
//            for (Ytelseskomponent y : puu.getYtelseskomponenter()) {
//                if (!hentKunYtelseskomponenterIBruk || (Boolean.TRUE.equals(y.getErBrukt()))) {
//                    ytelseskomponenter.add(y);
//                }
//            }
//        }

        return ytelseskomponenter;
    }
}

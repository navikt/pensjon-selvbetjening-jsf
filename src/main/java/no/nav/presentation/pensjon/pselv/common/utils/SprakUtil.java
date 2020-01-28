package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.codestable.CodesTablePeriodic;

import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCti;

public class SprakUtil {

    private static SprakCti bokmal;
    private static SprakCti nynorsk;
    private static SprakCti engelsk;

    public static String convertFromSprakKodeToMalform(CodesTableManager codesTableManager, String sprakKode) {
        getSprakCti(codesTableManager);

        if (nynorsk.getCodeAsString().equals(sprakKode)) {
            return nynorsk.getDecode();
        }

        if (engelsk.getCodeAsString().equals(sprakKode)) {
            return engelsk.getDecode();
        }

        return bokmal.getDecode();
    }


    public static List<SelectItem> getSprakMalformListe(CodesTableManager codesTableManager) {
        getSprakCti(codesTableManager);
        List<SelectItem> items = new ArrayList<>();
        items.add(new SelectItem(bokmal.getCodeAsString(), bokmal.getDecode()));
        items.add(new SelectItem(nynorsk.getCodeAsString(), nynorsk.getDecode()));
        items.add(new SelectItem(engelsk.getCodeAsString(), engelsk.getDecode()));
        return items;
    }

    private static void getSprakCti(CodesTableManager codesTableManager) {
        CodesTablePeriodic<SprakCti, SprakCode, String> codesTable = codesTableManager.getCodesTablePeriodic(SprakCti.class);
        bokmal = codesTable.getCodesTableItem(SprakCode.NB);
        nynorsk = codesTable.getCodesTableItem(SprakCode.NN);
        engelsk = codesTable.getCodesTableItem(SprakCode.EN);
    }

    private SprakUtil() {
    }
}

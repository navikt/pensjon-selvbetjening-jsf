package no.nav.domain.pensjon.kjerne.beregning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.nav.domain.pensjon.kjerne.Merknad;

/**
 * Dette er ikke et domenobjekt, men en parser for lister med Poengtall.
 * Poengtall er denormalisert som en String i de objektene som bruker den.
 * Denne strengen er : separert, med ; som record-separator.
 */
public class PoengtallListeParser {

    private static final String RECORD_SEPARATOR = ";";

    public static List<Poengtall> parseString(String poengtallListe, List<Merknad> merknadListe) {
        List<Poengtall> parsedList = new ArrayList<>();

        if (poengtallListe != null && !poengtallListe.isEmpty()) {
            String[] records = poengtallListe.split(RECORD_SEPARATOR);
            Map<Integer, List<Merknad>> poengtallMerknader = findPoengtallMerknader(merknadListe);

            for (String record2 : records) {
                String record = record2 + RECORD_SEPARATOR;
                Poengtall poengtall = new Poengtall(record);
                addMerknaderToPoengtall(poengtallMerknader, poengtall);
                parsedList.add(poengtall);
            }
        }

        return parsedList;
    }

    /**
     * Gjør om en liste med Poengtall til en streng som kan lagres av Poengrekke eller FramtidigePoengtall
     */
    public static String parsePoengtallListe(List<Poengtall> poengtallListe) {
        StringBuilder builder = new StringBuilder();

        if (poengtallListe != null) {
            for (Poengtall poengtall : poengtallListe) {
                // Note: record separator is included
                builder.append(poengtall.serializeToString());
            }
        }

        return builder.toString();
    }

    private static void addMerknaderToPoengtall(Map<Integer, List<Merknad>> poengtallMerknader, Poengtall poengtall) {
        List<Merknad> merknader = poengtallMerknader.get(poengtall.getAr());

        if (merknader != null) {
            poengtall.setMerknadListe(merknader);
        }
    }

    /**
     * Lag et hashmap som inneholder ar som key og merknadliste som value
     */
    private static Map<Integer, List<Merknad>> findPoengtallMerknader(List<Merknad> merknadListe) {
        Map<Integer, List<Merknad>> map = new HashMap<>();

        if (merknadListe == null) {
            return map;
        }

        for (Merknad merknad : merknadListe) {
            if (merknad.getAr() != null) {
                List<Merknad> merknader = map.get(merknad.getAr());

                if (merknader == null) {
                    merknader = new ArrayList<>();
                    map.put(merknad.getAr(), merknader);
                }

                merknader.add(merknad);
            }
        }

        return map;
    }

    private PoengtallListeParser() {
    }
}

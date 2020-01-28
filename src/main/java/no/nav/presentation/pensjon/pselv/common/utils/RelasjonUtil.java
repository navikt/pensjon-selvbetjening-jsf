package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.kjerne.kodetabeller.PersonrelasjonTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstatusTypeCode;
import no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants;

public class RelasjonUtil {

    /**
     * Retrieves all children (Relasjon with type BARN) under 18 from the inputlist.
     */
    public List<Relasjon> hentBarn(List<Relasjon> relasjoner, boolean excludeDead, Integer ageLimit, Date date) {
        Integer _ageLimit = ageLimit;
        Date _date = date;

        if (_ageLimit == null) {
            _ageLimit = PensjonsKalkulatorConstants.AGE_LIMIT_18;
        }
        if (_date == null) {
            _date = new Date();
        }

        return getAllRelationsUnderAgeLimit(PersonrelasjonTypeCode.BARN, _date, relasjoner, _ageLimit, excludeDead);
    }

    public Relasjon hentRelasjonAvType(PersonrelasjonTypeCode type, List<Relasjon> list, boolean excludeDead) {
        Calendar today = Calendar.getInstance();

        for (Relasjon relasjon : list) {
            if (type == null || !type.toString().equalsIgnoreCase(relasjon.getRelasjonsType())) {
                continue;
            }

            if (relasjon.getPerson().getDodsdato() == null) {
                //<SIR 76048>
                if (relasjon.getTom() == null) {
                    //Tilogmed dato er ikke satt  --> nåværende ektefelle
                    return relasjon;
                } else if (relasjon.getTom() != null && relasjon.getTom().after(today)) {
                    return relasjon;
                }
                //</SIR 76048>
            } else {
                if (!excludeDead) {
                    return relasjon;
                }
            }
        }

        return null;
    }

    public List<Relasjon> hentAlleRelasjonerAvType(PersonrelasjonTypeCode type, List<Relasjon> list, boolean excludeDead) {
        List<Relasjon> liste = new ArrayList<>();
        Relasjon relasjon;

        if (list == null) {
            return liste;
        }

        ListIterator<Relasjon> iterate = list.listIterator();

        while (iterate.hasNext()) {
            relasjon = iterate.next();

            if (type == null || !type.toString().equalsIgnoreCase(relasjon.getRelasjonsType())) {
                continue;
            }

            if (relasjon.getPerson().getDodsdato() == null) {
                liste.add(relasjon);
            } else {
                if (!excludeDead) {
                    liste.add(relasjon);
                }
            }
        }

        return liste;
    }

    /**
     * Retrieves the dead relation of the specified type. If multiple dead relations exist, the one with the LATEST tom date is
     * returned.
     */
    public Relasjon getLatestDeadRelationOfType(PersonrelasjonTypeCode type, List<Relasjon> list) {
        List<Relasjon> relasjonsList = hentAlleRelasjonerAvType(type, list, false);
        Relasjon newestRelasjon = null;

        for (Relasjon relasjon : relasjonsList) {
            newestRelasjon = findLatestDeceasedRelation(newestRelasjon, relasjon);
        }

        return newestRelasjon;
    }

    private Relasjon findLatestDeceasedRelation(Relasjon newestRelasjon, Relasjon relasjon) {
        if (!isPersonDead(relasjon.getPerson())) {
            return newestRelasjon;
        }

        if (newestRelasjon == null) {
            newestRelasjon = relasjon;
        } else if (isRelationAfterByTOM(newestRelasjon, relasjon)) {
            newestRelasjon = relasjon;
        }

        return newestRelasjon;
    }

    private boolean isPersonDead(Person person) {
        return person.getDodsdato() != null;
    }

    private boolean isRelationAfterByTOM(Relasjon newestRelasjon, Relasjon relasjon) {
        return DateUtil.isAfterByDay(buildDate(relasjon.getTom()), buildDate(newestRelasjon.getTom()), false);
    }

    private Date buildDate(Calendar cal) {
        return cal != null ? cal.getTime() : null;
    }

    /**
     * Checks if the <code>Relasjon</code> object is RELASJON_TYPE_BARN and is less than the child AGE_LIMIT_18 at the
     * inputdate specified.
     */
    public boolean isBarn(Relasjon relasjon, Date retiredDate) {
        int age = Pid.calculateAge(relasjon.getPerson().getPid(), retiredDate);
        boolean personIsUnderEighteen = age < PensjonsKalkulatorConstants.AGE_LIMIT_18;
        boolean personIsBarn = PersonrelasjonTypeCode.BARN.toString().equals(relasjon.getRelasjonsType());
        return personIsBarn && personIsUnderEighteen;
    }

    /**
     * Returns a list of sosken (siblings) at the given date. Siblings are defined as having the relation type "SOSK" and being
     * under the age given by the age limit at the given time.
     */
    public List<Relasjon> getSosken(Date date, List<Relasjon> relasjoner, Integer ageLimit, Boolean excludeDead) {
        Integer _ageLimit = ageLimit; //TODO why copy?
        Boolean _excludeDead = excludeDead;
        if (_ageLimit == null) {
            _ageLimit = PensjonsKalkulatorConstants.AGE_LIMIT_18;
        }
        if (_excludeDead == null) {
            _excludeDead = Boolean.TRUE;
        }
        return getAllRelationsUnderAgeLimit(PersonrelasjonTypeCode.SOSK, date, relasjoner, _ageLimit, _excludeDead);
    }

    /**
     * Get the user's EPS based on sivilstatus.
     */
    public Relasjon hentEpsRelasjon(List<Relasjon> relasjoner, String sivilstatus) {
        if (SivilstatusTypeCode.GIFT.name().equals(sivilstatus) || SivilstatusTypeCode.SEPR.name().equals(sivilstatus)) {
            return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.EKTE);
        }

        if (SivilstatusTypeCode.REPA.name().equals(sivilstatus) || SivilstatusTypeCode.SEPR.name().equals(sivilstatus)) {
            return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.REPA);
        }

        return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.SAMB);
    }

    public boolean containsValidEps(List<Relasjon> relasjoner, String sivilstatus) {
        if (SivilstatusTypeCode.GIFT.name().equals(sivilstatus) || SivilstatusTypeCode.SEPR.name().equals(sivilstatus)) {
            return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.EKTE) != null;
        }

        if (SivilstatusTypeCode.REPA.name().equals(sivilstatus) || SivilstatusTypeCode.SEPA.name().equals(sivilstatus)) {
            return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.REPA) != null;
        }

        if (SivilstatusTypeCode.SAMB.name().equals(sivilstatus)) {
            return getCurrentRelation(relasjoner, PersonrelasjonTypeCode.SAMB) != null;
        }

        return true;
    }

    /**
     * Find a person's current relation of a given type. Should be used for types where you can only have one relation at a
     * time, like EKTE, REPA or SAMB.
     */
    private Relasjon getCurrentRelation(List<Relasjon> relasjoner, PersonrelasjonTypeCode type) {
        Relasjon currentRelasjon = null;

        for (Relasjon relasjon : relasjoner) {
            if (type.name().equals(relasjon.getRelasjonsType())) {
                currentRelasjon = relasjon;
            }
        }

        return currentRelasjon;
    }

    /**
     * Returns a list of relations of the given type that are under the given age limit at the given date. The method excludes
     * dead relations if this is desired.
     */
    protected List<Relasjon> getAllRelationsUnderAgeLimit(PersonrelasjonTypeCode type, Date date, List<Relasjon> relasjoner,
                                                          int ageLimit, boolean excludeDead) {
        List<Relasjon> tempRelasjoner = new ArrayList<>();
        ListIterator<Relasjon> iterator = relasjoner.listIterator();

        while (iterator.hasNext()) {
            Relasjon relasjon = iterator.next();

            if (!type.toString().equalsIgnoreCase(relasjon.getRelasjonsType())) {
                continue;
            }

            Calendar dodsdato = relasjon.getPerson().getDodsdato();
            boolean addRelation = true;
            /* SIR NAV00100735: Added the not valid pid condition */
            if (excludeDead && (dodsdato != null || !Pid.isValidPid(relasjon.getPerson().getFodselsnummer()))) {
                addRelation = false;
            }

            if (addRelation) {
                Pid pid = new Pid(relasjon.getPerson().getFodselsnummer());

                if (Pid.calculateAge(pid, date) < ageLimit) {
                    tempRelasjoner.add(relasjon);
                }
            }
        }

        return tempRelasjoner;
    }
}

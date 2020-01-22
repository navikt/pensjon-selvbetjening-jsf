package no.nav.domain.pensjon.common.person;

import no.stelvio.domain.person.Pid;

public interface PersonFnr {

    void setFnr(Pid fnr);

    Pid getFnr();
}

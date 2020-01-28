package no.nav.domain.pensjon.common;

import java.util.Date;

/**
 * Interface that should be implemented by domain objects that has FOM and TOM dates.
 * <p>
 * In most cases introducing this interface to the domain object will not lead to any other changes to the domain object.
 * In other cases you might have to implement the
 * <code>VirkFom</code> and <code>VirkTom</code> instead of <code>FomDato</code> and <code>TomDato</code>.
 */
public interface PeriodisertInformasjon {

    Date getFomDato();

    Date getTomDato();

    void setFomDato(Date fom);

    void setTomDato(Date tom);
}

package no.nav.service.pensjon.grunnlag;

import java.util.Date;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import no.stelvio.common.transferobject.ServiceRequest;

public class HentGrunnbelopListeRequest extends ServiceRequest {

    private static final long serialVersionUID = -7808801711144708452L;
    private Date fromDate;
    private Date toDate;

    public HentGrunnbelopListeRequest() {
    }

    public HentGrunnbelopListeRequest(Date fromDate, Date toDate) {
        super();
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (object == this) {
            return true;
        }

        if (object.getClass() != getClass()) {
            return false;
        }

        HentGrunnbelopListeRequest request = (HentGrunnbelopListeRequest) object;
        return new EqualsBuilder()
                .append(fromDate, request.getFromDate())
                .append(toDate, request.getToDate())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(23, 59)
                .append(fromDate)
                .append(toDate)
                .toHashCode();
    }
}

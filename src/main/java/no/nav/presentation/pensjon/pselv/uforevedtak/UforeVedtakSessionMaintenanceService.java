package no.nav.presentation.pensjon.pselv.uforevedtak;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import no.nav.presentation.pensjon.common.security.validation.enums.Roles;

import static java.util.Arrays.stream;

public class UforeVedtakSessionMaintenanceService {

    public static final String HAS_UFORE_VEDTAK = "no.nav.presentation.pensjon.pselv.vedtak.attribute.uforeVedtak";

    public void setAssignedUforeVedtakRoles(HttpSession session, Roles... roles) {
        session.setAttribute(HAS_UFORE_VEDTAK, extractRoleNames(roles));
    }

    public void removeAssignedUforeVedtakRoles(HttpSession session) {
        session.removeAttribute(HAS_UFORE_VEDTAK);
    }

    private List<String> extractRoleNames(Roles... roles) {
        return stream(roles)
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}

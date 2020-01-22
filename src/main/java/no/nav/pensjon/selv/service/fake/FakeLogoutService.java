package no.nav.pensjon.selv.service.fake;

import no.stelvio.presentation.security.logout.LogoutService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FakeLogoutService implements LogoutService {

    @Override
    public void logout() throws IOException {
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    @Override
    public void logoutToUrl(String url) throws IOException {
    }

    @Override
    public void logoutToUrl(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
    }
}

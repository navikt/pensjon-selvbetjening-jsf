package no.nav.presentation.pensjon.pselv.common;

import static no.nav.presentation.pensjon.pselv.common.PselvConstants.LEFT_MENU_PENSJON;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.LEFT_MENU_PENSJON_XML;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.LEFT_MENU_UT;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.LEFT_MENU_UT_XML;

import java.io.FileReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.presentation.pensjon.pselv.common.menu.MenuItem;
import no.nav.presentation.pensjon.pselv.common.menu.MenuItemConverter;

/**
 * Loads the menu on startup of the application.
 */
public class PSELVStartupListener implements ServletContextListener {

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        event.getServletContext().setAttribute(LEFT_MENU_PENSJON, null);
        event.getServletContext().setAttribute(LEFT_MENU_UT, null);
    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        try {
            loadMenu(event, LEFT_MENU_PENSJON_XML, LEFT_MENU_PENSJON);
            loadMenu(event, LEFT_MENU_UT_XML, LEFT_MENU_UT);
        } catch (IOException ioe) {
            throw new ImplementationUnrecoverableException(ioe);
        }
    }

    private static void loadMenu(ServletContextEvent event, String menuXml, String menuAttributeName) throws IOException {
        XStream xstream = new XStream(new DomDriver());
        xstream.setClassLoader(Thread.currentThread().getContextClassLoader());
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByWildcard(new String[]{"no.nav.**"});
        xstream.alias("menuitem", MenuItem.class);
        xstream.alias("role", String.class);
        xstream.registerConverter(new MenuItemConverter());
        ServletContext context = event.getServletContext();

        try (FileReader file = new FileReader(context.getRealPath("/WEB-INF/classes/" + menuXml))) {
            MenuItem top = (MenuItem) xstream.fromXML(file);
            context.setAttribute(menuAttributeName, top);
        }
    }
}

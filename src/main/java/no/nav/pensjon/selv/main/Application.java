package no.nav.pensjon.selv.main;

import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

/**
 * Adapted from:
 * https://devcenter.heroku.com/articles/create-a-java-web-application-using-embedded-tomcat
 * http://www.alto-tek.eu/2017/04/jsf-21-within-embedded-tomcat-8-2017.html
 */
public class Application {

    private static final String WEBAPP_LOCATION = "src/main/webapp/";
    private static final String WEBAPP_MOUNT = "/WEB-INF/classes";
    private static final String WEB_INF_CLASS_BASE = "target/classes";

    public static void main(String[] args) throws Exception {
        Tomcat tomcat = prepareTomcat();
        tomcat.start();
        tomcat.getServer().await();
    }

    private static Tomcat prepareTomcat() {
        var tomcat = new Tomcat();
        tomcat.setPort(getPort());
        addWebapp(tomcat);
        return tomcat;
    }

    private static void addWebapp(Tomcat tomcat) {
        var context = (StandardContext) tomcat.addWebapp("/", absolutePath(WEBAPP_LOCATION));
        String additionWebInfClasses = absolutePath(WEB_INF_CLASS_BASE);
        var resources = new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(resources, WEBAPP_MOUNT, additionWebInfClasses, "/"));
        context.setResources(resources);
    }

    private static String absolutePath(String relativePath) {
        return new File(relativePath).getAbsolutePath();
    }

    private static int getPort() {
        String webPort = System.getenv("PORT");
        return webPort == null || webPort.isEmpty() ? 8080 : Integer.parseInt(webPort);
    }
}

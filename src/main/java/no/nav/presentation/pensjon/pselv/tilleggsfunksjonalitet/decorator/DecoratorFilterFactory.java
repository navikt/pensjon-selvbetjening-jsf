package no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.decorator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import no.nav.innholdshenter.common.EnonicContentRetriever;
import no.nav.innholdshenter.filter.DecoratorFilter;
import org.springframework.beans.factory.FactoryBean;

public class DecoratorFilterFactory implements FactoryBean<Filter> {

    private static final String FRAGMENT_HEADER_WITHMENU = "header-withmenu";
    private static final String FRAGMENT_FOOTER = "footer";
    private static final String FRAGMENT_FOOTER_WITHMENU = "footer-withmenu";
    private static final String FRAGMENT_SKIPLINKS = "skiplinks";
    private static final String FRAGMENT_STYLES = "styles";
    private static final String FRAGMENT_SCRIPTS = "scripts";
    private static final String FRAGMENT_MEGAMENU_RESOURCES = "megamenu-resources";
    private String applicationName;
    private String fragmentsUrl;
    private PENonicContentRetriever contentRetriever;
    private boolean selvbetjeningsSone;

    @Override
    public Filter getObject() {
        final DecoratorFilter filter = new DecoratorFilter();
        filter.setApplicationName(applicationName);
        filter.setFragmentsUrl(fragmentsUrl);
        filter.setContentRetriever(contentRetriever);
        filter.setFragmentNames(getFragmentNames());
        return filter;
    }

    @Override
    public Class<?> getObjectType() {
        return DecoratorFilter.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getFragmentsUrl() {
        return fragmentsUrl;
    }

    public void setFragmentsUrl(String fragmentsUrl) {
        this.fragmentsUrl = fragmentsUrl;
    }

    public EnonicContentRetriever getContentRetriever() {
        return contentRetriever;
    }

    public void setContentRetriever(PENonicContentRetriever contentRetriever) {
        this.contentRetriever = contentRetriever;
    }

    public boolean isSelvbetjeningsSone() {
        return selvbetjeningsSone;
    }

    public void setSelvbetjeningsSone(boolean selvbetjeningsSone) {
        this.selvbetjeningsSone = selvbetjeningsSone;
    }

    private static List<String> getFragmentNames() {
        final List<String> names = new ArrayList<>();
        names.add(FRAGMENT_STYLES);
        names.add(FRAGMENT_SCRIPTS);
        names.add(FRAGMENT_MEGAMENU_RESOURCES);
        names.add(FRAGMENT_HEADER_WITHMENU);
        names.add(FRAGMENT_SKIPLINKS);
        names.add(FRAGMENT_FOOTER_WITHMENU);
        return names;
    }
}

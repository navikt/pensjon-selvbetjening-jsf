package no.nav.presentation.pensjon.pselv.avvik;

import java.util.Map;

import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.execution.FlowExecutionException;

import no.stelvio.domain.person.Pid;

import no.nav.presentation.pensjon.common.webflow.AbstractFlowExecutionAvvikHandler;
import no.nav.service.pensjon.avvik.AvvikServiceBi;
import no.nav.service.pensjon.avvik.KategoriserAvvikRequest;
import no.nav.service.pensjon.avvik.KategoriserAvvikResponse;
import no.nav.service.pensjon.vedlikehold.HentPenPersonRequest;
import no.nav.service.pensjon.vedlikehold.HentPenPersonResponse;
import no.nav.service.pensjon.vedlikehold.VedlikeholdServiceBi;

public class FlowExecutionAvvikHandler extends AbstractFlowExecutionAvvikHandler {

    private static final String APPLIKASJON = "PSELV";
    private VedlikeholdServiceBi vedlikeholdService;
    private AvvikServiceBi avvikService;

    @Override
    protected LocalAttributeMap handleAvvik(FlowExecutionException exception, Map<String, String> contextData, Map<String, String> serverData) {
        KategoriserAvvikResponse response = avvikService.kategoriserAvvik(new KategoriserAvvikRequest(exception, contextData, serverData, APPLIKASJON));
        return createAttributeMap(response);
    }

    @Override
    protected Long findPersonIdByPid(Pid pid) {
        HentPenPersonResponse response = vedlikeholdService.hentPenPerson(new HentPenPersonRequest(pid));
        return response.getPenPerson().getPenPersonId();
    }

    @SuppressWarnings("Duplicates")
    private LocalAttributeMap createAttributeMap(KategoriserAvvikResponse response) {
        LocalAttributeMap map = new LocalAttributeMap();
        map.put("avviksinfoId", response.getAvviksinfoId().toString());
        map.put("meldingTilBruker", response.getMeldingTilBruker());
        map.put("status", response.getStatus());
        return map;
    }

    public void setVedlikeholdService(VedlikeholdServiceBi vedlikeholdService) {
        this.vedlikeholdService = vedlikeholdService;
    }

    public void setAvvikService(AvvikServiceBi avvikService) {
        this.avvikService = avvikService;
    }
}

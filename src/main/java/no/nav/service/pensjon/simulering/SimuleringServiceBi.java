package no.nav.service.pensjon.simulering;

public interface SimuleringServiceBi {
    SimuleringEtter2011Response finnForsteMuligeUttaksalder(SimuleringEtter2011Request request);

    SimuleringEtter2011Response simulerFleksibelAP(SimuleringEtter2011Request request);
}

package no.nav.presentation.pensjon.pselv.publisering.support;

import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.DinPensjonForm;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.SkjemaKravFormData;
import no.nav.presentation.pensjon.pselv.publisering.uforetrygd.UforetrygdForm;
import no.stelvio.presentation.util.PagedSortableList;

import java.util.List;

public interface SakerAndSkjemaTablePanel {
    String getHelpPopupSakStatus();

    DinPensjonForm getDinPensjonDefaultForm(DinPensjonForm form);

    SkjemaKravFormData populateSkjemaFormData(Skjema skjema);

    UforetrygdForm getUforetrygdDefaultForm(UforetrygdForm form);

    List<Sak> getFilterKravHodeList(List<KravHode> kravList);

    SkjemaKravFormData populateSakFormData(Sak sak);

    void setPagedSortableSkjemaList(PagedSortableList<SkjemaKravFormData> pagedSortableSkjemaList);

    PagedSortableList<SkjemaKravFormData> getPagedSortableSkjemaList();

    SkjemaKravFormData getSelectedSkjemaOrKrav();

    void setSelectedSkjemaOrKrav(SkjemaKravFormData selectedSkjemaOrKrav);

    boolean isSakAndSkjemaListContainsOnlySak();

    boolean isSakAndSkjemaListContainsOnlySkjema();

    boolean isSakAndSkjemaListContainsBoth();

    boolean isShowSakAndSkjemaBlock();
}

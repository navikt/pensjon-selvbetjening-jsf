package no.nav.presentation.pensjon.pselv.publisering.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.stelvio.presentation.util.PagedSortableList;

import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.DinPensjonForm;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.SkjemaKravFormData;
import no.nav.presentation.pensjon.pselv.publisering.uforetrygd.UforetrygdForm;

/**
 * View helper for the component sakSkjemaTable.xhtml
 * The component is used by PUS002 Din Pensjon and PUS019 Uforetrygd. The pages
 * forms delegates to this view helper
 *
 * @see no.nav.presentation.pensjon.pselv.publisering.dinpensjon.DinPensjonForm
 * @see no.nav.presentation.pensjon.pselv.publisering.uforetrygd.UforetrygdForm
 */
public class SakerAndSkjemaTablePanelViewHelper implements Serializable, SakerAndSkjemaTablePanel {

    /**
     * Sortable list of user's skjema and sak
     */
    private PagedSortableList<SkjemaKravFormData> pagedSortableSkjemaList;

    /**
     * The selected element in the list of user's skjema and krav. Used when the user wishes to open (or in the case of skjema,
     * delete) a skjema/sak
     */
    private SkjemaKravFormData selectedSkjemaOrKrav;

    private boolean isUserGroup1;

    private Sak lopendeSak;

    public SakerAndSkjemaTablePanelViewHelper(boolean isUserGroup1, Sak lopendeSak) {
        this.isUserGroup1 = isUserGroup1;
        this.lopendeSak = lopendeSak;
    }

    @Override
    public boolean isSakAndSkjemaListContainsOnlySak() {
        for (SkjemaKravFormData row : getPagedSortableSkjemaList().getList()) {
            if (row.getErSkjema()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSakAndSkjemaListContainsOnlySkjema() {
        for (SkjemaKravFormData row : getPagedSortableSkjemaList().getList()) {
            if (row.getErKrav()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isSakAndSkjemaListContainsBoth() {
        return !isSakAndSkjemaListContainsOnlySak() && !isSakAndSkjemaListContainsOnlySkjema();
    }

    @Override
    public boolean isShowSakAndSkjemaBlock() {
        return !getPagedSortableSkjemaList().getList().isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHelpPopupSakStatus() {
        String helpPopupSakStatus;
        if (this.isUserGroup1) {
            helpPopupSakStatus = "pselv.pus002.helppopup.hjelpstatusskjemaer";
        } else {
            if (this.lopendeSak == null) {
                helpPopupSakStatus = "pselv.pus002.helpinpage.hjelpstatusskjemaernytt";
            } else {
                helpPopupSakStatus = "pselv.pus002.helpinpage.hjelpstatusskjemaerlopende";
            }
        }
        return helpPopupSakStatus;
    }

    @Override
    public DinPensjonForm getDinPensjonDefaultForm(DinPensjonForm form) {
        form.setAlderspensjonSkjemaId(null);
        form.setAlderspensjonSkjemaStarted(false);
        form.setForsorgertilleggSkjemaId(null);
        form.setForsorgertilleggSkjemaStarted(false);
        form.setSelectedSkjemaOrKrav(null);
        form.setDeleteSkjema(false);
        return form;
    }

    @Override
    public UforetrygdForm getUforetrygdDefaultForm(UforetrygdForm form) {
        form.setAlderspensjonSkjemaId(null);
        form.setAlderspensjonSkjemaStarted(false);
        form.setForsorgertilleggSkjemaId(null);
        form.setForsorgertilleggSkjemaStarted(false);
        form.setSelectedSkjemaOrKrav(null);
        form.setDeleteSkjema(false);
        return form;
    }

    @Override
    public List<Sak> getFilterKravHodeList(List<KravHode> kravList) {
        List<Sak> sakList = new ArrayList<Sak>();

        Map<Long, Sak> sakMap = new HashMap<Long, Sak>();
        for (KravHode kravHode : kravList) {
            if (!sakMap.containsKey(kravHode.getSak().getSakId())) {
                sakMap.put(kravHode.getSak().getSakId(), kravHode.getSak());
            }
        }
        sakList.addAll(sakMap.values());
        return sakList;
    }

    @Override
    public SkjemaKravFormData populateSkjemaFormData(Skjema skjema) {
        SkjemaKravFormData skjemaKravFormData = new SkjemaKravFormData();

        skjemaKravFormData.setDato(skjema.getChangeStamp().getCreatedDate());
        skjemaKravFormData.setErKrav(false);
        skjemaKravFormData.setErSkjema(true);
        skjemaKravFormData.setId(skjema.getSkjemaId().toString());
        skjemaKravFormData.setNavn(skjema.getSkjemaPselvType().getDecode());
        skjemaKravFormData.setType(skjema.getSkjemaPselvType().getCodeAsString());
        skjemaKravFormData.setStatus(skjema.getSkjemaPselvStatus().getDecode());
        return skjemaKravFormData;
    }

    @Override
    public SkjemaKravFormData populateSakFormData(Sak sak) {
        SkjemaKravFormData skjemaKravFormData = new SkjemaKravFormData();
        skjemaKravFormData.setDato(sak.getChangeStamp().getCreatedDate());
        skjemaKravFormData.setErKrav(true);
        skjemaKravFormData.setErSkjema(false);
        skjemaKravFormData.setId(sak.getSakId().toString());
        skjemaKravFormData.setNavn(sak.getSakType().getDecode());
        skjemaKravFormData.setType(sak.getSakType().getCodeAsString());
        skjemaKravFormData.setStatus(sak.getSakStatus().getDecode());
        return skjemaKravFormData;
    }

    @Override
    public void setPagedSortableSkjemaList(PagedSortableList<SkjemaKravFormData> pagedSortableSkjemaList) {
        this.pagedSortableSkjemaList = pagedSortableSkjemaList;
    }

    @Override
    public PagedSortableList<SkjemaKravFormData> getPagedSortableSkjemaList() {
        return this.pagedSortableSkjemaList;
    }

    @Override
    public SkjemaKravFormData getSelectedSkjemaOrKrav() {
        return selectedSkjemaOrKrav;
    }

    @Override
    public void setSelectedSkjemaOrKrav(SkjemaKravFormData selectedSkjemaOrKrav) {
        this.selectedSkjemaOrKrav = selectedSkjemaOrKrav;
    }
}

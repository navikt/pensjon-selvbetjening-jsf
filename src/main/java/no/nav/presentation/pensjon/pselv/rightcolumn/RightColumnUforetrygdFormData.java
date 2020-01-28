package no.nav.presentation.pensjon.pselv.rightcolumn;

import org.apache.commons.collections.CollectionUtils;

/**
 * The rightColumn to be used in the ufore context
 *
 */
public class RightColumnUforetrygdFormData extends RightColumnCommonFormData {

    private static final long serialVersionUID = -7224832960671610723L;

    private Long sisteRegistrerteForventetInntekt;

    private String sisteRegistrerteForventetInntektAr;

    /**
     * Determine if the block 'info nav har om deg' is to be shown.
     *
     * @return true if the user has a løpende uføretrygd, false otherwise.
     */
    @Override
    public boolean isShowBlokkInfoNavHarOmDeg() {
        return isLopendeUforetrygd();
    }

    /**
     * Determine if the field 'registrert forventet inntekt' should be shown.
     *
     * @return true if the user has one or more 'registrert forventet inntekt', false otherwise.
     */
    public boolean isShowRegistrerteInntekter() {
        return sisteRegistrerteForventetInntekt != null;
    }

    /**
     * Determine if the rightColumn is to be shown.
     *
     * @return true if the user has one or more løpende ytelser, false otherwise.
     */
    public boolean isShowRightColumn() {
        return CollectionUtils.isNotEmpty(getLopendeYtelser());
    }

    public Long getSisteRegistrerteForventetInntekt() {
        return sisteRegistrerteForventetInntekt;
    }

    public void setSisteRegistrerteForventetInntekt(Long sisteRegistrerteForventetInntekt) {
        this.sisteRegistrerteForventetInntekt = sisteRegistrerteForventetInntekt;
    }

    public String getSisteRegistrerteForventetInntektAr() {
        return sisteRegistrerteForventetInntektAr;
    }

    public void setSisteRegistrerteForventetInntektAr(String sisteRegistrerteForventetInntektAr) {
        this.sisteRegistrerteForventetInntektAr = sisteRegistrerteForventetInntektAr;
    }
}

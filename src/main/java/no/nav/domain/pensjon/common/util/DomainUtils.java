package no.nav.domain.pensjon.common.util;

import java.util.List;

public class DomainUtils {

    /**
     * <p>
     * Helper method for setting a value for a OneToOne field. OneToOne fields can't be lazy loaded. Thus, we simulate OneToOne by using a OneToMany relation with only one element
     * in the list. This method will throw a RuntimeException if you try to replace an already existing value.
     * </p>
     * <p>
     * Parameter example assumes that this method is called from the <code>Persongrunnlag.setYrkesskadegrunnlag()</code> method.
     * </p>
     */
    public static <T> void setOneToOneField(List<T> valueList, Object oldPrimaryKey, T newValue, String fieldName, String ownerName, Object ownerId) {
        // Get old value
        T oldValue = null;

        if (valueList != null && !valueList.isEmpty()) {
            oldValue = valueList.get(0);
        }

        // Try to set a value. It's only allowed to set a value if there is no previous persistent value
        if (oldValue == null || oldPrimaryKey == null) {
            valueList.clear();
            valueList.add(newValue);
        } else {
            String errorMessage = "Det finnes allerede et " + fieldName + " registrert på " + ownerName + " ";
            errorMessage += ownerId + ". Det er kun lov til å endre et eksisterende " + fieldName;
            errorMessage += ", det er ikke lov til å nullstille et eksisterende.";
            throw new RuntimeException(errorMessage);
        }
    }

    /**
     * <p>
     * Helper method for setting a value for a OneToOne field. OneToOne fields can't be lazy loaded. Thus, we simulate OneToOne by using a OneToMany relation with only one element
     * in the list. This method will overwrite the previous value. This requires that the underlying list uses DELETE_ORPHAN cascade. If you use the value <code>null</code> as
     * newValue, the underlying list will be cleared.
     * </p>
     * <p>
     * Parameter example assumes that this method is called from the <code>Persongrunnlag.setYrkesskadegrunnlag()</code> method.
     * </p>
     */
    public static <T> void setOneToOneFieldOverwritePrevious(List<T> valueList, T newValue) {
        T oldValue = !valueList.isEmpty() ? valueList.get(0) : null;

        // Do nothing we we try to set the same object
        if (oldValue == newValue) {
            return;
        }

        valueList.clear();

        if (newValue == null) {
            return;
        }

        valueList.add(newValue);
    }

    private DomainUtils() {
    }
}

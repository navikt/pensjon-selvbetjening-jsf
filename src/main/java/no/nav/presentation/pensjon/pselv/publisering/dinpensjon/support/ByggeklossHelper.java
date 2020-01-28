package no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.DinPensjonForm;
import no.nav.presentation.pensjon.pselv.publisering.support.ByggeklossFormData;
import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

/**
 * Helper used by PUS002 Din pensjon for population byggeklosser.
 *
 * */
public class ByggeklossHelper {

    /**
     * Initialize the list of byggekloss elements to show in view. The elements to show in view is based on the user's user
     * group, ranging from 10 to 90
     *
     * @param dinPensjonForm The form to get the user group from
     * @return A list of the byggekloss elements that should be visible in view for this particular user
     */
    public List<ByggeklossFormData> initByggeklossList(DinPensjonForm dinPensjonForm) {
        Map<Character, ByggeklossFormData> byggeklossMap = initByggeklossMap();

        List<ByggeklossFormData> byggeklossList = new ArrayList<>();
        char[] byggeklossIdsToShow = new char[] {};
        if (isVariant2()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_VARIANT_2;
        } else if (dinPensjonForm.isUserGroup10()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_10;
        } else if (dinPensjonForm.isUserGroup20()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_20;
        } else if (dinPensjonForm.isUserGroup30()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_30;
        } else if (dinPensjonForm.isUserGroup40()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_40;
        } else if (dinPensjonForm.isUserGroup50()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_50;
        } else if (dinPensjonForm.isUserGroup60()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_60;
        } else if (dinPensjonForm.isUserGroup70()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_70;
        } else if (dinPensjonForm.isUserGroup80()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_80;
        } else if (dinPensjonForm.isUserGroup90()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_90;
        } else if (dinPensjonForm.isUserGroup91()) {
            byggeklossIdsToShow = DinPensjonConstants.BYGGEKLOSS_LIST_USERGROUP_91;
        }

        for (char element : byggeklossIdsToShow) {
            byggeklossList.add(byggeklossMap.get(element));
        }

        return byggeklossList;
    }

    /**
     * Initialize the byggekloss elements
     *
     * @return A map with the initialized elements. The elements' keys are their id in design, for instance the key to get the
     * Saksoversikt byggekloss is 'A'.
     */
    private Map<Character, ByggeklossFormData> initByggeklossMap() {
        Map<Character, ByggeklossFormData> byggeklossMap = new HashMap<>();

        ByggeklossFormData byggeklossA = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_A,
                DinPensjonConstants.ACTION_BYGGEKLOSS_A, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_A,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_A);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_A, byggeklossA);

        ByggeklossFormData byggeklossB = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_B,
                DinPensjonConstants.ACTION_BYGGEKLOSS_B, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_B,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_B);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_B, byggeklossB);

        ByggeklossFormData byggeklossC = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_C,
                DinPensjonConstants.ACTION_BYGGEKLOSS_C, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_C,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_C);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_C, byggeklossC);

        ByggeklossFormData byggeklossD = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_D,
                DinPensjonConstants.ACTION_BYGGEKLOSS_D, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_D,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_D);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_D, byggeklossD);

        ByggeklossFormData byggeklossE = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_E,
                DinPensjonConstants.ACTION_BYGGEKLOSS_E, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_E,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_E);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_E, byggeklossE);

        ByggeklossFormData byggeklossF = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_F,
                DinPensjonConstants.ACTION_BYGGEKLOSS_F, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_F,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_F);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_F, byggeklossF);

        ByggeklossFormData byggeklossG = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_G,
                DinPensjonConstants.ACTION_BYGGEKLOSS_G, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_G,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_G);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_G, byggeklossG);

        ByggeklossFormData byggeklossH = createByggekloss(DinPensjonConstants.ID_BYGGEKLOSS_H,
                DinPensjonConstants.ACTION_BYGGEKLOSS_H, DinPensjonConstants.ALT_KEY_BYGGEKLOSS_H,
                DinPensjonConstants.TITLE_KEY_BYGGEKLOSS_H);
        byggeklossMap.put(DinPensjonConstants.ID_BYGGEKLOSS_H, byggeklossH);

        return byggeklossMap;
    }

    /**
     * Utility method for creating a byggekloss.
     *
     * @param id The byggekloss' id
     * @param action The byggekloss' action, what action should be submitted when the user selects the element
     * @param altKey The key (in resources.properties) to the byggekloss' image's alt text.
     * @param titleKey The key (in resources.properties) to the byggekloss' tooltip text.
     * @return A complete byggekloss
     */
    private ByggeklossFormData createByggekloss(char id, String action, String altKey, String titleKey) {
        ByggeklossFormData byggekloss = new ByggeklossFormData();
        byggekloss.setId(id);
        byggekloss.setAction(action);
        byggekloss.setAlt(altKey);
        byggekloss.setTitle(titleKey);
        return byggekloss;
    }

    /**
     * Find the view's variant. Unless the user has begrenset fullmakt, the variant to show is variant
     * 1.
     *
     * @return the variant
     */
    private boolean isVariant2() {
        return PSELVSecurityFunctions.isFullmakt();
    }
}

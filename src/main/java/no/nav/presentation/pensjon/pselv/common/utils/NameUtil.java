package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.StringTokenizer;

public class NameUtil {

    /**
     * Formats the input parameters into a name with the firts letter in uppercase and the rest in lowercase.
     * Handles '-' and ' ' in the names. I.e KEN-SVERRE will be Ken-Sverre
     */
    public String formatName(String givenname, String middlename, String sirname) {
        StringBuffer name = new StringBuffer();
        name.append(this.formatName(givenname));

        if (givenname != null && !"".equalsIgnoreCase(givenname)) {
            if (middlename != null && !"".equalsIgnoreCase(middlename)) {
                name.append(" ");
            }
        }

        name.append(this.formatName(middlename));

        if (givenname != null && !"".equalsIgnoreCase(givenname) || middlename != null && !"".equalsIgnoreCase(middlename)) {
            if (sirname != null && !"".equalsIgnoreCase(sirname)) {
                name.append(" ");
            }
        }

        name.append(this.formatName(sirname));
        return name.toString();
    }

    /**
     * Handles '-' and ' ' in the names. I.e KEN-SVERRE will be Ken-Sverre
     */
    public String formatName(String name) {
        StringBuffer buffer = new StringBuffer();

        if (null != name && !"".equalsIgnoreCase(name)) {
            if (name.indexOf('-') > 0) {
                buffer.append(formatSeperatedName(name, "-"));
            } else if (name.indexOf(' ') > 0) {
                buffer.append(formatSeperatedName(name, " "));
            } else {
                buffer.append(format(name));
            }
        }

        return buffer.toString();
    }

    /**
     * Formats a seperated name by capitalizing first letter and every letter after the seperators
     * <ul>
     * <li>Dash (-)</li>
     * <li>Blank ( )</li>
     * </ul>
     */
    private String formatSeperatedName(String name, String separator) {
        StringBuffer result = new StringBuffer();
        StringTokenizer tokenizer = new StringTokenizer(name, separator);

        while (tokenizer.hasMoreElements()) {
            String token = tokenizer.nextToken();
            result.append(format(token));
            if (tokenizer.hasMoreElements()) {
                result.append(separator);
            } else if (name.substring(name.length() - 1).equalsIgnoreCase(separator)) {
                result.append(separator);
            }
        }

        return result.toString();
    }

    /**
     * Formats a string to have uppercase in first letter,and lowercase in the rest
     */
    private String format(String name) {
        String formattedName = "";

        if (name == null || "".equalsIgnoreCase(name)) {
            return formattedName;
        }

        formattedName = name.toLowerCase();
        formattedName = name.substring(0, 1).toUpperCase() + formattedName.substring(1);
        return formattedName;
    }
}

package no.nav.presentation.pensjon.pselv.common.utils;

public class AccountNumberUtil {

    public AccountNumberUtil() {
    }

    /**
     * Formats a 11 digit account number to the form 'xxxx xx xxxxx'. If the account number is invalid (not 11 consecutive
     * digits possibly containing spaces) the original <code>kontonr</code> is returned.
     */
    public String getFormattedAccountNumber(String accountNumber) {
        String acn = accountNumber.replaceAll(" ", "");

        return acn.matches("^\\d{11}$")
                ? String.format("%s %s %s", acn.substring(0, 4), acn.substring(4, 6), acn.substring(6))
                : accountNumber;
    }

    /**
     * Formats a 11 digit account number to the form 'xxxx xx xxxxx'. If the account number is invalid (not 11 consecutive
     * digits possibly containing spaces) the original <code>kontonr</code> is returned.
     * If <code>accountNumber</code> is <code>null</code> an empty string is returned.
     */
    public String getFormattedAccountNumberNullSafe(String accountNumber) {
        return accountNumber == null ? "" : getFormattedAccountNumber(accountNumber);
    }
}

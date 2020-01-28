package no.nav.presentation.pensjon.pselv.common.tekster;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import no.nav.presentation.pensjon.common.taglib.help.HelpUtils;

/**
 * TeksterMessageSource - Resolves incoming MessageSource code with value from Help URL Path.
 *
 */
public class TeksterMessageSource extends ReloadableResourceBundleMessageSource {

    private String helpurlPath;

    /**
     * Resolves messages in the following order - returns first found
     * <ol>
     * <li>System properties</li>
     * <li>System environment properties</li>
     * <li>ReloadableResourceBundleMessageSource (super)</li>
     * <li>Help URL</li>
     * </ol>
     *
     * @param code code
     * @param locale locale
     * @return first result from the list above
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String result = resolveSystemPropertyOrEnvironmentVariable(code);

        if (result == null) {
            MessageFormat messageFormat = super.resolveCode(code, locale);

            if (messageFormat == null) {
                String msg = findHelpURLPath(code);

                if (msg != null) {
                    messageFormat = new MessageFormat(msg, locale);
                }
            }

            return messageFormat;
        }

        return new MessageFormat(result, locale);
    }

    /**
     * Resolves messages in the following order - returns first found
     * <ol>
     * <li>System properties</li>
     * <li>System environment properties</li>
     * <li>ReloadableResourceBundleMessageSource (super)</li>
     * <li>Help URL</li>
     * </ol>
     *
     * @param code code
     * @param locale locale
     * @return first result from the list above
     */
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = resolveSystemPropertyOrEnvironmentVariable(code);

        if (result == null) {
            result = super.resolveCodeWithoutArguments(code, locale);
        }

        if (result == null) {
            result = findHelpURLPath(code);
        }

        return result;
    }

    /**
     * Looks through system properties then enviroment variables for the given key
     *
     * @param key key
     * @return system property or system environment variable. Will return null if not found.
     */
    private String resolveSystemPropertyOrEnvironmentVariable(String key) {
        String msg = getSystemProperty(key);
        if (msg == null) {
            msg = getEnvironmentProperty(key);
        }
        return msg;
    }

    String getSystemProperty(final String key) {
        return System.getProperty(key);
    }

    String getEnvironmentProperty(final String key) {
        return System.getenv().get(key);
    }

    private String findHelpURLPath(Object key) {
        return HelpUtils.HELP.equals(key) ? helpurlPath : null;
    }

    public void setHelpurlPath(String helpurlPath) {
        this.helpurlPath = helpurlPath;
    }
}

package no.nav.presentation.pensjon.pselv.common.tekster;

import java.text.MessageFormat;
import java.util.Locale;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import no.nav.presentation.pensjon.common.taglib.help.HelpUtils;

/**
 * TeksterMessageSource - Resolves incoming MessageSource code with value from Help URL Path.
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
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        String result = resolveSystemPropertyOrEnvironmentVariable(code);

        if (result != null) {
            return new MessageFormat(result, locale);
        }

        MessageFormat messageFormat = super.resolveCode(code, locale);

        if (messageFormat != null) {
            return messageFormat;
        }

        String message = findHelpURLPath(code);
        return message == null ? null : new MessageFormat(message, locale);
    }

    /**
     * Resolves messages in the following order - returns first found
     * <ol>
     * <li>System properties</li>
     * <li>System environment properties</li>
     * <li>ReloadableResourceBundleMessageSource (super)</li>
     * <li>Help URL</li>
     * </ol>
     */
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = resolveSystemPropertyOrEnvironmentVariable(code);

        if (result == null) {
            result = super.resolveCodeWithoutArguments(code, locale);
        }

        return result == null ? findHelpURLPath(code) : result;
    }

    /**
     * Looks through system properties then environment variables for the given key.
     */
    private String resolveSystemPropertyOrEnvironmentVariable(String key) {
        String message = getSystemProperty(key);
        return message == null ? getEnvironmentProperty(key) : message;
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

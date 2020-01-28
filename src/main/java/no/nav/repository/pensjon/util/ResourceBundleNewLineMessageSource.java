package no.nav.repository.pensjon.util;

import java.util.Locale;

/**
 * The {@link ResourceBundleNewLineMessageSource} is a {@link org.springframework.context.MessageSource} that inserts
 * newline characters at particular points in the messages it serves.
 */
public class ResourceBundleNewLineMessageSource extends EnvironmentFirstResourceBundleMessageSource {
    private final String newLinePlaceHolder;
    private final String newLine = System.getProperty("line.separator");

    public ResourceBundleNewLineMessageSource(final String newLinePlaceHolder) {
        this.newLinePlaceHolder = newLinePlaceHolder;
    }

    @Override
    protected String getMessageInternal(String code, Object[] args, Locale locale) {
        String message = super.getMessageInternal(code, args, locale);

        if (message != null) {
            return message.replaceAll(newLinePlaceHolder, newLine);
        }

        return code; //TODO restore
//        return message;
    }
}

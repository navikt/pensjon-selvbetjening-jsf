package no.nav.repository.pensjon.util;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

/**
 * {@link org.springframework.context.MessageSource} implementation that
 * accesses resource bundles using specified basenames. This class relies
 * on the underlying JDK's {@link java.util.ResourceBundle} implementation,
 * in combination with the JDK's standard message parsing provided by {@link java.text.MessageFormat}.
 * <p>
 * This MessageSource caches both the accessed ResourceBundle instances and the generated MessageFormats for each message. It also implements rendering of no-arg messages without
 * MessageFormat, as supported by the AbstractMessageSource base class. The caching provided by this MessageSource is significantly faster than the built-in caching of the
 * {@code java.util.ResourceBundle} class.
 * <p>
 * Unfortunately, {@code java.util.ResourceBundle} caches loaded bundles forever: Reloading a bundle during VM execution is <i>not</i> possible. As this MessageSource relies on
 * ResourceBundle, it faces the same limitation. Consider {@link ReloadableResourceBundleMessageSource} for an alternative that is capable of refreshing the underlying bundle
 * files.
 */
public class EnvironmentFirstResourceBundleMessageSource extends AbstractMessageSource implements BeanClassLoaderAware {

    private String[] basenames = new String[0];
    private long cacheMillis = -1;
    private ClassLoader bundleClassLoader;
    private ClassLoader beanClassLoader = ClassUtils.getDefaultClassLoader();

    /**
     * Cache to hold loaded ResourceBundles.
     * This Map is keyed with the bundle basename, which holds a Map that is
     * keyed with the Locale and in turn holds the ResourceBundle instances.
     * This allows for very efficient hash lookups, significantly faster
     * than the ResourceBundle class's own cache.
     */
    private final Map<String, Map<Locale, ResourceBundle>> cachedResourceBundles = new HashMap<>();

    /**
     * Cache to hold already generated MessageFormats.
     * This Map is keyed with the ResourceBundle, which holds a Map that is
     * keyed with the message code, which in turn holds a Map that is keyed
     * with the Locale and holds the MessageFormat values. This allows for
     * very efficient hash lookups without concatenated keys.
     */
    private final Map<ResourceBundle, Map<String, Map<Locale, MessageFormat>>> cachedBundleMessageFormats = new HashMap<>();

    /**
     * Set a single basename, following {@link java.util.ResourceBundle} conventions:
     * essentially, a fully-qualified classpath location. If it doesn't contain a
     * package qualifier (such as {@code org.mypackage}), it will be resolved
     * from the classpath root.
     * <p>
     * Messages will normally be held in the "/lib" or "/classes" directory of a web application's WAR structure. They can also be held in jar files on the class path.
     * <p>
     * Note that ResourceBundle names are effectively classpath locations: As a consequence, the JDK's standard ResourceBundle treats dots as package separators. This means that
     * "test.theme" is effectively equivalent to "test/theme", just like it is for programmatic {@code java.util.ResourceBundle} usage.
     */
    public void setBasename(String basename) {
        setBasenames(basename);
    }

    /**
     * Set an array of basenames, each following {@link java.util.ResourceBundle} conventions: essentially, a fully-qualified classpath location. If it
     * doesn't contain a package qualifier (such as {@code org.mypackage}),
     * it will be resolved from the classpath root.
     * <p>
     * The associated resource bundles will be checked sequentially when resolving a message code. Note that message definitions in a <i>previous</i> resource bundle will override
     * ones in a later bundle, due to the sequential lookup.
     * <p>
     * Note that ResourceBundle names are effectively classpath locations: As a consequence, the JDK's standard ResourceBundle treats dots as package separators. This means that
     * "test.theme" is effectively equivalent to "test/theme", just like it is for programmatic {@code java.util.ResourceBundle} usage.
     */
    public void setBasenames(String... basenames) {
        if (basenames == null) {
            this.basenames = new String[0];
            return;
        }

        this.basenames = new String[basenames.length];

        for (int index = 0; index < basenames.length; index++) {
            String basename = basenames[index];
            Assert.hasText(basename, "Basename must not be empty");
            this.basenames[index] = basename.trim();
        }
    }

    /**
     * Set the number of seconds to cache loaded resource bundle files.
     * <ul>
     * <li>Default is "-1", indicating to cache forever.
     * <li>A positive number will expire resource bundles after the given number of seconds. This is essentially the interval between refresh checks. Note that a refresh attempt
     * will first check the last-modified timestamp of the file before actually reloading it; so if files don't change, this interval can be set rather low, as refresh attempts
     * will not actually reload.
     * <li>A value of "0" will check the last-modified timestamp of the file on every message access. <b>Do not use this in a production environment!</b>
     * <li><b>Note that depending on your ClassLoader, expiration might not work reliably since the ClassLoader may hold on to a cached version of the bundle file.</b> Consider
     * {@link ReloadableResourceBundleMessageSource} in combination with resource bundle files in a non-classpath location.
     * </ul>
     * <p>
     * <b>NOTE: Only works on JDK 1.6 and higher.</b> Consider using {@link ReloadableResourceBundleMessageSource} for JDK 1.5 support and more flexibility in terms of the kinds of
     * resources to load from (in particular from outside of the classpath where expiration works reliably).
     */
    public void setCacheSeconds(int cacheSeconds) {
        cacheMillis = cacheSeconds * 1000;
    }

    /**
     * Set the ClassLoader to load resource bundles with.
     * <p>
     * Default is the containing BeanFactory's {@link org.springframework.beans.factory.BeanClassLoaderAware bean ClassLoader}, or the default ClassLoader determined by
     * {@link org.springframework.util.ClassUtils#getDefaultClassLoader()} if not running within a BeanFactory.
     */
    public void setBundleClassLoader(ClassLoader classLoader) {
        bundleClassLoader = classLoader;
    }

    /**
     * Return the ClassLoader to load resource bundles with.
     * <p>
     * Default is the containing BeanFactory's bean ClassLoader.
     */
    protected ClassLoader getBundleClassLoader() {
        return bundleClassLoader == null ? beanClassLoader : bundleClassLoader;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        beanClassLoader = classLoader == null ? ClassUtils.getDefaultClassLoader() : classLoader;
    }

    /**
     * Resolves the given message code as key in the registered resource bundles,
     * returning the value found in the bundle as-is (without MessageFormat parsing).
     */
    @Override
    protected String resolveCodeWithoutArguments(String code, Locale locale) {
        String result = null;

        for (int index = 0; result == null && index < basenames.length; index++) {
            ResourceBundle bundle = getResourceBundle(basenames[index], locale);

            if (bundle != null) {
                result = getStringOrNull(bundle, code);
            }
        }

        return result;
    }

    /**
     * Resolves the given message code as key in the registered resource bundles,
     * using a cached MessageFormat instance per message code.
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessageFormat messageFormat = null;

        for (int index = 0; messageFormat == null && index < basenames.length; index++) {
            ResourceBundle bundle = getResourceBundle(basenames[index], locale);

            if (bundle != null) {
                messageFormat = getMessageFormat(bundle, code, locale);
            }
        }

        return messageFormat;
    }

    /**
     * Return a ResourceBundle for the given basename and code,
     * fetching already generated MessageFormats from the cache.
     */
    protected ResourceBundle getResourceBundle(String basename, Locale locale) {
        if (cacheMillis >= 0) {
            // Fresh ResourceBundle.getBundle call in order to let ResourceBundle
            // do its native caching, at the expense of more extensive lookup steps.
            return doGetBundle(basename, locale);
        }
        // Cache forever: prefer locale cache over repeated getBundle calls.
        synchronized (cachedResourceBundles) {
            Map<Locale, ResourceBundle> localeMap = cachedResourceBundles.get(basename);

            if (localeMap != null) {
                ResourceBundle bundle = localeMap.get(locale);

                if (bundle != null) {
                    return bundle;
                }
            }

            try {
                ResourceBundle bundle = doGetBundle(basename, locale);

                if (localeMap == null) {
                    localeMap = new HashMap<Locale, ResourceBundle>();
                    cachedResourceBundles.put(basename, localeMap);
                }

                localeMap.put(locale, bundle);
                return bundle;
            } catch (MissingResourceException ex) {
                if (logger.isWarnEnabled()) {
                    logger.warn("ResourceBundle [" + basename + "] not found for MessageSource: " + ex.getMessage());
                }
                // Assume bundle not found
                // -> do NOT throw the exception to allow for checking parent message source.
                return null;
            }
        }
    }

    /**
     * Obtain the resource bundle for the given basename and Locale.
     */
    protected ResourceBundle doGetBundle(String basename, Locale locale) throws MissingResourceException {
        return ResourceBundle.getBundle(basename, locale, getBundleClassLoader());
    }

    /**
     * Return a MessageFormat for the given bundle and code,
     * fetching already generated MessageFormats from the cache.
     */
    protected MessageFormat getMessageFormat(ResourceBundle bundle, String code, Locale locale)
            throws MissingResourceException {

        synchronized (cachedBundleMessageFormats) {
            Map<String, Map<Locale, MessageFormat>> codeMap = cachedBundleMessageFormats.get(bundle);
            Map<Locale, MessageFormat> localeMap = null;

            if (codeMap != null) {
                localeMap = codeMap.get(code);

                if (localeMap != null) {
                    MessageFormat result = localeMap.get(locale);

                    if (result != null) {
                        return result;
                    }
                }
            }

            String message = getStringOrNull(bundle, code);

            if (message == null) {
                return null;
            }

            if (codeMap == null) {
                codeMap = new HashMap<String, Map<Locale, MessageFormat>>();
                cachedBundleMessageFormats.put(bundle, codeMap);
            }

            if (localeMap == null) {
                localeMap = new HashMap<Locale, MessageFormat>();
                codeMap.put(code, localeMap);
            }

            MessageFormat result = createMessageFormat(message, locale);
            localeMap.put(locale, result);
            return result;
        }
    }

    private String getStringOrNull(ResourceBundle bundle, String key) {
        try {
            String message = System.getProperty(key);

            if (message == null) {
                message = System.getenv().get(key);
            }

            if (message == null) {
                message = bundle.getString(key);
            }

            return message;
        } catch (MissingResourceException | IllegalArgumentException ex) {
            // Assume key not found
            // -> do NOT throw the exception to allow for checking parent message source.
            return null;
        }
    }

    @Override
    public String toString() {
        return getClass().getName() + ": basenames=[" + StringUtils.arrayToCommaDelimitedString(basenames) + "]";
    }
}

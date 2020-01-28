package no.nav.consumer.pensjon.common.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * Helper for cache in Consumer integration of PEN, PSAK and PSELV.
 */
public class CacheHelper {

    private static final Log LOG = LogFactory.getLog(CacheHelper.class);
    private static final String UNDER_KEY = " under key ";
    private static final String FROM_CACHE = " from cache ";
    private CacheManager manager;

    public void cache(String cachename, Object toBeCached, String key) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Trying to cache element " + toBeCached + UNDER_KEY + key);
        }

        Cache cache = manager.getCache(cachename);

        if (cache != null) {
            Element element = new Element(key, toBeCached);
            cache.put(element);
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Element " + toBeCached + " successfully cached under key " + key);
        }
    }

    public Object get(String cachename, String key) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Trying to retrieve element with key " + key + FROM_CACHE + cachename);
        }

        Cache cache = manager.getCache(cachename);

        if (cache == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No element found in cache " + cachename + UNDER_KEY + key);
            }

            return null;
        }

        Element element = cache.get(key);

        if (element == null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("No element found in cache " + cachename + UNDER_KEY + key);
            }
            return null;
        }

        Object obj = element.getObjectValue();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Retrieved " + obj + FROM_CACHE + cachename + UNDER_KEY + key);
        }

        return obj;
    }

    public void removeElement(String cachename, String key) {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Trying to remove element with key " + key + FROM_CACHE + cachename);
        }

        Cache cache = manager.getCache(cachename);

        if (cache == null) {
            return;
        }

        cache.remove(key);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Removed element with key " + key + FROM_CACHE + cachename);
        }
    }

    public void setManager(CacheManager manager) {
        this.manager = manager;
    }
}

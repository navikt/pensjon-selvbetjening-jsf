package no.nav.repository.pensjon.common.cache;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

/**
 * Empty Cache Event Listener which should be extended when creating new Listeners to reduce the number of empty methods.
 */
public class DefaultCacheEventListener implements CacheEventListener {
    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) throws CacheException {
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) throws CacheException {
    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
    }

    @Override
    public void dispose() {
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

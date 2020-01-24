package no.nav.repository.pensjon.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.ehcache.CacheException;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

public class LoggingElementPutCacheEventListener extends DefaultCacheEventListener {

    private final Logger logger = LoggerFactory.getLogger(LoggingElementPutCacheEventListener.class);

    @Override
    public void notifyElementPut(Ehcache cache, Element element) throws CacheException {
        if (!logger.isTraceEnabled() || cache == null || element == null) {
            return;
        }

        logger.trace("Caching object: {} in cache: {} with key: {}", element.getObjectValue(), cache.getName(), element.getObjectKey());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

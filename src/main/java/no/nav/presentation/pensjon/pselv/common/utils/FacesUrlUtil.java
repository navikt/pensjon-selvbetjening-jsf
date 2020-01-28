package no.nav.presentation.pensjon.pselv.common.utils;

import static java.util.Arrays.stream;

import static org.springframework.util.StringUtils.isEmpty;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Map;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

/**
 * Utilities for handling URLs in a JavaServer Faces context.
 */
@SuppressWarnings("SameParameterValue")
public class FacesUrlUtil {

    private static final String URL_ENCODING = "UTF-8";
    private static final String PARAM_SEPARATOR = "&";
    private static final String KEY_VALUE_SEPARATOR = "=";
    private static final String SOURCE_PARAM_KEY = "from";
    private static final String REFERRER_HEADER_KEY = "Referer"; // Note misspelling of "Referrer"

    /**
     * Gets the URL that can be used to get back to the current page after a redirect.
     * An optional source can be included in the back URL to indicate from where the back URL is used.
     * The URL is encoded so that it can be used as a query parameter in the redirection URL.
     */
    public static String getEncodedBackUrl(FacesContext facesContext, String source) {
        return encodeUrl(getBackUrl(facesContext, source));
    }

    /**
     * Determines whether the current page is requested from a given source.
     * Typically used in conjunction with {@link #getEncodedBackUrl(FacesContext, String) getEncodedBackUrl}} for handling redirection;
     * it may then be arranged for the back URL to include a source identifier.
     */
    public static boolean comingFrom(String source, FacesContext facesContext) {
        ExternalContext externalContext = facesContext.getExternalContext();

        if (externalContext == null) {
            return false;
        }

        Map<String, String> headerMap = externalContext.getRequestHeaderMap();

        if (headerMap == null) {
            return false;
        }

        String referrer = headerMap.get(REFERRER_HEADER_KEY);
        String from = getQueryParamValue(referrer, SOURCE_PARAM_KEY);
        return source.equalsIgnoreCase(from);
    }

    private static String getBackUrl(FacesContext context, String source) {
        String currentPageUrl = getUrlWithQueryParams(getHttpServletRequest(context));
        String appendage = isEmpty(source) ? "" : makeQueryParamAppendage(SOURCE_PARAM_KEY, source);
        return currentPageUrl + appendage;
    }

    private static String makeQueryParamAppendage(String key, String value) {
        return PARAM_SEPARATOR + makeQueryParam(key, value);
    }

    private static String makeQueryParam(String key, String value) {
        return key + KEY_VALUE_SEPARATOR + value;
    }

    private static HttpServletRequest getHttpServletRequest(FacesContext context) {
        return (HttpServletRequest) context.getExternalContext().getRequest();
    }

    private static String getUrlWithQueryParams(HttpServletRequest request) {
        return String.format("%s?%s", request.getRequestURL(), request.getQueryString());
    }

    private static String encodeUrl(String url) {
        try {
            return URLEncoder.encode(url, URL_ENCODING);
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    private static String getQueryParamValue(String url, String paramKey) {
        if (isEmpty(url)) {
            return null;
        }

        String queryString = URI.create(url).getQuery();

        if (isEmpty(queryString)) {
            return null;
        }

        String[] params = queryString.split(PARAM_SEPARATOR);

        return stream(params)
                .filter(p -> p.startsWith(paramKey + KEY_VALUE_SEPARATOR))
                .findFirst()
                .map(kv -> kv.split(KEY_VALUE_SEPARATOR)[1])
                .orElse(null);
    }

    private FacesUrlUtil() {
    }
}

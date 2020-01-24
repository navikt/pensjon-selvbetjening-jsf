package no.nav.domain.pensjon.common.cm;

import java.io.Serializable;

/**
 * Hold information retrieved from the content management system.
 */
public class Content implements Serializable {

    private static final long serialVersionUID = -1268220235007591593L;
    private String contentKey;
    private String text;
    private String url;

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

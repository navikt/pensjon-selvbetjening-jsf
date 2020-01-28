package no.nav.pensjon.selv.service.fake;

import no.stelvio.common.codestable.support.AbstractCodesTableItem;

public class FakeCodesTableItem<K extends Enum, V> extends AbstractCodesTableItem<K, V> {

    private static final long serialVersionUID = -2501698338040432765L;
    private String id;
    private String code;

    protected FakeCodesTableItem() {
    }

    public String getId() {
        return this.id;
    }

    public String getCodeAsString() {
        return this.code;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCodeAsString(String code) {
        this.code = code;
    }
}

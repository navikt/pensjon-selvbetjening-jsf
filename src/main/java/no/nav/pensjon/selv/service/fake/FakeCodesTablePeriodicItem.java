package no.nav.pensjon.selv.service.fake;

import no.stelvio.common.codestable.CodesTablePeriodicItem;
import no.stelvio.common.codestable.support.AbstractCodesTableItem;

public class FakeCodesTablePeriodicItem<K extends Enum, V> extends CodesTablePeriodicItem<K, V> {

    private static final long serialVersionUID = -2501698338040432765L;
    private String id;
    private String code;

    protected FakeCodesTablePeriodicItem() {
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

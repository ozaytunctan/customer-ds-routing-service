package tr.otunctan.customer.service.domain.model.base;

import tr.otunctan.customer.service.domain.valueobjects.BaseId;

public abstract class BaseEntity<ID extends BaseId<?>> {
    private ID id;

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }

    public <V> V getIdValue() {
        if (getId() == null) {
            return null;
        }
        return (V) getId().getValue();
    }
}

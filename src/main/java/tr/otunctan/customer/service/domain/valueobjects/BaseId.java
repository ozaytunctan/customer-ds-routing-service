package tr.otunctan.customer.service.domain.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public abstract class BaseId<ID> implements Serializable {
    private ID value;

    public BaseId(ID value) {
        this.value = value;
    }

    public ID getValue() {
        return value;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseId<?> baseId = (BaseId<?>) o;
        return Objects.equals(value, baseId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

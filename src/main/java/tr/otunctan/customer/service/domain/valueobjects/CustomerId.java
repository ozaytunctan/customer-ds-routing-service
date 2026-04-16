package tr.otunctan.customer.service.domain.valueobjects;

import java.util.UUID;

public final class CustomerId extends BaseId<UUID> {
    private CustomerId(UUID value) {
        super(value);
    }
    public static CustomerId generate() {
        return valueOf(UUID.randomUUID());
    }
    public static CustomerId valueOf(UUID id) {
        return new CustomerId(id);
    }
}

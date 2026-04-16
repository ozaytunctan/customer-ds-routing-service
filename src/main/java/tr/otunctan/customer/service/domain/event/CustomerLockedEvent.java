package tr.otunctan.customer.service.domain.event;

import tr.otunctan.customer.service.domain.model.base.Customer;

import java.time.ZonedDateTime;

public class CustomerLockedEvent extends CustomerDomainEvent {
    public CustomerLockedEvent(Customer customer, ZonedDateTime createdAt) {
        super(customer, createdAt);
    }
}

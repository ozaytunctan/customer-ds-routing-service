package tr.otunctan.customer.service.domain.event;

import tr.otunctan.customer.service.domain.model.base.Customer;

import java.time.ZonedDateTime;

public class CustomerDomainEvent implements DomainEvent{
    private Customer customer;
    private ZonedDateTime createdAt;
    public CustomerDomainEvent(Customer customer, ZonedDateTime createdAt) {
        this.customer = customer;
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }
}

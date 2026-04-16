package tr.otunctan.customer.service.domain.model.base;

import tr.otunctan.customer.service.domain.valueobjects.BaseId;
import tr.otunctan.customer.service.domain.event.DomainEvent;

import java.util.ArrayList;
import java.util.List;

public abstract class AggregateRootWithEvents<ID extends BaseId<?>>
        extends AggregateRoot<ID> {
    private final List<DomainEvent> domainEvents = new ArrayList<>();

    protected void registerEvent(DomainEvent event) {
        domainEvents.add(event);
    }

    public List<DomainEvent> pullEvents() {
        return List.copyOf(domainEvents);
    }

    public void clearEvents() {
        domainEvents.clear();
    }

    public boolean hasEvents() {
        return !domainEvents.isEmpty();
    }
}
package tr.otunctan.customer.service.domain.repository;

import tr.otunctan.customer.service.domain.model.base.Customer;
import tr.otunctan.customer.service.domain.valueobjects.CustomerId;
import tr.otunctan.customer.service.domain.valueobjects.Email;

import java.util.Optional;

public interface CustomerRepositoryPort {
    Customer save(Customer customer);

    void deleteById(CustomerId customerId);
}

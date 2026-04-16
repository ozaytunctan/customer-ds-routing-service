package tr.otunctan.customer.service.infrastructure.persistence.adapters;

import org.springframework.stereotype.Component;
import tr.otunctan.customer.service.domain.repository.CustomerRepositoryPort;
import tr.otunctan.customer.service.domain.model.base.Customer;
import tr.otunctan.customer.service.domain.valueobjects.CustomerId;
import tr.otunctan.customer.service.domain.valueobjects.Email;
import tr.otunctan.customer.service.infrastructure.persistence.entity.jpa.CustomerJpaEntity;
import tr.otunctan.customer.service.infrastructure.persistence.repository.jpa.CustomerJpaRepository;

import java.util.Optional;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {
    private final CustomerJpaRepository customerCommandJpaRepository;

    public CustomerRepositoryAdapter(CustomerJpaRepository customerCommandJpaRepository) {
        this.customerCommandJpaRepository = customerCommandJpaRepository;
    }

    @Override
    public Optional<Customer> findById(CustomerId customerId) {
        return customerCommandJpaRepository
                .findById(customerId.getValue())
                .map(CustomerJpaEntity::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(Email email) {
        return customerCommandJpaRepository.findByEmail(email.getValue())
                .map(CustomerJpaEntity::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerJpaEntity customerJpaEntity = CustomerJpaEntity.valueOf(customer);
        customerJpaEntity = customerCommandJpaRepository.save(customerJpaEntity);
        return customerJpaEntity.toDomain();
    }

    @Override
    public void deleteById(CustomerId customerId) {
        this.customerCommandJpaRepository.deleteById(customerId.getValue());
    }
}

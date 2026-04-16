package tr.otunctan.customer.service.infrastructure.persistence.adapters;

import org.springframework.stereotype.Component;
import tr.otunctan.customer.service.application.query.model.CustomerView;
import tr.otunctan.customer.service.application.ports.out.repository.CustomerQueryRepository;
import tr.otunctan.customer.service.domain.valueobjects.Email;
import tr.otunctan.customer.service.infrastructure.persistence.repository.jpa.CustomerJpaRepository;

@Component
public class CustomerQueryRepositoryAdapter implements CustomerQueryRepository {

    private final CustomerJpaRepository customerJpaRepository;

    public CustomerQueryRepositoryAdapter(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public CustomerView findByEmail(Email email) {
        return customerJpaRepository.findByEmail(email.getValue());
    }
}

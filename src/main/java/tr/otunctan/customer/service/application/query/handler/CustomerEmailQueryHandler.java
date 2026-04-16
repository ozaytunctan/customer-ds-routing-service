package tr.otunctan.customer.service.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tr.otunctan.customer.service.application.query.model.CustomerView;
import tr.otunctan.customer.service.application.ports.out.repository.CustomerQueryRepository;
import tr.otunctan.customer.service.domain.valueobjects.Email;

@Component
public class CustomerEmailQueryHandler {
    private final CustomerQueryRepository customerQueryRepository;
    public CustomerEmailQueryHandler(CustomerQueryRepository customerQueryRepository) {
        this.customerQueryRepository = customerQueryRepository;
    }

    @Transactional(readOnly = true)
    public CustomerView handle(String email) {
        return customerQueryRepository.findByEmail(Email.valueOf(email));
    }

}

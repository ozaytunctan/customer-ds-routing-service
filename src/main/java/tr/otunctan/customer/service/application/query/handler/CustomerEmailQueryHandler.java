package tr.otunctan.customer.service.application.query.handler;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tr.otunctan.customer.service.application.exceptions.CustomerNotFoundException;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;
import tr.otunctan.customer.service.application.mapper.CustomerMapper;
import tr.otunctan.customer.service.domain.repository.CustomerRepositoryPort;
import tr.otunctan.customer.service.domain.valueobjects.Email;

@Component
public  class CustomerEmailQueryHandler {
    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper customerMapper;

    public CustomerEmailQueryHandler(CustomerRepositoryPort customerRepositoryPort, CustomerMapper customerMapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.customerMapper = customerMapper;
    }

    public CustomerQueryResponse handle(String email) {
        return customerRepositoryPort.findByEmail(Email.valueOf(email))
                .map(customerMapper::customerToCustomerQueryResponse)
                .orElseThrow(CustomerNotFoundException::new);
    }

}

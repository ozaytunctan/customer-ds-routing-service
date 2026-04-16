package tr.otunctan.customer.service.application.command.handler;


import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.mapper.CustomerMapper;
import tr.otunctan.customer.service.domain.model.base.Customer;
import tr.otunctan.customer.service.domain.repository.CustomerRepositoryPort;

@Component
public class CreateCustomerCommandHandler {

    private final CustomerRepositoryPort customerRepositoryPort;
    private final CustomerMapper customerMapper;

    public CreateCustomerCommandHandler(CustomerRepositoryPort customerRepositoryPort, CustomerMapper customerMapper) {
        this.customerRepositoryPort = customerRepositoryPort;
        this.customerMapper = customerMapper;
    }

    @Transactional
    public CustomerCreateResponse handle(CreateCustomerCommand command) {
        Customer customer = customerMapper.createCustomerCommandToCustomer(command);
        customer.validateAndInitiateCustomer();

        Customer saveCustomer = this.customerRepositoryPort.save(customer);

        //burada publish edilebilir.
//        List<DomainEvent> domainEvents = saveCustomer.pullEvents();

        return customerMapper.customerToCustomerCreateResponse(saveCustomer);
    }
}

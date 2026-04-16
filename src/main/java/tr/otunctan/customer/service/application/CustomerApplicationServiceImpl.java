package tr.otunctan.customer.service.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import tr.otunctan.customer.service.application.command.handler.CreateCustomerCommandHandler;
import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;
import tr.otunctan.customer.service.application.ports.in.service.CustomerApplicationService;
import tr.otunctan.customer.service.application.query.handler.CustomerEmailQueryHandler;

@Service
public class CustomerApplicationServiceImpl implements CustomerApplicationService {

    private final CreateCustomerCommandHandler createCustomerCommandHandler;
    private final CustomerEmailQueryHandler customerEmailQueryHandler;

    public CustomerApplicationServiceImpl(CreateCustomerCommandHandler createCustomerCommandHandler, CustomerEmailQueryHandler customerEmailQueryHandler) {
        this.createCustomerCommandHandler = createCustomerCommandHandler;
        this.customerEmailQueryHandler = customerEmailQueryHandler;
    }

    @Transactional
    @Override
    public CustomerCreateResponse create(CreateCustomerCommand command) {
        return createCustomerCommandHandler.handle(command);
    }

    @Transactional(readOnly = true)
    @Override
    public CustomerQueryResponse getByEmail(String email) {
        System.out.println("TX ACTIVE INSIDE: " +
                TransactionSynchronizationManager.isActualTransactionActive());

        System.out.println("READONLY INSIDE: " +
                TransactionSynchronizationManager.isCurrentTransactionReadOnly());
        return customerEmailQueryHandler.handle(email);
    }
}

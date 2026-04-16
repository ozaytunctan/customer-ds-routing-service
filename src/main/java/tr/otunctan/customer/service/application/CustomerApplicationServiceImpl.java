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


}

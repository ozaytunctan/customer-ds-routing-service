package tr.otunctan.customer.service.application.ports.in.service;

import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;

public interface CustomerApplicationService {

    CustomerCreateResponse create(CreateCustomerCommand command);
    CustomerQueryResponse getByEmail(String email);
}

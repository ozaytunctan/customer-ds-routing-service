package tr.otunctan.customer.service.application.mapper;

import org.springframework.stereotype.Component;
import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;
import tr.otunctan.customer.service.domain.model.base.Customer;
import tr.otunctan.customer.service.domain.valueobjects.Email;

@Component
public final class CustomerMapper {
    public Customer createCustomerCommandToCustomer(CreateCustomerCommand command) {
        return Customer.builder()
                .firstName(command.getFirstName())
                .lastName(command.getLastName())
                .email(Email.valueOf(command.getEmail()))
                .build();
    }

    public CustomerCreateResponse customerToCustomerCreateResponse(Customer customer) {
        CustomerCreateResponse customerCreateResponse = new CustomerCreateResponse();
        customerCreateResponse.setEmail(customer.getEmail().getValue());
        customerCreateResponse.setId(customer.getIdValue());
        return customerCreateResponse;
    }

    public CustomerQueryResponse customerToCustomerQueryResponse(Customer customer) {
        CustomerQueryResponse customerQueryResponse = new CustomerQueryResponse();
        customerQueryResponse.setId(customer.getIdValue());
        customerQueryResponse.setFirstName(customer.getFirstName());
        customerQueryResponse.setLastName(customer.getLastName());
        customerQueryResponse.setEmail(customer.getEmail().getValue());
        return customerQueryResponse;
    }
}

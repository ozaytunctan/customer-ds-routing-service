package tr.otunctan.customer.service.api.rest.controller;

import org.springframework.web.bind.annotation.*;
import tr.otunctan.customer.service.api.rest.controller.base.BaseRestController;
import tr.otunctan.customer.service.application.command.handler.CreateCustomerCommandHandler;
import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;
import tr.otunctan.customer.service.application.query.handler.CustomerEmailQueryHandler;
import tr.otunctan.customer.service.application.query.model.CustomerView;


@RestController
@RequestMapping("/rest/api/v1/customers")
public class CustomerController extends BaseRestController {

    private final CreateCustomerCommandHandler createCustomerCommandHandler;
    private final CustomerEmailQueryHandler customerEmailQueryHandler;

    public CustomerController(CreateCustomerCommandHandler createCustomerCommandHandler, CustomerEmailQueryHandler customerEmailQueryHandler) {
        this.createCustomerCommandHandler = createCustomerCommandHandler;
        this.customerEmailQueryHandler = customerEmailQueryHandler;
    }

    @PostMapping("/create")
    public CustomerCreateResponse create(@RequestBody CreateCustomerCommand command) {
        logger.info("Create customer {}", command);
        return createCustomerCommandHandler.handle(command);
    }

    @GetMapping()
    public CustomerView get(@RequestParam("email") String email) {
        logger.info("Fetching customer {}", email);
        return customerEmailQueryHandler.handle(email);
    }
}

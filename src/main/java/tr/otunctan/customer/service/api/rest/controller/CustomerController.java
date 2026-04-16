package tr.otunctan.customer.service.api.rest.controller;

import org.springframework.web.bind.annotation.*;
import tr.otunctan.customer.service.api.rest.controller.base.BaseRestController;
import tr.otunctan.customer.service.application.dto.request.CreateCustomerCommand;
import tr.otunctan.customer.service.application.dto.response.CustomerCreateResponse;
import tr.otunctan.customer.service.application.dto.response.CustomerQueryResponse;
import tr.otunctan.customer.service.application.ports.in.service.CustomerApplicationService;


@RestController
@RequestMapping("/rest/api/v1/customers")
public class CustomerController extends BaseRestController {

    private final CustomerApplicationService customerApplicationService;

    public CustomerController(CustomerApplicationService customerApplicationService) {
        this.customerApplicationService = customerApplicationService;
    }


    @PostMapping("/create")
    public CustomerCreateResponse createCustomer(@RequestBody CreateCustomerCommand command) {
        logger.info("Create customer {}", command);
        return customerApplicationService.create(command);
    }

    @GetMapping("/search")
    public CustomerQueryResponse getCustomerByEmail(@RequestParam("email") String email) {
        logger.info("Fetching customer {}", email);
        return customerApplicationService.getByEmail(email);
    }
}

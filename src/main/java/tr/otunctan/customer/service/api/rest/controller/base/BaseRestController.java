package tr.otunctan.customer.service.api.rest.controller.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public abstract class BaseRestController {

    protected final Logger logger;
    protected BaseRestController() {
       logger= LoggerFactory.getLogger(this.getClass());
    }

}

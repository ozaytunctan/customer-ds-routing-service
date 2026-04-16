package tr.otunctan.customer.service.application.ports.out.repository;

import tr.otunctan.customer.service.application.query.model.CustomerView;
import tr.otunctan.customer.service.domain.valueobjects.Email;

public interface CustomerQueryRepository {
    CustomerView findByEmail(Email email);
}

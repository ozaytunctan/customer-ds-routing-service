package tr.otunctan.customer.service.domain.model.base;

import tr.otunctan.customer.service.domain.valueobjects.CustomerId;
import tr.otunctan.customer.service.domain.valueobjects.Email;

public class Customer extends AggregateRootWithEvents<CustomerId> {

    private String firstName;
    private String lastName;
    private Email email;

    public Customer() {
    }

    private Customer(Builder builder) {
        setId(builder.customerId);
        firstName = builder.firstName;
        lastName = builder.lastName;
        email = builder.email;
    }

    public void validateAndInitiateCustomer() {
        validateCustomer();
        initiateCustomer();
    }

    public void validateCustomer() {

    }

    public void initiateCustomer() {
        setId(CustomerId.generate());
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Email getEmail() {
        return email;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private CustomerId customerId;
        private String firstName;
        private String lastName;
        private Email email;

        public Builder() {
        }

        public Builder(Customer copy) {
            this.customerId = copy.getId();
            this.firstName = copy.getFirstName();
            this.lastName = copy.getLastName();
            this.email = copy.getEmail();
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder firstName(String val) {
            firstName = val;
            return this;
        }

        public Builder lastName(String val) {
            lastName = val;
            return this;
        }

        public Builder email(Email val) {
            email = val;
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}

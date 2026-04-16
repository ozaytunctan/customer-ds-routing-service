package tr.otunctan.customer.service.infrastructure.persistence.entity.jpa;

import jakarta.persistence.*;
import tr.otunctan.customer.service.domain.model.base.Customer;
import tr.otunctan.customer.service.domain.valueobjects.CustomerId;
import tr.otunctan.customer.service.domain.valueobjects.Email;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "customers")
public class CustomerJpaEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firstName;
    private String lastName;
    private String email;

    public static CustomerJpaEntity valueOf(Customer customer) {
        CustomerJpaEntity customerJpaEntity = new CustomerJpaEntity();
        customerJpaEntity.setId(customer.getIdValue());
        customerJpaEntity.setFirstName(customer.getFirstName());
        customerJpaEntity.setLastName(customer.getLastName());
        customerJpaEntity.setEmail(customer.getEmail().getValue());
        return customerJpaEntity;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Customer toDomain() {
        return Customer.builder()
                .customerId(CustomerId.valueOf(this.id))
                .firstName(firstName)
                .lastName(lastName)
                .email(Email.valueOf(this.email))
                .build();
    }
}

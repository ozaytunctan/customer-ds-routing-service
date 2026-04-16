package tr.otunctan.customer.service.application.dto.response;

import java.util.UUID;

public class CustomerCreateResponse {
    private UUID id;
    private String email;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

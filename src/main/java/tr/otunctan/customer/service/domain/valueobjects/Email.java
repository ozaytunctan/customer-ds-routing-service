package tr.otunctan.customer.service.domain.valueobjects;

public final class Email {

    private final String value;
    private Email(String value) {
        //validate et sonra setle
        this.value = value;
    }

    public static Email valueOf(String email) {
        return new Email(email);
    }

    public String getValue() {
        return value;
    }
}

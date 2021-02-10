package tech.dobler.springmongo;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    @Setter(AccessLevel.NONE)
    private String id;

    private String firstName;
    private String lastName;

    /**
     *  Only for hibernate
     */
    @Deprecated
    public Customer() {}

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }
}

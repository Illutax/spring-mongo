package tech.dobler.springmongo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository repoUnderTest;

    @Test
    void findByFirstName() {
        // Arrange
        repoUnderTest.deleteAll();
        var cust1 = new Customer("Albert", "Ainstain");
        var cust2 = new Customer("Bert", "Booch");
        repoUnderTest.saveAll(Arrays.asList(cust1, cust2));

        // Act
        final var customer = repoUnderTest.findByFirstName("Bert");

        // Assert
        assertNotNull(customer);
        assertThat(customer, is(cust2));
    }

    @Test
    void findByLastName() {
        // Arrange
        repoUnderTest.deleteAll();
        var cust1 = new Customer("Albert", "Smith");
        var cust2 = new Customer("Bert", "Smith");
        var cust3 = new Customer("Ciara", "Booch");
        repoUnderTest.saveAll(Arrays.asList(cust1, cust2, cust3));

        // Act
        final var customers = repoUnderTest.findByLastName("Smith");

        // Assert
        assertNotNull(customers);
        assertThat(customers.size(), is(2));
        assertThat(customers, hasItems(cust1, cust2));
    }
}
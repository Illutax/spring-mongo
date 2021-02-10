package tech.dobler.springmongo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class SpringMongoApplication implements CommandLineRunner {

    @Autowired
    CustomerRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(SpringMongoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        log.info("Customers found with findAll():");
        logDivider();
        repository.findAll().stream()
                .map(Customer::toString)
                .forEach(log::info);
        log.info("");

        // fetch an individual customer
        log.info("Customer found with findByFirstName('Alice'):");
        logDivider();
        log.info("{}", repository.findByFirstName("Alice"));

        log.info("Customer found with findByLastName('Smith'):");
        logDivider();
        repository.findByLastName("Smith").stream()
                .map(Customer::toString)
                .forEach(log::info);
    }

    private void logDivider() {
        log.info("--------------------------");
    }

}

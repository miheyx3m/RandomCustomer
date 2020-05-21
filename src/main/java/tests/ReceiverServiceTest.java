package tests;

import betcio.com.randomcustomers.dto.CustomerDto;
import betcio.com.randomcustomers.entity.Customer;
import betcio.com.randomcustomers.repository.CustomerMySQLRepository;
import betcio.com.randomcustomers.service.ServiceWriteToDB;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootApplication(scanBasePackages = {"betcio.com.randomcustomers"})
@EnableJpaRepositories(basePackages = "betcio.com.randomcustomers.repository")
@EntityScan(basePackages = "betcio.com.randomcustomers.entity")
class ReceiverServiceTest {

    private ConfigurableApplicationContext configurableApplicationContext;
    private ServiceWriteToDB service;
    private CustomerMySQLRepository customerRepository;

    private static final String FIRST_NAME = "MikhailMMMMMM";
    private static final String LAST_NAME = "Zotov";
    private static final LocalDateTime TIME = LocalDateTime.now();
    private static final CustomerDto CUSTOMER_OBJ = new CustomerDto(FIRST_NAME, LAST_NAME, TIME);

    @BeforeEach
    void setUp() {
        configurableApplicationContext = SpringApplication.run(ReceiverServiceTest.class);
        service = configurableApplicationContext.getBean(ServiceWriteToDB.class);
        customerRepository = configurableApplicationContext.getBean(CustomerMySQLRepository.class);
    }

    @AfterEach
    void tearDown() {
        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            if ((customer.getFirst_name().equals(FIRST_NAME)) &&
                    (customer.getLast_name().equals(LAST_NAME))) {
                customerRepository.deleteById(customer.getId());
            }
        }
        configurableApplicationContext.close();
    }

    @Test
    void writerToDB() {
        assertTrue(service.writerToDB(CUSTOMER_OBJ));
    }
}
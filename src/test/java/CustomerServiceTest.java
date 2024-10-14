import ing.brokeragefirm.model.Customer;
import ing.brokeragefirm.repository.CustomerRepository;
import ing.brokeragefirm.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest

public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testAddCustomer() {
        Customer customer = new Customer();
        customer.setName("Beytullah Guney");
        customer.setEmail("beytullahguney@gmail.com");

        //when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        Customer savedCustomer = customerService.addCustomer(customer);

        assertNotNull(savedCustomer);
        assertEquals("Beytullah Guney", savedCustomer.getName());
    }
}

package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class CustomerImpl implements CustomerService {

    @Override
    public Customer getCustomerId(UUID customId) {
        return Customer.builder().id(UUID.randomUUID())
                .name("Juan")
                .build();
    }

    @Override
    public Customer saveNewCustomer(Customer customer) {
        return Customer.builder()
                .id(UUID.randomUUID())
                .name(customer.getName())
                .build();
    }

    @Override
    public void updateCustomer(UUID customId, Customer customer) {
        Customer newCustomerSaved = getCustomerId(customId);
        newCustomerSaved.setName(customer.getName());

        saveNewCustomer(newCustomerSaved);
    }

    @Override
    public void deleteById(UUID customerId) {
        log.debug("Data deleted");
    }
}

package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.Customer;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerImpl implements CustomerService {

    @Override
    public Customer getCustomerId(UUID customId) {
        return Customer.builder().id(UUID.randomUUID())
                .name("Juan")
                .build();
    }
}

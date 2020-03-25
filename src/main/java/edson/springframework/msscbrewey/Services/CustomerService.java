package edson.springframework.msscbrewey.Services;

import edson.springframework.msscbrewey.web.model.Customer;

import java.util.UUID;

public interface CustomerService {
    Customer getCustomerId(UUID customId);
}

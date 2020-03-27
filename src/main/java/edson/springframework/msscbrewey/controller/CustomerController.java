package edson.springframework.msscbrewey.controller;

import edson.springframework.msscbrewey.Services.CustomerService;
import edson.springframework.msscbrewey.web.model.Customer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("/{customId}")
    ResponseEntity<Customer> getCustomer(@PathVariable("customId") UUID customId){
        return new ResponseEntity<>(customerService.getCustomerId(customId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> handlePost(@RequestBody Customer customer){
        Customer saveCustomer = customerService.saveNewCustomer(customer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "http:localhost://api/v1/customer"+saveCustomer.getId().toString() );
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    @PutMapping("/{customId}")
    public ResponseEntity<?> handlePut(@PathVariable("customId") UUID customId, @RequestBody Customer customer ){
        customerService.updateCustomer(customId, customer);
        return new ResponseEntity<>(customId, HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void handleDelete(@PathVariable("customerId") UUID customerId){
        customerService.deleteById(customerId);
    }
}

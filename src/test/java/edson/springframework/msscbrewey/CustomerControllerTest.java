package edson.springframework.msscbrewey;

import com.fasterxml.jackson.databind.ObjectMapper;
import edson.springframework.msscbrewey.Services.BeerService;
import edson.springframework.msscbrewey.Services.CustomerService;
import edson.springframework.msscbrewey.controller.CustomerController;
import edson.springframework.msscbrewey.web.model.BeerDto;
import edson.springframework.msscbrewey.web.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @MockBean
    CustomerService customerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    Customer validCustomer;

    @Before
    public void setUp(){
        validCustomer = Customer.builder().id(UUID.randomUUID())
                .name("Jose Antonio")
                .build();
    }

    @Test
    public void getBeer() throws Exception {
        given(customerService.getCustomerId(any(UUID.class))).willReturn(validCustomer);

        mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
                .andExpect(jsonPath("$.name", is("Jose Antonio")));
    }

    @Test
    public void handlePost() throws Exception {
        Customer customer = validCustomer;
        customer.setId(null);
        /*beerDto.setBeerName("Taquiña");
        beerDto.setBeerStyle("Black");
        beerDto.setUpc(78946123L);*/

        Customer saveCustomer = Customer.builder().id(UUID.randomUUID()).name("Maria Teresa").build();
        String customerDtoJson = objectMapper.writeValueAsString(customer);

        given(customerService.saveNewCustomer(any())).willReturn(saveCustomer);

        mockMvc.perform(post("/api/v1/customer/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test
    public void handleUpdate() throws Exception {
        //given
        Customer customer = validCustomer;
        String customerDtoJson = objectMapper.writeValueAsString(customer);

        //when
        mockMvc.perform(put("/api/v1/customer/" + validCustomer.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(customerDtoJson))
                .andExpect(status().isNoContent());

        then(customerService).should().updateCustomer(any(), any());

    }
}

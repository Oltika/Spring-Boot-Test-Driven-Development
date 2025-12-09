package springboot.testing.app.web.controller;

import springboot.testing.app.service.CustomerService;
import springboot.testing.app.web.error.BadRequestException;
import springboot.testing.app.web.model.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
This class is the controller for the customer service.
It is used to handle the HTTP requests for the customer service.
It is also used to handle the HTTP responses for the customer service.
*/
@RestController
@RequestMapping("/customers")
public class CustomerController {

  private final CustomerService customerService;

  public CustomerController(CustomerService customerService) {
    this.customerService = customerService;
  }

  @GetMapping
  public List<Customer> getCustomers(){
    return this.customerService.getAllCustomers();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public Customer addCustomer(@RequestBody Customer customer){
    return this.customerService.addCustomer(customer);
  }

  @GetMapping("/{id}")
  public Customer getCustomer(@PathVariable("id")String id){
    return this.customerService.getCustomer(id);
  }

  @PutMapping("/{id}")
  public Customer updateCustomer(@PathVariable("id")String id, @RequestBody Customer customer){
    if(!id.equals(customer.getCustomerId())){
      throw new BadRequestException("path variable must match incoming request id");
    }
    return this.customerService.updateCustomer(customer);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.RESET_CONTENT)
  public void deleteCustomer(@PathVariable("id") String id){
    this.customerService.deleteCustomer(id);
  }
}

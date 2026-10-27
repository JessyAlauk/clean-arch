package com.arantes.cleanarch.entrypoint.controller;

import com.arantes.cleanarch.core.usecase.DeleteCustomerByIdUseCase;
import com.arantes.cleanarch.core.usecase.FindCustomerByIdUseCase;
import com.arantes.cleanarch.core.usecase.InsertCustomerUsecase;
import com.arantes.cleanarch.core.usecase.UpdateCustomerUseCase;
import com.arantes.cleanarch.entrypoint.controller.mapper.CustomerMapper;
import com.arantes.cleanarch.entrypoint.controller.request.CustomerRequest;
import com.arantes.cleanarch.entrypoint.controller.response.CustomerResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private InsertCustomerUsecase insertCustomerUsecase;

    private FindCustomerByIdUseCase findCustomerByIdUseCase;

    private CustomerMapper customerMapper;

    private UpdateCustomerUseCase updateCustomerUseCase;

    private DeleteCustomerByIdUseCase deleteCustomerByIdUseCase;

    public CustomerController(InsertCustomerUsecase insertCustomerUsecase,
                              FindCustomerByIdUseCase findCustomerByIdUseCase,
                              CustomerMapper customerMapper,
                              UpdateCustomerUseCase updateCustomerUseCase,
                              DeleteCustomerByIdUseCase deleteCustomerByIdUseCase) {
        this.insertCustomerUsecase = insertCustomerUsecase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.customerMapper = customerMapper;
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.deleteCustomerByIdUseCase = deleteCustomerByIdUseCase;
    }


    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.toCustomer(customerRequest);
        insertCustomerUsecase.insert(customer, customerRequest.zipCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id) {
        var customer = findCustomerByIdUseCase.find(id);
        var customerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final String id,
                                       @Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.toCustomer(customerRequest);
        customer.withId(id);
        updateCustomerUseCase.update(customer, customerRequest.zipCode());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        deleteCustomerByIdUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

}

package com.arantes.cleanarch.entrypoint.controller;

import com.arantes.cleanarch.core.usecase.FindCustomerByIdUseCase;
import com.arantes.cleanarch.core.usecase.InsertCustomerUsecase;
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

    public CustomerController(InsertCustomerUsecase insertCustomerUsecase,
                              FindCustomerByIdUseCase findCustomerByIdUseCase,
                              CustomerMapper customerMapper) {
        this.insertCustomerUsecase = insertCustomerUsecase;
        this.findCustomerByIdUseCase = findCustomerByIdUseCase;
        this.customerMapper = customerMapper;
    }
    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerMapper.toCustomer(customerRequest);
        insertCustomerUsecase.insert(customer, customerRequest.zpiCode());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable final String id) {
        var customer = findCustomerByIdUseCase.find(id);
        var customerResponse = customerMapper.toCustomerResponse(customer);
        return ResponseEntity.ok().body(customerResponse);
    }

}

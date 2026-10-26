package com.arantes.cleanarch.dataprovider;

import com.arantes.cleanarch.core.dataprovider.DeleteCustomerById;
import com.arantes.cleanarch.dataprovider.repository.CustomerRepository;
import org.springframework.stereotype.Component;

@Component
public class DeleteCustomerByIdImpl implements DeleteCustomerById {

    private CustomerRepository customerRepository;

    public DeleteCustomerByIdImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void delete(String id) {
        customerRepository.deleteById(id);
    }
}

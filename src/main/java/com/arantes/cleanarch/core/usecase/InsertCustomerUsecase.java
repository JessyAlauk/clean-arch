package com.arantes.cleanarch.core.usecase;

import com.arantes.cleanarch.core.domain.Customer;

public interface InsertCustomerUsecase {

    void insert(Customer customer, String zipcode);

}

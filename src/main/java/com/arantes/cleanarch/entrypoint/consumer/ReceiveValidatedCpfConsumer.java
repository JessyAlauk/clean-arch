package com.arantes.cleanarch.entrypoint.consumer;

import com.arantes.cleanarch.core.usecase.UpdateCustomerUseCase;
import com.arantes.cleanarch.entrypoint.consumer.mapper.CustomerMessageMapper;
import com.arantes.cleanarch.entrypoint.consumer.message.CustomerMessage;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ReceiveValidatedCpfConsumer {

    private UpdateCustomerUseCase updateCustomerUseCase;

    private CustomerMessageMapper customerMessageMapper;

    public ReceiveValidatedCpfConsumer(UpdateCustomerUseCase updateCustomerUseCase,
                                       CustomerMessageMapper customerMessageMapper) {
        this.updateCustomerUseCase = updateCustomerUseCase;
        this.customerMessageMapper = customerMessageMapper;
    }

    @KafkaListener(topics = "tp-cpf-validated", groupId = "arantes")
    public void receive(CustomerMessage customerMessage){
        var customer = customerMessageMapper.toCustomer(customerMessage);
        updateCustomerUseCase.update(customer, customerMessage.getZpiCode());
    }
}

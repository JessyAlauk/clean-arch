package com.arantes.cleanarch.core.domain;

public class Customer {

    private String id;

    private String name;

    private String cpf;

    private Address address;

    private Boolean isValidCpf;

    public Customer() {
        this.isValidCpf = false;
    }

    public Customer(String id, String name, String cpf, Address address, Boolean isValidCpf) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.address = address;
        this.isValidCpf = isValidCpf;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public Address getAddress() {
        return address;
    }

    public Boolean getValidCpf() {
        return isValidCpf;
    }

    public Customer wihtAddress(Address address) {
        return new Customer(
                this.id,
                this.name,
                this.cpf,
                address,
                this.isValidCpf
        );
    }
}

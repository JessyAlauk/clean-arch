package com.arantes.cleanarch.entrypoint.consumer.message;

public class CustomerMessage {

    private String id;

    private String name;

    private String zpiCode;

    private String cpf;

    private Boolean isValidCpf;

    public CustomerMessage(String id,
                           String name,
                           String zpiCode,
                           String cpf,
                           Boolean isValidCpf) {
        this.id = id;
        this.name = name;
        this.zpiCode = zpiCode;
        this.cpf = cpf;
        this.isValidCpf = isValidCpf;
    }

    public CustomerMessage() {

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getZpiCode() {
        return zpiCode;
    }

    public String getCpf() {
        return cpf;
    }

    public Boolean getValidCpf() {
        return isValidCpf;
    }
}

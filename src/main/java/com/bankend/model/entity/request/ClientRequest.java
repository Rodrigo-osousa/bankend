package com.bankend.model.entity.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ClientRequest {

    private int id;
    
    @NotBlank(message =  "Name cannot be blank")
    private String name;
    
    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "Document Number cannot be blank")
    @Size(max=12, min=9, message = "Número do documento deve estar entre 9 a 12 dígitos")
    private String documentNumber;

    public ClientRequest() {

    }

    public ClientRequest(String name, String address, String documentNumber) {
        this.name = name;
        this.address = address;
        this.documentNumber = documentNumber;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "{" +
                ", name='" + name + '\'' +
                ", adress='" + address + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}

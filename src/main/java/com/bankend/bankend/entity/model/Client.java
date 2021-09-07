package com.bankend.bankend.entity.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private double document;


    public Client(){

    }

    public Client(int id, String name, double document) {
        super();
        this.id = id;
        this.name = name;
        this.document = document;

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

    public double getDocument() {
        return document;
    }

    public void setDocument(double document) {
        this.document = document;
    }

}

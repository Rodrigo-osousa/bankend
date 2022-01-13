package com.bankend.model.entity;


import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String accountNumber;

    private int agency;

    private Double balance;

    private Double credit;

    private Boolean inactive;

    @OneToOne
    private Customer customer;

    public Account(){

    }

    public Account(int id,String accountNumber, int agency, Double balance, Double credit, Boolean inactive, Customer customer) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.balance = balance;
        this.credit = credit;
        this.inactive = inactive;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAgency() {
        return agency;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Customer getClient() {
        return customer;
    }

    public void setClient(Customer customer) {
        this.customer = customer;
    }

    public String getAccountNumber() { return accountNumber; }

    public void setAccountNumber(String accountNumber) { this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accountNumber='" + accountNumber + '\'' +
                ", agency=" + agency +
                ", balance=" + balance +
                ", credit=" + credit +
                ", inactive=" + inactive +
                ", customer=" + customer +
                '}';
    }
}
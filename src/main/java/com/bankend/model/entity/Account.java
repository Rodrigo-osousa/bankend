package com.bankend.model.entity;


import javax.persistence.*;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;

    private int agency;

    private Double balance;

    private Double credit;

    private Boolean inactive;

    @OneToOne
    private Client client;

    public Account(){

    }

    public Account(int idAccount, int agency, Double balance, Double credit, Boolean inactive, Client client) {
        this.idAccount = idAccount;
        this.agency = agency;
        this.balance = balance;
        this.credit = credit;
        this.inactive = inactive;
        this.client = client;
    }

    public int getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(int idAccount) {
        this.idAccount = idAccount;
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "{" +
                "idAccount=" + idAccount +
                ", agency=" + agency +
                ", balance=" + balance +
                ", credit=" + credit +
                ", inactive=" + inactive +
                ", client=" + client +
                '}';
    }
}

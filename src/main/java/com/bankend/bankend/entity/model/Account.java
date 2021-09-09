package com.bankend.bankend.entity.model;


import javax.persistence.*;

@Entity

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAccount;

    private int agency;

    private int balance;

    private int credit;

    private Boolean inactive;

    @OneToOne
    private Account account;

    public Account(){

    }

    public Account(int idAccount, int agency, int balance, int credit, Boolean inactive) {
        this.idAccount = idAccount;
        this.agency = agency;
        this.balance = balance;
        this.credit = credit;
        this.inactive = inactive;
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

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Boolean getInactive() {
        return inactive;
    }

    public void setInactive(Boolean inactive) {
        this.inactive = inactive;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

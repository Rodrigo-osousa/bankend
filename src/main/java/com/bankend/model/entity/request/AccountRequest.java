package com.bankend.model.entity.request;

import javax.validation.constraints.NotBlank;

public class AccountRequest {

    @NotBlank
    private int agency;

    @NotBlank
    private Double balance;

    @NotBlank
    private Double credit;

    @NotBlank
    private Boolean inactive;

    @NotBlank
    private String documentNumber;

    public AccountRequest(){

    }

    public AccountRequest(int agency, Double balance, Double credit, Boolean inactive, String documentNumber) {
        this.agency = agency;
        this.balance = balance;
        this.credit = credit;
        this.inactive = inactive;
        this.documentNumber = documentNumber;
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "{" +
                "agency=" + agency +
                ", balance=" + balance +
                ", credit=" + credit +
                ", inactive=" + inactive +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}

package com.bankend.model.request;

import javax.validation.constraints.*;

public class AccountRequest {



    @NotNull(message = "AccountNumber cannot be null")
    @Max(value = 99999, message = "AccountNumber cannot be greater than  5 digits")
    private String accountNumber;

    @NotNull(message = "Agency cannot be null")
    @Max(value = 9999,message = "Agency cannot be greater than 4 digits")
    private int agency;

    @NotNull(message = "Balance cannot be less than 0")
    @Min(value = 0)
    private Double balance;

    @NotNull(message = "Credit cannot be null")
    @Min(value = 0, message = "Credit cannot be lass than 0")
    private Double credit;

    @NotNull(message = "Inactive cannot be null")
    private Boolean inactive;

    @NotBlank(message = "Document Number cannot be blank")
    @Size(min = 9, max = 12, message = "Document Number cannot be lass than 9 and more than 12")
    private String documentNumber;

    public AccountRequest(){

    }

    public AccountRequest( String accountNumber, int agency, Double balance, Double credit, Boolean inactive, String documentNumber) {
        this.accountNumber = accountNumber;
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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                ", accountNumber='" + accountNumber + '\'' +
                ", agency=" + agency +
                ", balance=" + balance +
                ", credit=" + credit +
                ", inactive=" + inactive +
                ", documentNumber='" + documentNumber + '\'' +
                '}';
    }
}


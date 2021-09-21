package com.bankend.model.entity.request;

import javax.validation.constraints.*;

public class AccountRequest {

    @NotNull(message = "Acgency cannot be null")
    @Max(value = 9999,message = "Agency cannot be greater than 4")
    private int agency;

    @NotNull(message = "Balance cannot be less than 0")
    @Min(0)
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

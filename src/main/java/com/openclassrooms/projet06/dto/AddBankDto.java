package com.openclassrooms.projet06.dto;

public class AddBankDto {

    private String bankName;

    private String iban;

    public AddBankDto() {
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}

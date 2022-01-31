package com.openclassrooms.projet06.dto;

public class AddContactDto {

    private String userConnected;

    private String email;

    public AddContactDto() {
    }

    public String getUserConnected() {
        return userConnected;
    }

    public void setUserConnected(String userConnected) {
        this.userConnected = userConnected;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

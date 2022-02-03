package com.openclassrooms.projet06.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "contact_user")
public class Contact extends User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    private final List<User> contact = new ArrayList<>();

    public Contact() {

    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public List<User> getUsers() {
        return contact;
    }

    public void addUsers(User user) {
        this.contact.add(user);
    }

}

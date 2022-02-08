package com.openclassrooms.projet06.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double soldes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
/*
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<Operation> operations;
*/
    public Account() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSoldes() {
        return soldes;
    }

    public void setSoldes(double soldes) {
        this.soldes = soldes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}

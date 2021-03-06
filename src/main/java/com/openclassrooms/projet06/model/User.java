package com.openclassrooms.projet06.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    private String email;

    private String password;

    /*@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Account> accounts;*/

    @ManyToMany
    @JoinTable(name="user_contact",
            joinColumns=@JoinColumn(name="contact_id"),
            inverseJoinColumns=@JoinColumn(name="user_id")
    )
    private List<User> user;

    @ManyToMany
    @JoinTable(name="user_contact",
            joinColumns=@JoinColumn(name="user_id"),
            inverseJoinColumns=@JoinColumn(name="contact_id")
    )
    private List<User> contact;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {

    }

    public User(String firstName, String lastName, String email, String password, Role role) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }


    public List<User> getUsers() {
        return user;
    }

    public List<User> getContact() {
        return contact;
    }

    public void addUsers(User user) {
        this.user.add(user);
    }

    public void addContacts(User user) {
        this.contact.add(user);
    }
    public void setContact(List<User> contact) {
        this.contact = contact;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
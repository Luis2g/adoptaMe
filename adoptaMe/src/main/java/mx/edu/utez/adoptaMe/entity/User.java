package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class User {
    
    private Integer id;
    private String username;
    private String email;
    private String password;

    public User() {
    }
    public User(Integer id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
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
    
    // foreign key for person
    @OneToOne
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;
    
    //configuration for donation
    @OneToMany(mappedBy = "donation")
    @JsonIgnore
    private List<Donation> donations;
    
    
}

package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, length = 45, unique = true)
    @NotEmpty(message = "Esta campo es requerido")
    @Size(min = 2, max = 45)
    private String username;

    @Column(name = "email", nullable = false, length = 50, unique = true)
    @NotEmpty(message = "Este campo es requerido")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$")
    @Size(min = 3, max = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
    private String password;

    @NotEmpty
    public User() {
    }


    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
    
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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
    
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public List<UserHasRole> getUserHasRoles() {
        return userHasRoles;
    }

    public void setUserHasRoles(List<UserHasRole> userHasRoles) {
        this.userHasRoles = userHasRoles;
    }

    // foreign key for person
    @OneToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name = "person_id", nullable = false, unique = true)
    private Person person;
    
    // configuration for donation
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Donation> donations;

    // configuration for UserHasRole
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserHasRole> userHasRoles; 

    // configuration for pets
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Pet> pets;

    @Override
    public String toString() {
        return "User [donations=" + donations + ", email=" + email + ", password=" + password
                + ", person=" + person + ", pets=" + pets + ", userHasRoles=" + userHasRoles + ", username=" + username
                + "]";
    }

}

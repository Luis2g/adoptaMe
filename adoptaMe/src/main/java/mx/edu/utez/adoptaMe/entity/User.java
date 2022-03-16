package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Esta campo es requerido")
    private String username;

    @Column(name = "email", nullable = false)
    @NotEmpty(message = "Este campo es requerido")
    @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$" )
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres")
    private String password;

    public User() {
    }

    public User(Long id, @NotEmpty(message = "Esta campo es requerido") String username,
            @NotEmpty(message = "Este campo es requerido") @Pattern(regexp = "^[^@]+@[^@]+\\.[a-zA-Z]{2,}$") String email,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 8, message = "La contraseña debe contener al menos 8 caracteres") String password,
            Person person, List<Donation> donations, List<UserHasRole> userHasRoles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.person = person;
        this.donations = donations;
        this.userHasRoles = userHasRoles;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
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
    
    // configuration for donation
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Donation> donations;

    // configuration for UserHasRole
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserHasRole> userHasRoles; 
    
    
}

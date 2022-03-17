package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*")
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 45)
    private String name;

    @Column(name = "description", nullable = false, length = 255)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 255)
    private String description;

    public Role() {
    }

    public Role(Long id,
            @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*") @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 45) String name,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 255) String description,
            List<UserHasRole> userHasRoles, List<RoleHasPermit> rolesHasPermits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.userHasRoles = userHasRoles;
        this.rolesHasPermits = rolesHasPermits;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<UserHasRole> getUserHasRoles() {
        return userHasRoles;
    }

    public void setUserHasRoles(List<UserHasRole> userHasRoles) {
        this.userHasRoles = userHasRoles;
    }

    public List<RoleHasPermit> getRolesHasPermits() {
        return rolesHasPermits;
    }

    public void setRolesHasPermits(List<RoleHasPermit> rolesHasPermits) {
        this.rolesHasPermits = rolesHasPermits;
    }

    // Configuration for UserHasRole
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserHasRole> userHasRoles; 

    // configuration for roleHasPermit
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<RoleHasPermit> rolesHasPermits;

}

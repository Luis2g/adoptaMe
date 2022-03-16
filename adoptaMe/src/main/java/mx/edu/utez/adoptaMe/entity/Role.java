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

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*")
    @NotEmpty
    private String name;

    @Column(name = "description")
    @NotEmpty
    private String description;

    public Role() {
    }

    public Role(Long id, @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*$") @NotEmpty String name,
            @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$") @NotEmpty String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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

    // Configuration for UserHasRole
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserHasRole> userHasRoles; 

    // configuration for roleHasPermit
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<RoleHasPermit> rolesHasPermits;

}

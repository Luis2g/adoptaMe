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
@Table(name = "rol")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")
    @NotEmpty
    private String name;

    @Column(name = "description")
    @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$")
    @NotEmpty
    private String description;

    public Role() {
    }

    public Role(Integer id, @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$") @NotEmpty String name,
            @Pattern(regexp = "^[A-Za-z]+((\\s)?((\\'|\\-|\\.)?([A-Za-z])+))*$") @NotEmpty String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    // Configuration for UserHasRoles
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<UserHasRole> userHasRoles; 

    // configuration for roleHasPermit
    @OneToMany(mappedBy = "role")
    @JsonIgnore
    private List<RoleHasPermit> rolesHasPermits;

}

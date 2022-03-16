package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "permits")
public class Permit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(nullable = false)
    @NotBlank(message = "Este campo es requerido")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "Los caracteres proporcinados no son aceptados")
    private String name;

    @Column(nullable = false)
    @NotBlank(message = "Esta campo es requerido")
    private String description;

    public Permit() {
    }
    
    public Permit(Integer id,
            @NotBlank(message = "Este campo es requerido") @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "Los caracteres proporcinados no son aceptados") String name,
            @NotBlank(message = "Esta campo es requerido") String description, List<RoleHasPermit> rolesHasPermits) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.rolesHasPermits = rolesHasPermits;
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

    // configuration for RolesHasPermits
    @OneToMany(mappedBy = "permit")
    @JsonIgnore
    private List<RoleHasPermit> rolesHasPermits;

}

package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "permits")
public class Permit {
    
    private Integer id;
    private String name;
    private String description;

    public Permit() {
    }
    public Permit(Integer id, String name, String description) {
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

    // configuration for RolesHasPermits
    @OneToMany(mappedBy = "permit")
    @JsonIgnore
    private List<RoleHasPermit> rolesHasPermits;

}

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
    @Column(name = "authority", nullable = false, length = 45)
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*")
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 45)
    private String authority;

    @Column(name = "description", nullable = false, length = 255)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 255)
    private String description;

    public Role() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
 
    public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


    //This used to be the old configuration before Spring Security
//    // configuration for roleHasPermit
//    @OneToMany(mappedBy = "role")
//    @JsonIgnore
//    private List<RoleHasPermit> rolesHasPermits;

    

}

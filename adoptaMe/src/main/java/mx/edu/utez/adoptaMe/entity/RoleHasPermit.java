package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "roles_has_permits")
public class RoleHasPermit {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public RoleHasPermit() {
    }

    public RoleHasPermit(Long id, Role role, Permit permit) {
        this.id = id;
        this.role = role;
        this.permit = permit;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Permit getPermit() {
        return permit;
    }

    public void setPermit(Permit permit) {
        this.permit = permit;
    }



    // foreign key for role
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // foreign key for permit
    @ManyToOne
    @JoinColumn(name = "permit_id", nullable = false)
    private Permit permit;

}

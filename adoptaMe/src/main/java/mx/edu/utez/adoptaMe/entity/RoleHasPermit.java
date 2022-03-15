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
    @Column(name = "id")
    private Integer id;

    // configuration for role
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    // configuration for permit
    @ManyToOne
    @JoinColumn(name = "permit_id")
    private Permit permit;

}

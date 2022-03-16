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
    @Column
    private Long id;

    // foreign key for role
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    // foreign key for permit
    @ManyToOne
    @JoinColumn(name = "permit_id", nullable = false)
    private Permit permit;

}

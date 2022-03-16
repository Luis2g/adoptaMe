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
@Table(name = "requests")
public class Request {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_accepted", nullable = false)
    private Boolean isAccepted;

    // Foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Foreign key for pet
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petRequest;

}

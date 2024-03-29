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
    private String isAccepted;

    public Request() {
    }
    
    public Request(User user, Pet pet) {
    	this.user = user;
    	this.petRequest = pet;
    	this.isAccepted = "pending";
    }

    public Request(Long id, Boolean isAccepted, User user, Pet petRequest) {
        this.id = id;
        this.isAccepted = "pending";
        this.user = user;
        this.petRequest = petRequest;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(String isAccepted) {
        this.isAccepted = isAccepted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPetRequest() {
        return petRequest;
    }

    public void setPetRequest(Pet petRequest) {
        this.petRequest = petRequest;
    }

    // Foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Foreign key for pet
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petRequest;

	@Override
	public String toString() {
		return "Request [id=" + id + ", isAccepted=" + isAccepted + ", petRequest=" + petRequest
				+ "]";
	}

}

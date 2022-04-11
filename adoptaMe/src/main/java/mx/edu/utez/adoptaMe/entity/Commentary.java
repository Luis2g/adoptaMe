package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "commentaries")
public class Commentary {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
	
	@Column(name = "name", nullable = false, length = 500)
	@NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 500, message = "El motivo de rechazo debe contener al menos 2 caracteres")
	private String commentary;

	
	
	public Commentary(Long id,
			@NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 500, message = "El motivo de rechazo debe contener al menos 2 caracteres") String commentary,
			Pet petCommentary) {
		this.id = id;
		this.commentary = commentary;
		this.petCommentary = petCommentary;
	}



	public Commentary() {
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getCommentary() {
		return commentary;
	}



	public void setCommentary(String commentary) {
		this.commentary = commentary;
	}



	public Pet getPetCommentary() {
		return petCommentary;
	}



	public void setPetCommentary(Pet petCommentary) {
		this.petCommentary = petCommentary;
	}



	// Foreign key for pet
    @ManyToOne
    @JoinColumn(name = "pet_id", nullable = false)
    private Pet petCommentary;
	
}

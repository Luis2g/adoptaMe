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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "personalities")
public class Personality {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 45)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 45)
    private String name;

    public Personality() {
    }

    public Personality(Long id, @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 45) String name,
            List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    // Configuration for pet
    @OneToMany(mappedBy = "personality")
    @JsonIgnore
    private List<Pet> pets;

}

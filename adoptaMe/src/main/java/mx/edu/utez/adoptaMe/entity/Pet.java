package mx.edu.utez.adoptaMe.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pets")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales")
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "sex", nullable = false, length = 1)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 1, max = 1, message = "La mascota solo puede ser H (Hembra) ó M (Macho)")
    private String sex;

    @Column(name = "date_of_birth", nullable = false, length = 50)
    @NotEmpty(message = "Este campo es requerido")
    private String dateOfBirth;

    @Column(name = "size", nullable = false, length = 45)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 45)
    private String size;

    @Column(name = "type", nullable = false, length = 45)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 5, max = 45)
    private String type;

    @Column(name = "registration_date", nullable = false, length = 50)
    @NotEmpty(message = "Este campo es requerido")
    private String registrationDate;

    @Column(name = "is_available", nullable = false)
    @NotEmpty(message = "Este campo es requerido")
    private Boolean isAvailable;

    public Pet() {
    }

    public Pet(Long id,
            @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales") @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 50) String name,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 1, max = 1, message = "La mascota solo puede ser H (Hembra) ó M (Macho)") String sex,
            @NotEmpty(message = "Este campo es requerido") String dateOfBirth,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 45) String size,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 5, max = 45) String type,
            @NotEmpty(message = "Este campo es requerido") String registrationDate,
            @NotEmpty(message = "Este campo es requerido") Boolean isAvailable, Personality personality, Color color,
            Post post, List<Request> requests) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.size = size;
        this.type = type;
        this.registrationDate = registrationDate;
        this.isAvailable = isAvailable;
        this.personality = personality;
        this.color = color;
        this.requests = requests;
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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(Boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Personality getPersonality() {
        return personality;
    }

    public void setPersonality(Personality personality) {
        this.personality = personality;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    // Foreign key for personality
    @ManyToOne
    @JoinColumn(name = "personality_id", nullable = false)
    private Personality personality;

    // Foreign key for color
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    // Foreign key for color
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Configuration for request
    @OneToMany(mappedBy = "petRequest")
    @JsonIgnore
    private List<Request> requests;

    // Configuration for request
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<Image> images;

    // Configuration for request
    @OneToMany(mappedBy = "pet")
    @JsonIgnore
    private List<FavoriteOne> favoriteOnes;

}

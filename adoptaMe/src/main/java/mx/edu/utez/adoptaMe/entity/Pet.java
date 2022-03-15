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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "pets")
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sex", nullable = false)
    private String sex;

    @Column(name = "date_of_birth", nullable = false)
    private String dateOfBirth;

    @Column(name = "size", nullable = false)
    private String size;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "registration_date", nullable = false)
    private String registrationDate;

    @Column(name = "is_available", nullable = false)
    private Boolean isAvailable;

    public Pet() {
    }

    public Pet(Integer id, String name, String sex, String dateOfBirth, String size, String type,
            String registrationDate, Boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
        this.size = size;
        this.type = type;
        this.registrationDate = registrationDate;
        this.isAvailable = isAvailable;
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

    // Foreign key for personality
    @ManyToOne
    @JoinColumn(name = "personality_id", nullable = false)
    private Personality personality;

    // Foreign key for color
    @ManyToOne
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    // Configuration for post
    @OneToOne(mappedBy = "petPost")
    @JsonIgnore
    private Post post;

    // Configuration for request
    @OneToMany(mappedBy = "petRequest")
    @JsonIgnore
    private List<Request> requests;

}

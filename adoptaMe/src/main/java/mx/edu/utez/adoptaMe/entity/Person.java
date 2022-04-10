package mx.edu.utez.adoptaMe.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "people")
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Long personId;

    @Column(name = "name", nullable = false, length = 50)
    @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales")
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 50)
    private String name;

    @Column(name = "surname", nullable = false, length = 50)
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales")
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, max = 50)
    private String surname;

    @Column(name = "second_surname", nullable = true, length = 50)
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales")
    @Size(min = 2, max = 50)
    private String secondSurname;

    @Column(name = "gender", nullable = false, length = 1)
    @Size(min = 1, max = 1, message = "Esta campo solo puede contener un caracter")
    @NotEmpty(message = "Este campo es requerido")
    private String gender;

    @Column(name = "phone_number", nullable = false, length = 10)
    @Size(min = 7, max = 10, message = "El numero telefónico debe contener al menos 7 numeros")
    @Pattern(regexp = "[0-9]+", message = "Este campo solo debe contener numeros")
    @NotEmpty(message = "Este campo es requerido")
    private String phoneNumber;

    @Column(name = "registration_date")
    // @NotEmpty(message = "Este campo es requerido")
    // @Size(min = 0, max = 10)
    private String registrationDate;

    public Person() {
    }

    public Person(Long personId,
            @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales") @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 50) String name,
            @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales") @NotEmpty(message = "Este campo es requerido") @Size(min = 2, max = 50) String surname,
            @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales") @Size(min = 2, max = 50) String secondSurname,
            @Size(min = 1, max = 1, message = "Esta campo solo puede contener un caracter") @NotEmpty(message = "Este campo es requerido") String gender,
            @Size(min = 7, max = 10, message = "El numero telefónico debe contener al menos 7 numeros") @Pattern(regexp = "[0-9]+", message = "Este campo solo debe contener numeros") @NotEmpty(message = "Este campo es requerido") String phoneNumber,
            String registrationDate, User user) {
        this.personId = personId;
        this.name = name;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.user = user;
    }



    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getSecondSurname() {
        return secondSurname;
    }
    public void setSecondSurname(String secondSurname) {
        this.secondSurname = secondSurname;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getRegistrationDate() {
        return registrationDate;
    }
    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    //configuration for user
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private User user;
    

}

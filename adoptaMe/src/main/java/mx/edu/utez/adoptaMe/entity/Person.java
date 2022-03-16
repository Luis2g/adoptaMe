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
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales")
    @NotEmpty
    private String name;

    @Column(name = "surname")
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales")
    @NotEmpty
    private String surname;

    @Column(name = "second_surname")
    @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales")
    @NotEmpty
    private String secondSurname;

    @Column(name = "gender")
    @Size(min = 1, max = 1, message = "Esta campo solo puede contener un caracter")
    @NotEmpty
    private String gender;

    @Column(name = "phone_number")
    @Size(min = 7, max = 10, message = "El numero telefónico debe contener al menos 7 numeros")
    @Pattern(regexp = "[0-9]+", message = "Este campo solo debe contener numeros")
    @NotEmpty
    private String phoneNumber;

    @Column(name = "registration_date")
    @NotEmpty
    private String registrationDate;

    public Person() {
    }
    public Person(Integer id,
            @Pattern(regexp = "^[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*((\\s)?((\\'|\\-|\\.)?([A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*)+))*$", message = "El nombre debe contener solo caracteres normales") @NotEmpty String name,
            @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales") @NotEmpty String surname,
            @Pattern(regexp = "[A-ZÁÉÍÓÚ]{1}[a-zñáéíóú ]*", message = "El apellido solo debe contener caracteres normales") @NotEmpty String secondSurname,
            @Size(min = 1, max = 1, message = "Esta campo solo puede contener un caracter") @NotEmpty String gender,
            @Size(min = 7, max = 10, message = "El numero telefónico debe contener al menos 7 numeros") @Pattern(regexp = "[0-9]+", message = "Este campo solo debe contener numeros") @NotEmpty String phoneNumber,
            @NotEmpty String registrationDate, User user) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.registrationDate = registrationDate;
        this.user = user;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
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

    //configuration for user
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private User user;
    
    

    

}

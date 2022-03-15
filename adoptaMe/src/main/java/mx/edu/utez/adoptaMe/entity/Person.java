package mx.edu.utez.adoptaMe.entity;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Person {

    private Integer id;
    private String nombre;
    private String surname;
    private String secondSurname;
    private String gender;
    private String phoneNumber;
    private String date;

    public Person() {
    }
    public Person(Integer id, String nombre, String surname, String secondSurname, String gender, String phoneNumber,
            String date) {
        this.id = id;
        this.nombre = nombre;
        this.surname = surname;
        this.secondSurname = secondSurname;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.date = date;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    //configuration for user
    @OneToOne(mappedBy = "person")
    @JsonIgnore
    private User user;
    
    

    

}

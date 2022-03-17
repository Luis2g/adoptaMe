package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "donations")
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "amount", nullable = false)
    @NotEmpty(message = "Este campo es requerido")
    @Min(value = 1, message = "El donativo debe ser al menos de un peso")
    private Double amount;

    @Column(name = "donation_date", nullable = false)
    @NotEmpty(message = "Este campo es requerido")
    private String donationDate;

    @Column(name = "authorization_data", nullable = false, length = 255)
    @NotEmpty(message = "Este campo es requerido")
    @Size(min = 2, message = "La informacion al menos debe contener dos caracteres")
    private String authorizationData;

    @Column(name = "status", nullable = false, length = 45)
    @NotEmpty(message = "Este campo es requerido")
    private String status;

    public Donation() {
    }

    public Donation(Long id,
            @NotEmpty(message = "Este campo es requerido") @Min(value = 1, message = "El donativo debe ser al menos de un peso") Double amount,
            @NotEmpty(message = "Este campo es requerido") String donationDate,
            @NotEmpty(message = "Este campo es requerido") @Size(min = 2, message = "La informacion al menos debe contener dos caracteres") String authorizationData,
            @NotEmpty(message = "Este campo es requerido") String status, User user) {
        this.id = id;
        this.amount = amount;
        this.donationDate = donationDate;
        this.authorizationData = authorizationData;
        this.status = status;
        this.user = user;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Double getAmount() {
        return amount;
    }
    public void setAmount(Double amount) {
        this.amount = amount;
    }
    public String getDonationDate() {
        return donationDate;
    }
    public void setDonationDate(String donationDate) {
        this.donationDate = donationDate;
    }
    public String getAuthorizationData() {
        return authorizationData;
    }
    public void setAuthorizationData(String authorizationData) {
        this.authorizationData = authorizationData;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    
}

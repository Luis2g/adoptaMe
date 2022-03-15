package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotEmpty
    @Min(value = 1, message = "El donativo debe ser al menos de un peso")
    private Double amount;

    @NotEmpty
    private String donationDate;

    @NotEmpty
    @Size(min = 2, message = "La informacion al menos debe contener dos caracteres")
    private String authorizationData;

    @NotEmpty
    private String status;

    public Donation() {
    }
    public Donation(Integer id, Double amount, String donationDate, String authorizationData, String status) {
        this.id = id;
        this.amount = amount;
        this.donationDate = donationDate;
        this.authorizationData = authorizationData;
        this.status = status;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
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


    // foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    
}

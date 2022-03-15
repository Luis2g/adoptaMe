package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    private Double amount;
    private String donationDate;
    private String authorizationData;
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

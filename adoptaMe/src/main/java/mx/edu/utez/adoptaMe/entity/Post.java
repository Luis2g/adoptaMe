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
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "is_main", nullable = true)
    private Boolean isMain;

    @Column(name = "is_accepted", nullable = true)
    private Boolean isAccepted;

    public Post() {
    }

    public Post(Long id, String title, Boolean isMain, Boolean isAccepted) {
        this.id = id;
        this.title = title;
        this.isMain = isMain;
        this.isAccepted = isAccepted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getIsMain() {
        return isMain;
    }

    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

    public Boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(Boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    // Foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Foreign key for pet
    @OneToOne
    @JoinColumn(name = "pet_id", nullable = true)
    private Pet petPost;

    // Configuration for images
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Image> image;

    // Configuration for request
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<FavoriteOne> favoritesOnes;

}

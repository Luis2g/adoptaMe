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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "posts")
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 150)
    @Size(min = 2, max = 150)
    @NotEmpty(message = "Este campo es requerido")
    private String title;

    @Column(name = "content", nullable = false, length = 255)
    @Size(min = 2, max = 255)
    @NotEmpty(message = "Este campo es requerido")
    private String content;

    @Column(name = "is_main", nullable = true)
    private Boolean isMain;

    @Column(name = "is_accepted", nullable = true)
    private Boolean isAccepted;

    public Post() {
    }

    public Post(Long id, @Size(min = 2, max = 150) @NotEmpty(message = "Este campo es requerido") String title,
            @Size(min = 2, max = 255) @NotEmpty(message = "Este campo es requerido") String content, Boolean isMain,
            Boolean isAccepted, User user, Pet petPost, List<Image> image, List<FavoriteOne> favoritesOnes) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.isMain = isMain;
        this.isAccepted = isAccepted;
        this.user = user;
        this.petPost = petPost;
        this.image = image;
        this.favoritesOnes = favoritesOnes;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pet getPetPost() {
        return petPost;
    }

    public void setPetPost(Pet petPost) {
        this.petPost = petPost;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public List<FavoriteOne> getFavoritesOnes() {
        return favoritesOnes;
    }

    public void setFavoritesOnes(List<FavoriteOne> favoritesOnes) {
        this.favoritesOnes = favoritesOnes;
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

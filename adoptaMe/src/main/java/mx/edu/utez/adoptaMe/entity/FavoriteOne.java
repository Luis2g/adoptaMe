package mx.edu.utez.adoptaMe.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "favorite_ones")
public class FavoriteOne {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public FavoriteOne() {
    }

    public FavoriteOne(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Foreign key for user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Foreign key for post
    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

}

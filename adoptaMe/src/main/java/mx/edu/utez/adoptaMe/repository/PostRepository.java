package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import mx.edu.utez.adoptaMe.entity.Post;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(String status);

    List<Post> findByStatusAndIsMain(String status, Boolean isMain);
}

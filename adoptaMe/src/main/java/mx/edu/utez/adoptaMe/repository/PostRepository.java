package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import mx.edu.utez.adoptaMe.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}

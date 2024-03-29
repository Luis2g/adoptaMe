package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Post;

public interface PostService {

    List<Post> listAll();

    boolean save(Post post);

    Post edit(Long id);

    Post update();

    boolean delete();

    List<Post> findByStatus();

    List<Post> findByIsMain();
    
    Post savePost(Post post, String username);

    Post modifyPost(Post post, String username);
    
}

package mx.edu.utez.adoptaMe.service;

import java.util.List;

import mx.edu.utez.adoptaMe.entity.Post;

public interface PostService {

    List<Post> listAll();

    Post save();

    Post edit();

    Post update();

    void delete();
    
}

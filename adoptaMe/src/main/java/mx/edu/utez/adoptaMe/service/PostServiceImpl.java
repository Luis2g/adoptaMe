package mx.edu.utez.adoptaMe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.edu.utez.adoptaMe.entity.Post;
import mx.edu.utez.adoptaMe.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> listAll() {
        return postRepository.findAll();
    }

    @Override
    public boolean save(Post post) {
        try{
            postRepository.save(post);
            return true;
        }catch(Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Post edit() {
        return null;
    }

    @Override
    public Post update() {
        return null;
    }
    
    @Override
    public boolean delete() {
        return false;
    }


}

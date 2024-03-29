package mx.edu.utez.adoptaMe.service;

import java.util.List;
import java.util.Optional;

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
    public Post edit(Long id) {        
        Optional<Post> optional = postRepository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
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

    @Override
    public List<Post> findByStatus(){
        return postRepository.findByStatus("enabled");
    }

    @Override
    public List<Post> findByIsMain(){
        return postRepository.findByStatusAndIsMain("enabled", true);
    }

    @Override
    public Post savePost(Post post, String user) {
        try{
            return postRepository.savePost(post.getContent(), post.getImage(), post.getIsMain(), post.getPostDate(), post.getTitle(), post.getUser().getUsername(), post.getStatus(), user).get();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public Post modifyPost(Post post, String user) {
        try{
            return postRepository.modifyPost(post.getId(),post.getContent(), post.getImage(), post.getIsMain(), post.getPostDate(), post.getTitle(), post.getUser().getUsername(), post.getStatus(), user).get();
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}

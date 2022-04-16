package mx.edu.utez.adoptaMe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import mx.edu.utez.adoptaMe.entity.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByStatus(String status);

    List<Post> findByStatusAndIsMain(String status, Boolean isMain);

    @Query(value = "call insertPost(:content,:image,:is_main,:post_date,:title,:user_id,:status,:username)", nativeQuery=true)
    Optional<Post> savePost(
    @Param("content") String content,
    @Param("image") String image,
    @Param("is_main") Boolean is_main,
    @Param("post_date") String post_date,
    @Param("title") String title,
    @Param("user_id") String user_id,
    @Param("status") String status,
    @Param("username") String username
    );
     
    @Query(value = "call modifyPost(:id,:content,:image,:is_main,:post_date,:title,:user_id,:status,:username)", nativeQuery=true)
    Optional<Post> modifyPost(
    @Param("id") Long id,
    @Param("content") String content,
    @Param("image") String image,
    @Param("is_main") Boolean is_main,
    @Param("post_date") String post_date,
    @Param("title") String title,
    @Param("user_id") String user_id,
    @Param("status") String status,
    @Param("username") String username
    );
}

package com.ftn.djole.spring.repository;

import com.ftn.djole.spring.entity.Post;
import javafx.geometry.Pos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

    List<Post> findByTags_Id(Integer tagId);

    List<Post> findAllByOrderByDateDesc();
    List<Post> findAllByOrderByLikesDesc();
    List<Post> findAllByOrderByDislikeDesc();

    @Query(value = "SELECT p.post_id,p.date,p.description,p.dislikes,p.latitude,p.likes,p.longitude,p.photo" +
            ",p.title,p.user_id  FROM posts p JOIN  comments c ON p.post_id=c.post_id GROUP BY  c.post_id ORDER BY COUNT(c.post_id) DESC ",nativeQuery = true)
    List<Post> findAllOrOrderByCommentsCount();

    @Query(value = "SELECT DISTINCT p.post_id,p.date,p.description,p.dislikes,p.latitude,p.likes,p.longitude,p.photo" +
            ",p.title,p.user_id  FROM posts p JOIN  users u ON p.user_id=u.user_id JOIN post_tags pt ON p.post_id=pt.post_id JOIN tags t ON pt.tag_id=t.tag_id " +
            "WHERE t.name LIKE %:text% OR u.username LIKE %:text%",nativeQuery = true)
    List<Post> findAllBySearch(@Param("text") String text);
}

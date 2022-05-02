package com.sofkaU.postAPP.repository;

import com.sofkaU.postAPP.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("SELECT c FROM Comment c WHERE c.fkUserId = :userId")
    List<Comment> getCommentsByUserId(@Param("userId") Long userId);

    @Query("SELECT c FROM Comment c WHERE c.fkPostId = :postId")
    List<Comment> getCommentsByPostId(@Param("postId") Long postId);
}

package com.sofkaU.postAPP.service;

import com.sofkaU.postAPP.entity.Comment;
import com.sofkaU.postAPP.entity.Post;

import java.util.List;

public interface PostServiceInterface {

    Post createPost(Post post);

    Post createComment(Comment comment);

    List<Post> getAllPosts();

    List<Post> getPostsByUserId(Long userId);

    void deleteComment(Long commentId);

    void deletePost(Long postId);

    void deletePostsByUserId(Long userId);

}

package com.sofkaU.postAPP.service;

import com.sofkaU.postAPP.entity.Comment;
import com.sofkaU.postAPP.entity.Post;

import java.util.List;

public interface PostServiceInterface {

    Post createPost(Post post);

    Post createComment(Comment comment);

    void deleteComment(Comment comment);

    void deletePost(Post post);

    List<Post> findAllPosts();

}

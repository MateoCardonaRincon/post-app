package com.sofkaU.postAPP.controller;

import com.sofkaU.postAPP.entity.Comment;
import com.sofkaU.postAPP.entity.Post;
import com.sofkaU.postAPP.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("get/posts")
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("get/posts/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.getPostsByUserId(userId);
    }

    @PostMapping("create/post")
    public Post createPost(@RequestBody Post post) {
        return postService.createPost(post);
    }

    @PostMapping("create/comment")
    public Post createComment(@RequestBody Comment comment) {
        return postService.createComment(comment);
    }

    @DeleteMapping("delete/post/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }

    @DeleteMapping("delete/comment/{commentId}")
    public void deleteComment(@PathVariable Long commentId) {
        postService.deleteComment(commentId);
    }

    @DeleteMapping("delete/posts/{userId}")
    public void deletePostByUserId(@PathVariable Long userId) {
        postService.deletePostsByUserId(userId);
    }
}

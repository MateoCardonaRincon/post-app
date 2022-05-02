package com.sofkaU.postAPP.service;

import com.sofkaU.postAPP.entity.Comment;
import com.sofkaU.postAPP.entity.Post;
import com.sofkaU.postAPP.repository.CommentRepository;
import com.sofkaU.postAPP.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService implements PostServiceInterface {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostService(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post createComment(Comment comment) {
        Optional<Post> post = postRepository.findById(comment.getFkPostId());
        if (post.isPresent()) {
            post.get().addComment(comment);
            commentRepository.save(comment);
            return postRepository.save(post.get());
        }
        return null;
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return postRepository.getPostsByUserId(userId);
    }

    @Override
    public void deleteComment(Long commentId) {
        Optional<Comment> commentToDelete = commentRepository.findById(commentId);
        if (commentToDelete.isEmpty()) {
            throw new IllegalStateException("Comment with id " + commentId + " does not exist");
        }
        commentRepository.deleteById(commentId);
    }

    @Override
    public void deletePost(Long postId) {
        Optional<Post> postToDelete = postRepository.findById(postId);
        if (postToDelete.isPresent()) {
            List<Comment> comments = commentRepository.getCommentsByPostId(postToDelete.get().getId());
            comments.forEach(comment -> commentRepository.deleteById(comment.getId()));
            postRepository.deleteById(postId);
        }
    }

    @Override
    public void deletePostsByUserId(Long userId) {
        List<Post> posts = postRepository.getPostsByUserId(userId);
        posts.forEach(post -> {
            post.getComments().forEach(comment -> commentRepository.deleteById(comment.getId()));
            postRepository.deleteById(post.getId());
        });
    }
}

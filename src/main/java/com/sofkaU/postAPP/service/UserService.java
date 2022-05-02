package com.sofkaU.postAPP.service;

import com.sofkaU.postAPP.entity.Comment;
import com.sofkaU.postAPP.entity.Post;
import com.sofkaU.postAPP.entity.User;
import com.sofkaU.postAPP.repository.CommentRepository;
import com.sofkaU.postAPP.repository.PostRepository;
import com.sofkaU.postAPP.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Autowired
    public UserService(UserRepository userRepository,
                       CommentRepository commentRepository,
                       PostRepository postRepository) {
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    @Override
    public ResponseEntity<String> createUser(User user) {
        Optional<User> existingEmail = userRepository.getUserByEmail(user.getEmail());

        if (existingEmail.isPresent() || user.getEmail() == null) {
            return new ResponseEntity<>("Email already taken or is null", HttpStatus.BAD_REQUEST);
        }
        userRepository.save(user);
        return new ResponseEntity<>("User successfully created!", HttpStatus.OK);
    }

    @Override
    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public Optional<User> getUserByEmail(User user) {
        return userRepository.getUserByEmail(user.getEmail());
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public ResponseEntity<String> updateUser(User user) {
        Optional<User> existingEmail = userRepository.getUserByEmail(user.getEmail());
        Optional<User> existingId = userRepository.findById(user.getId());

        if (existingId.isEmpty()) {
            return new ResponseEntity<>("User with id \" + user.getId() + \" does not exist", HttpStatus.NOT_FOUND);
        }

        if (existingEmail.isPresent() && !existingId.get().getEmail().equals(user.getEmail())) {
            return new ResponseEntity<>("Email already taken", HttpStatus.FORBIDDEN);
        }

        if (user.getEmail() == null) {
            return new ResponseEntity<>("Email can't be null", HttpStatus.BAD_REQUEST);
        }

        userRepository.save(user);

        return new ResponseEntity<>("User successfully updated!", HttpStatus.OK);
    }

    @Override
    public void deleteUser(Long userId) {
        Optional<User> existingUser = userRepository.findById(userId);

        if (existingUser.isPresent()) {
            List<Post> posts = postRepository.getPostsByUserId(userId);
            List<Comment> comments = commentRepository.getCommentsByUserId(userId);

            comments.forEach(comment -> commentRepository.deleteById(comment.getId()));

            posts.forEach(post -> {
                post.getComments().forEach(comment -> commentRepository.deleteById(comment.getId()));
                postRepository.deleteById(post.getId());
            });

            userRepository.deleteById(userId);
        }
    }
}

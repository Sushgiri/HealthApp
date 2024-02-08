package com.microservice.comment.controller;

import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.commentdto;
import com.microservice.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //http://localhost:8082/api/comments/ratingsortcomments
    @PostMapping("/{postId}/{username}")
    public ResponseEntity<Comment> saveComment(@PathVariable String postId, @PathVariable String username, @RequestBody commentdto commentdto){
        Comment c = commentService.saveComment(username,postId,commentdto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/byid/{postId}")
    public List<Comment> getAllCommentsByPostId(@PathVariable String postId){
        List<Comment> comments = commentService.getAllCommentsByPostId(postId);
        return comments;
    }

    @GetMapping("ratingsortcomments/{postId}")
    public ResponseEntity<?> ratingsot(@PathVariable String postId){
        List<Comment> ratingsorted = commentService.ratingsorted(postId);
        return new ResponseEntity<>(ratingsorted,HttpStatus.OK);
    }

    @GetMapping("recentcomments/{postId}")
    public ResponseEntity<?> recent(@PathVariable String postId){
        List<Comment> recentcomment = commentService.recentcomment(postId);
        return  new ResponseEntity<>(recentcomment,HttpStatus.OK);
    }
}

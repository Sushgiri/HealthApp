package com.microservice.comment.service;

import com.microservice.comment.config.RestTemplateConfig;
import com.microservice.comment.entity.Comment;
import com.microservice.comment.payload.Post;
import com.microservice.comment.payload.commentdto;
import com.microservice.comment.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RestTemplateConfig restTemplate;
    public Comment saveComment( String postId,String username, commentdto commentdto){
            String commentId = UUID.randomUUID().toString();
            LocalDateTime dateTime = LocalDateTime.now();
            String date = dateTime.toString();
            Comment comment1 = new Comment();
            comment1.setUsername(username);
            comment1.setPostId(postId);
            comment1.setCommentId(commentId);
            comment1.setContent(commentdto.getContent());
            comment1.setRating(commentdto.getRating());
            comment1.setDateTime(date);
            Comment save = commentRepository.save(comment1);
            return save;


    }

//    @CircuitBreaker(name = "postBreaker",fallbackMethod = "postdownfallback")
    public List<Comment> getAllCommentsByPostId(String postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

//    public List<Comment> postdownfallback(){
//        Comment comment = new Comment();
//
//        List<Comment> comments = new ArrayList<>();
//        comments.add(comment);
//
//        return comments;
//    }

    public List<Comment> ratingsorted(String postId){
        Comment comment = new Comment();
        List<Comment> all = commentRepository.findByPostId(postId);
        List<Comment> sortedComments =  all.stream().sorted(Comparator.comparingDouble(Comment::getRating).reversed())
                .collect(Collectors.toList());
        return sortedComments;
    }

    public List<Comment> recentcomment(String postId){
        List<Comment> all = commentRepository.findByPostId(postId);
        List<Comment> sorteddatetime = all.stream().sorted(Comparator.comparing(Comment::getDateTime).reversed()).collect(Collectors.toList());;
        return sorteddatetime;
    }


}

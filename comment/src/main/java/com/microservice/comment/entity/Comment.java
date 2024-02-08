package com.microservice.comment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;



import java.time.LocalDateTime;

@Entity
@Table(name = "Comments")
@Getter
@Setter
@Data
public class Comment {


    @Id
    private String commentId;


    private String postId;
    @NotEmpty(message = "content should not be empty")
    @Size(min = 2)
    private String content;
    private String username;
    @NotNull
    private double rating;

    private String DateTime;

}

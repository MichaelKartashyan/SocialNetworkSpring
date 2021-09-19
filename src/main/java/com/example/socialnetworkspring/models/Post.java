package com.example.socialnetworkspring.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Table
@Entity

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postText;
    private Timestamp date;
    @ManyToOne(fetch = FetchType.EAGER)
    private Users user;
    private int countLike;
    private int countComment;
//    @ManyToOne(fetch = FetchType.EAGER)
//    private Comment comment;
    private Long forUsers;
}

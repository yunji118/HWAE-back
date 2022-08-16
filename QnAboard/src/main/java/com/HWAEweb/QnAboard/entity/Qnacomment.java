package com.HWAEweb.QnAboard.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Qnacomment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentID;

    private String content;

    private Timestamp timestamp;

    //@ManyToOne
    //private Qnaboard postID;

    private Integer postID;

}

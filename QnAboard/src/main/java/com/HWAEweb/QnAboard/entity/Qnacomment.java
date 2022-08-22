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

    @Column
    private String content;

    @Column
    private Timestamp timestamp;
    
    //Qnaboard의 postID와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postID")
    private Qnaboard postID;


}

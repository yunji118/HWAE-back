package com.HWAEweb.QnAboard.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity   //DB에 있는 테이블
@Data
public class Qnaboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;

    private Timestamp timestamp;

    private Boolean secret;

    private Integer postPW;

    private String title;

    private String content;

    /*
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("timestamp asc")
    private List<Qnacomment> commentlist;
    */

    //private List<Qnacomment> commentlist = new ArrayList<Qnacomment>();


}

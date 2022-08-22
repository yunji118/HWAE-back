package com.HWAEweb.QnAboard.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity   //DB에 있는 테이블
@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class Qnaboard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postID;

    private Timestamp timestamp;

    private Boolean secret;

    private Integer postPW;

    private String title;

    private String content;

    /*Qnacomment와 연결
    @OneToMany(mappedBy = "posts", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("timestamp asc")
    private List<Qnacomment> commentlist;
    */

    /*
    //private List<Qnacomment> commentlist = new ArrayList<Qnacomment>();
    @OneToMany(mappedBy = "qnaboard", fetch = FetchType.LAZY)
    private List<Qnacomment> commentList;

    @Builder
    public Qnaboard(Integer id, Boolean secret, Integer postPW, String title, String content){
        this.id = id;
        this.secret = secret;
        this.postPW = postPW;
        this.title = title;
        this.content = content;
    }

     */
}

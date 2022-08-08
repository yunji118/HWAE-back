package com.HWAEweb.QnAboard.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity   //DB에 있는 테이블
@Data
public class Qna_board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer post_id;

    private Timestamp timestamp;

    private Boolean secret;

    private Integer post_pw;

    private String title;

    private String content;

}

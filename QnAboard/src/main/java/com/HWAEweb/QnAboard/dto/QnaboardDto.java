package com.HWAEweb.QnAboard.dto;

import com.HWAEweb.QnAboard.entity.Qnaboard;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class QnaboardDto {
    private Integer postID;
    private Timestamp timestamp;
    private Boolean secret;
    private Integer postPW;
    private String title;
    private String content;


    /*
    public Qnaboard.QnaboardBuilder toEntity(){
        Qnaboard.QnaboardBuilder build = Qnaboard.builder()
                .postID(postID)
                .secret(secret)
                .postPW(postPW)
                .title(title)
                .content(content);
        return build;
    }


    @Builder
    public QnaboardDto(Integer postID, Boolean secret, Integer postPW,String title, String content, Timestamp timestamp) {
        this.postID = postID;
        this.secret = secret;
        this.title = title;
        this.content = content;
        this.postPW = postPW;
        this.timestamp = timestamp;
    }

     */


}

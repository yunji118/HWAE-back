package com.HWAEweb.QnAboard.controller;

import com.HWAEweb.QnAboard.entity.Qnaboard;
import com.HWAEweb.QnAboard.entity.Qnacomment;
import com.HWAEweb.QnAboard.repository.QnaboardRepository;
import com.HWAEweb.QnAboard.service.QnaboardService;
import com.HWAEweb.QnAboard.service.QnacommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.Timestamp;

@Controller
public class QnacommentController {

    @Autowired
    private QnacommentService qnacommentService;

    @Autowired
    private QnaboardRepository qnaboardRepository;

/*
    //댓글 작성
    @GetMapping("/qnaboard/commentwrite")
    public String commentWrite(){
        qnacommentService.qnacommentwrite(qnacomment);

        //return "redirect:/qnaboard/view?postID=" + qnacomment.getPostID();   //댓글을 작성한 게시물로 이동
        return "qnaboardview";
    }
*/
    //작성된 댓글 처리
    @PostMapping("/qnaboard/commentpro")
    public String qnacommentWritePro(Qnacomment qnacomment, Model model){

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qnacomment.setTimestamp(timestamp);
        qnacommentService.qnacommentwrite(qnacomment);

        model.addAttribute("message","댓글 작성이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
        //return "redirect:/qnaboard/view?postID="+qnacomment.getPostID();
    }

    @GetMapping("/qnaboard/commentdelete")
    public String qnacommentDelete(Integer commentID){
        qnacommentService.qnacommentDelete(commentID);

        return "redirect:/qnaboard/list";
    }

    @GetMapping("/qnaboard/commentmodify/{commentID}")
    public String qnacommentModify(@PathVariable("commentID") Integer commentID, Model model){
        model.addAttribute("comment", qnacommentService.qnacommentView(commentID));

        return "qnacommentmodify";
    }

    @PostMapping("qnaboard/commentupdate/{commentID}")
    public String qnacommentUpdate(@PathVariable("commentID") Integer commentID, Qnacomment qnacomment, Model model){
        Qnacomment commentTemp = qnacommentService.qnacommentView(commentID);
        commentTemp.setCommentID(qnacomment.getCommentID());
        commentTemp.setContent(qnacomment.getContent());
        commentTemp.setPostID(qnacomment.getPostID());

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        commentTemp.setTimestamp(timestamp);

        qnacommentService.qnacommentwrite(commentTemp);
        model.addAttribute("message", "댓글 수정이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
    }





}

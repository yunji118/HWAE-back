package com.HWAEweb.QnAboard.controller;

import com.HWAEweb.QnAboard.entity.Qna_board;
import com.HWAEweb.QnAboard.service.QnaboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import java.sql.Timestamp;

@Controller
public class QnaboardController {
    @Autowired
    private QnaboardService qnaboardService;

    @GetMapping("/qnaboard/write")  //localhost:8080/qnaboard/qnaboardwrite
    public String qnaboardWriteForm(){
        return "qnaboardwrite";
    }

    @PostMapping("/qnaboard/writepro")
    public String qnaboardWrtiePro(Qna_board qna_board, Model model){

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qna_board.setTimestamp(timestamp);
        qnaboardService.qnawrite(qna_board);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
    }

    @GetMapping("/qnaboard/list")
    public String qnaboardlist(Model model, @PageableDefault(page = 0, size = 10, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Qna_board> list = qnaboardService.qnaboardList(pageable);

        //model.addAttribute("list", qnaboardService.qnaboardList(pageable));
        int nowPage = list.getPageable().getPageNumber() + 1;  //현재 페이지 번호 가져오기  //page는 0부터 시작. 우리가 보는건 1부터이므로
        int startPage = Math.max(nowPage-4,1);  //더 높은 값을 반환함->음수인 경우에 1을 반환
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list); //리스트 반환. list라는 이름으로 넘긴다
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        return "qnaboardlist";
    }

    @GetMapping("/qnaboard/view")    //localhost:8080/qnaboard/view?postId=1
    public String qnaboardView(Model model, Integer post_id){

        model.addAttribute("qna", qnaboardService.qnaboardView(post_id));
        return "qnaboardview";
    }

    @GetMapping("/qnaboard/delete")
    public String qnaboardDelete(Integer post_id){
        qnaboardService.qnaDelete(post_id);
        
        return "redirect:/qnaboard/list";
    }

    @GetMapping("/qnaboard/modify/{post_id}")
    public String qnaboardModify(@PathVariable("post_id") Integer post_id, Model model){

        model.addAttribute("qna", qnaboardService.qnaboardView(post_id));

        return "qnaboardmodify";
    }

    @PostMapping("qnaboard/update/{post_id}")
    public String qnaUpdate(@PathVariable("post_id") Integer post_id, Qna_board qna_board, Model model){  //qna_board는 새로 입력한 내용

        Qna_board qnaTemp = qnaboardService.qnaboardView(post_id);    //기존에 있던 글 검색
        qnaTemp.setTitle(qna_board.getTitle()); //덮어씌우기
        qnaTemp.setContent(qna_board.getContent());
        qnaTemp.setSecret(qna_board.getSecret());
        qnaTemp.setPost_pw(qna_board.getPost_pw());

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qnaTemp.setTimestamp(timestamp);

        qnaboardService.qnawrite(qnaTemp);
        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
        //return "redirect:/qnaboard/list";
    }
}

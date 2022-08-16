package com.HWAEweb.QnAboard.controller;

import com.HWAEweb.QnAboard.entity.Qnaboard;
import com.HWAEweb.QnAboard.entity.Qnacomment;
import com.HWAEweb.QnAboard.repository.QnacommentRepository;
import com.HWAEweb.QnAboard.service.QnaboardService;
import com.HWAEweb.QnAboard.service.QnacommentService;
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
import java.util.List;
import java.util.Optional;

@Controller
public class QnaboardController {
    @Autowired
    private QnaboardService qnaboardService;

    @Autowired
    private QnacommentService qnacommentService;


    @GetMapping("/qnaboard/write")  //localhost:8080/qnaboard/qnaboardwrite
    public String qnaboardWriteForm(){
        return "qnaboardwrite";
    }

    @PostMapping("/qnaboard/writepro")
    public String qnaboardWritePro(Qnaboard qnaboard, Model model){

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qnaboard.setTimestamp(timestamp);
        qnaboardService.qnawrite(qnaboard);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
    }
    
    @GetMapping("/qnaboard/list")
    public String qnaboardlist(Model model, @PageableDefault(page = 0, size = 10, sort = "timestamp", direction = Sort.Direction.DESC) Pageable pageable){

        Page<Qnaboard> list = qnaboardService.qnaboardList(pageable);

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

    //특정 게시물 보기
    @GetMapping("/qnaboard/view")    //localhost:8080/qnaboard/view?postId=1
    public String qnaboardView(Model model, Integer postID) throws Exception {


        model.addAttribute("qna", qnaboardService.qnaboardView(postID));

        //댓글조회
        List<Qnacomment> comment = null;
        comment = qnacommentService.qnacommentList(postID);
        model.addAttribute("comment", comment);

        return "qnaboardview";
    }

    @GetMapping("/qnaboard/delete")
    public String qnaboardDelete(Integer postID){
        qnaboardService.qnaDelete(postID);
        
        return "redirect:/qnaboard/list";
    }

    @GetMapping("/qnaboard/modify/{postID}")
    public String qnaboardModify(@PathVariable("postID") Integer postID, Model model){

        model.addAttribute("qna", qnaboardService.qnaboardView(postID));

        return "qnaboardmodify";
    }

    @PostMapping("qnaboard/update/{postID}")
    public String qnaUpdate(@PathVariable("postID") Integer postID, Qnaboard qnaboard, Model model){  //qna_board는 새로 입력한 내용

        Qnaboard qnaTemp = qnaboardService.qnaboardView(postID);    //기존에 있던 글 검색
        qnaTemp.setTitle(qnaboard.getTitle()); //덮어씌우기
        qnaTemp.setContent(qnaboard.getContent());
        qnaTemp.setSecret(qnaboard.getSecret());
        qnaTemp.setPostPW(qnaboard.getPostPW());

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qnaTemp.setTimestamp(timestamp);

        qnaboardService.qnawrite(qnaTemp);
        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
        //return "redirect:/qnaboard/list";
    }


    /*댓글 작성
    @GetMapping("/qnaboard/write")  //localhost:8080/qnaboard/qnaboardwrite
    public String qnacommentWriteForm(){
        return "qnaboardwrite";
    }


    /*입력된 댓글 처리
    @PostMapping("/qnaboard/commentpro")
    public String commentWritePro(Qnacomment qnacomment, Model model){

        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        qnacomment.setTimestamp(timestamp);
        qnacommentService.qnacommentwrite(qnacomment);

         현재 보고있는 페이지의 postid를 어떻게 받아올 것인가...
        Integer postid = qnaboard.getPostID();
        qnacomment.setPostid(postid);

        model.addAttribute("message", "댓글 작성이 완료되었습니다.");
        //model.addAttribute("qnaUrl", "/qnaboard/view?postID={postID}");
        model.addAttribute("listUrl", "/qnaboard/list");

        return "message";
    }
     */

}

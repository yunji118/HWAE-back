package com.HWAEweb.QnAboard.service;

import com.HWAEweb.QnAboard.entity.Qna_board;
import com.HWAEweb.QnAboard.repository.QnaboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QnaboardService {

    @Autowired
    private QnaboardRepository qnaboardRepository;

    //QnA 글 작성 처리
    public void qnawrite(Qna_board qna_board){

        qnaboardRepository.save(qna_board);
    }

    //QnA 게시글 리스트 처리
    public Page<Qna_board> qnaboardList(Pageable pageable){

        return qnaboardRepository.findAll(pageable);
    }

    //QnA 특정 게시글 불러오기
    public Qna_board qnaboardView(Integer post_id){

        return qnaboardRepository.findById(post_id).get();
    }

    //QnA 특정 게시글 삭제
    public void qnaDelete(Integer post_id){

        qnaboardRepository.deleteById(post_id);
    }

}

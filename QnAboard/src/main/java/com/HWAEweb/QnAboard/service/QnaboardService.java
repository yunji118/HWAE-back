package com.HWAEweb.QnAboard.service;

import com.HWAEweb.QnAboard.dto.QnaboardDto;
import com.HWAEweb.QnAboard.entity.Qnaboard;
import com.HWAEweb.QnAboard.entity.Qnacomment;
import com.HWAEweb.QnAboard.repository.QnaboardRepository;
import com.HWAEweb.QnAboard.repository.QnacommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QnaboardService {

    @Autowired
    private QnaboardRepository qnaboardRepository;


    //QnA 글 작성 처리
    @Transactional
    public void qnawrite(Qnaboard qnaboard){

        qnaboardRepository.save(qnaboard);
    }

    //QnA 게시글 리스트 처리
    public Page<Qnaboard> qnaboardList(Pageable pageable){

        return qnaboardRepository.findAll(pageable);
    }

    //QnA 특정 게시글 불러오기
    public Qnaboard qnaboardView(Integer postID){

        return qnaboardRepository.findById(postID).get();
    }

    //QnA 특정 게시글 삭제
    public void qnaDelete(Integer postID){

        qnaboardRepository.deleteById(postID);
    }


}

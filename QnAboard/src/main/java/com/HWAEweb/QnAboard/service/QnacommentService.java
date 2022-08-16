package com.HWAEweb.QnAboard.service;


import com.HWAEweb.QnAboard.entity.Qnacomment;
import com.HWAEweb.QnAboard.repository.QnacommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class QnacommentService {

    @Autowired
    private QnacommentRepository qnacommentRepository;

    //QnA 댓글 작성 처리
    public void qnacommentwrite(Qnacomment qnacomment){

        qnacommentRepository.save(qnacomment);

    }

    //QnA 댓글 리스트
    public List<Qnacomment> qnacommentList(Integer postID) throws Exception{

        return qnacommentRepository.findAllById(Collections.singleton(postID));  //아닌거같음
    }


    //QnA 특정 댓글 불러오기
    public Qnacomment qnacommentView(Integer postID){

        return qnacommentRepository.findById(postID).get();
    }


    //QnA 특정 댓글 삭제
    public void qnacommentDelete(Integer commentID){

        qnacommentRepository.deleteById(commentID);
    }

}

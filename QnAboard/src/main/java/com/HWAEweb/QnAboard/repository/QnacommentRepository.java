package com.HWAEweb.QnAboard.repository;

import com.HWAEweb.QnAboard.entity.Qnacomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QnacommentRepository extends JpaRepository<Qnacomment, Integer> {

    //@Query("select c from qnacomment c where c.postID = :postID")
    //List<Qnacomment> findCommentsBypostID(@Param("postID") Integer postID);
}

package com.HWAEweb.QnAboard.repository;

import com.HWAEweb.QnAboard.entity.Qnacomment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnacommentRepository extends JpaRepository<Qnacomment, Integer> {
}

package com.HWAEweb.QnAboard.repository;

import com.HWAEweb.QnAboard.entity.Qnaboard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QnaboardRepository extends JpaRepository<Qnaboard, Integer> {

}

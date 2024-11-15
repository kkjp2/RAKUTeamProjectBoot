package com.example.board_test.board.repository;

import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
public interface FestivalBoardRepository extends JpaRepository<FestivalBoardEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.likeCnt= f.likeCnt+1 WHERE f.f_id= :fId AND f.member = :member")
    void Like(@Param("fId") Long fId, @Param("member") MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.viewCnt=f.viewCnt +1 WHERE f.f_id = :fId")
    void View(@Param("fId") Long fId);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.likeCnt=:like, f.viewCnt=:view WHERE f.f_id= :fId")
    void updateBoardStats(@Param("fId") Long fId,
                          @Param("like") int like,
                          @Param("view") int view
    );
}

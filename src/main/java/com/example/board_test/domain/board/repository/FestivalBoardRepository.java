package com.example.board_test.domain.board.repository;

import com.example.board_test.domain.board.entity.FestivalBoardEntity;
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
    @Query("UPDATE FestivalBoardEntity f SET f.likeCnt= f.likeCnt+1 WHERE f.fbId= :fbId AND f.member = :member")
    void Like(@Param("fbId") Long fbId, @Param("member") MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.viewCnt=f.viewCnt +1 WHERE f.fbId = :fbId")
    void View(@Param("fbId") Long fbId);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.likeCnt=:like, f.viewCnt=:view WHERE f.fbId= :fbId")
    void updateBoardStats(@Param("fbId") Long fbId,
                          @Param("like") int like,
                          @Param("view") int view
    );
}

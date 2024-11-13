package com.example.board_test.board.repository;

import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FestivalBoardRepository extends JpaRepository<FestivalBoardEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.like= f.like+1 WHERE f.f_id= :fId AND f.member = :member")
    void Like(@Param("fId") Long fId, @Param("member") MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.view=f.view +1 WHERE f.f_id = :fId")
    void View(@Param("fId") Long fId);

    @Modifying
    @Transactional
    @Query("UPDATE FestivalBoardEntity f SET f.like=:like, f.dislike=:dislike, f.view=:view WHERE f.f_id= :fId")
    void updateBoardStats(@Param("fId") Long fId,
                          @Param("like") int like,
                          @Param("view") int view
    );
}

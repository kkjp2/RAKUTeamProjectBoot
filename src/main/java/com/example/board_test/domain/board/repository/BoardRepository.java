package com.example.board_test.domain.board.repository;

import com.example.board_test.domain.board.entity.BoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.likeCnt= b.likeCnt+1 WHERE b.nId= :nId AND b.member = :member")
    void Like(@Param("nId") Long nId, @Param("member")MemberEntity member);


    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.dislikeCnt= b.dislikeCnt+1 WHERE b.nId= :nId AND b.member =:member")
    void DisLike(@Param("nId") Long nId, @Param("member") MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.viewCnt=b.viewCnt+1 WHERE b.nId = :nId")
    void View(@Param("nId") Long nId);

    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.likeCnt=:like, b.dislikeCnt=:dislike, b.viewCnt=:view WHERE b.nId= :nId")
    void updateBoardStats(@Param("nId") Long nId,
                          @Param("like") int like,
                          @Param("dislike") int dislike,
                          @Param("view") int view
                          );












}

package com.example.board_test.board.repository;

import com.example.board_test.board.entity.BoardEntity;
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
    @Query("UPDATE BoardEntity b SET b.like= b.like+1 WHERE b.n_id= :nId AND b.member = :member")
    void Like(@Param("nId") Long nId, @Param("member")MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.dislike= b.dislike +1 WHERE b.n_id= :nId AND b.member =:member")
    void DisLike(@Param("nId") Long nId, @Param("member") MemberEntity member);

    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.view=b.view +1 WHERE b.n_id = :nId")
    void View(@Param("nId") Long nId);

    @Modifying
    @Transactional
    @Query("UPDATE BoardEntity b SET b.like=:like, b.dislike=:dislike, b.view=:view WHERE b.n_id= :nId")
    void updateBoardStats(@Param("nId") Long nId,
                          @Param("like") int like,
                          @Param("dislike") int dislike,
                          @Param("view") int view
                          );












}

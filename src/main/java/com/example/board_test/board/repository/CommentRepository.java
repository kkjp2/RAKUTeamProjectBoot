package com.example.board_test.board.repository;

import com.example.board_test.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
//    List<CommentEntity> findByBoardOrderByCreatedDateAsc(BoardEntity board);
//    List<CommentEntity> findByBoardOrderByCreatedDateDesc(BoardEntity board);
    List<CommentEntity> findByBoard_nId(Long nId);

    //게시물번호랑 댓글 번호 둘다 동일 한지 판별
   // Optional<CommentEntity> findByBoard_n_idAndComment_id(Long n_id, Long c_id);





}

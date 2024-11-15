package com.example.board_test.board.repository;

import com.example.board_test.board.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
//    List<CommentEntity> findByBoardOrderByCreatedDateAsc(BoardEntity board);
//    List<CommentEntity> findByBoardOrderByCreatedDateDesc(BoardEntity board);

    //게시물번호랑 댓글 번호 둘다 동일 한지 판별
   // Optional<CommentEntity> findByBoard_n_idAndComment_id(Long n_id, Long c_id);





}

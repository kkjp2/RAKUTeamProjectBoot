package com.example.board_test.board.repository;

import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.entity.ImageEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findByBoard(BoardEntity board);

    List<ImageEntity> findByFestivalBoard(FestivalBoardEntity festivalBoard);

    List<ImageEntity> findByMember(MemberEntity member);
}

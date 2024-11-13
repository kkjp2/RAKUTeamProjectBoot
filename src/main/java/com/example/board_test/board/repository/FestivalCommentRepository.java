package com.example.board_test.board.repository;

import com.example.board_test.board.entity.FestivalCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FestivalCommentRepository extends JpaRepository<FestivalCommentEntity, Long> {
}

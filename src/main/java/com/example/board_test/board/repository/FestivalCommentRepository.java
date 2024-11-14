package com.example.board_test.board.repository;

import com.example.board_test.board.entity.FestivalCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FestivalCommentRepository extends JpaRepository<FestivalCommentEntity, Long> {

    Optional<FestivalCommentEntity> findByPostsIdAndId(Long fboard_id, Long fc_id);
}

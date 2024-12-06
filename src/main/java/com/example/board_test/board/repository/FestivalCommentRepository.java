package com.example.board_test.board.repository;

import com.example.board_test.board.entity.FestivalCommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FestivalCommentRepository extends JpaRepository<FestivalCommentEntity, Long> {
    List<FestivalCommentEntity> findByFestivalBoard_fbId(Long fId);
    //Optional<FestivalCommentEntity> findByPostsIdAndId(Long fboard_id, Long fc_id);
}

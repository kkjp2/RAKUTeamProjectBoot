package com.example.board_test.board.repository;

import com.example.board_test.board.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {
}

package com.example.board_test.board.repository;

import com.example.board_test.board.entity.RecentlyViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecentlyViewRepository extends JpaRepository<RecentlyViewEntity, Long> {
}

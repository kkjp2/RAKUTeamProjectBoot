package com.example.board_test.domain.board.repository;

import com.example.board_test.domain.board.entity.RecentlyViewEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentlyViewRepository extends JpaRepository<RecentlyViewEntity, Long> {


    @Query(value = "SELECT * FROM notice_recently WHERE user_key = :userKey ORDER BY r_id DESC", nativeQuery = true)
    List<RecentlyViewEntity> findRecentlyViews(@Param("userKey") Long userKey);

}

package com.example.board_test.board.repository;

import com.example.board_test.board.entity.HashTagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
@Repository
public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {

   // List<HashTagEntity> findTop10ByOrderBySearchCountDesc();

    @Modifying
    @Query("UPDATE HashTagEntity h SET h.count =h.count +1 WHERE h.keyword = :keyword")
    void SearchCount(@Param("keyword") String keyword);

    boolean existsByKeyword(String keyword);

}

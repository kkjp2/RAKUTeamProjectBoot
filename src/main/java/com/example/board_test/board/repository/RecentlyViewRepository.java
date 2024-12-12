package com.example.board_test.board.repository;

import com.example.board_test.board.entity.RecentlyViewEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecentlyViewRepository extends JpaRepository<RecentlyViewEntity, Long> {

    List<RecentlyViewEntity> findByMemberOrderByRidDesc(MemberEntity member);

}

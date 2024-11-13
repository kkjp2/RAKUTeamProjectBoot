package com.example.board_test.board.repository;

import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FavoriteEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    //중복 판별
    boolean existsMemberAndBoard(MemberEntity member, BoardEntity board);


    //사용자의 즐겨찾기 목록
    List<FavoriteEntity> findByMember(MemberEntity member);

    Optional<FavoriteEntity> findByMemberAndBoard(MemberEntity member,BoardEntity board);

}

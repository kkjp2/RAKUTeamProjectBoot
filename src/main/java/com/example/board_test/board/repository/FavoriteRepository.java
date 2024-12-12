package com.example.board_test.board.repository;

import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.FavoriteEntity;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    //중복 판별
    boolean existsByMemberAndBoard(MemberEntity member, BoardEntity board);
    boolean existsByMemberAndFestivalBoard(MemberEntity member,FestivalBoardEntity festivalBoard);



    //사용자의 즐겨찾기 목록
    List<FavoriteEntity> findByMember(MemberEntity member);

    Optional<FavoriteEntity> findByMemberAndBoard(MemberEntity member,BoardEntity board);

    Optional<Object> findByMemberAndFestivalBoard(MemberEntity memberEntity, FestivalBoardEntity festivalBoard);
}

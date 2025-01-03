package rakuproject.raku.domain.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.entity.FavoriteEntity;
import rakuproject.raku.domain.board.entity.FestivalBoardEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {

    //중복 판별
    boolean existsByMemberAndBoard(MemberEntity member, BoardEntity board);
    boolean existsByMemberAndFestivalBoard(MemberEntity member, FestivalBoardEntity festivalBoard);



    //사용자의 즐겨찾기 목록
    List<FavoriteEntity> findByMember(MemberEntity member);

    Optional<FavoriteEntity> findByMemberAndBoard(MemberEntity member,BoardEntity board);

    Optional<Object> findByMemberAndFestivalBoard(MemberEntity memberEntity, FestivalBoardEntity festivalBoard);
}

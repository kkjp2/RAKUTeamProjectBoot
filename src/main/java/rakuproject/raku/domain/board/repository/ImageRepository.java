package rakuproject.raku.domain.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.entity.FestivalBoardEntity;
import rakuproject.raku.domain.board.entity.ImageEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.List;
@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {

    List<ImageEntity> findByBoard(BoardEntity board);

    List<ImageEntity> findByFestivalBoard(FestivalBoardEntity festivalBoard);

    List<ImageEntity> findByMember(MemberEntity member);
}

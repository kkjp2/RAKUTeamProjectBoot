package rakuproject.raku.domain.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.board.entity.FestivalCommentEntity;

import java.util.List;

@Repository
public interface FestivalCommentRepository extends JpaRepository<FestivalCommentEntity, Long> {
    List<FestivalCommentEntity> findByFestivalBoard_fbId(Long fId);
    //Optional<FestivalCommentEntity> findByPostsIdAndId(Long fboard_id, Long fc_id);
}

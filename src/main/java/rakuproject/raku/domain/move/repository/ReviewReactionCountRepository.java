package rakuproject.raku.domain.move.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.entity.ReviewReactionCountEntity;
import rakuproject.raku.domain.move.entity.enums.ReactionType;

import java.util.Optional;

@Repository
public interface ReviewReactionCountRepository extends JpaRepository<ReviewReactionCountEntity, Long> {
    Optional<ReviewReactionCountEntity> findByReviewId(MoveReviewEntity reviewId);
}





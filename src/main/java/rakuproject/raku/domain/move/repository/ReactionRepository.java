package rakuproject.raku.domain.move.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.entity.ReactionEntity;
import rakuproject.raku.domain.move.entity.enums.ReactionType;

@Repository
public interface ReactionRepository extends JpaRepository<ReactionEntity, Long> {

    // 使用 @Query 注解指定查询
    @Query("SELECT COUNT(r) FROM ReactionEntity r WHERE r.reviewId.reviewId = :reviewId AND r.reactionType = :reactionType")
    long countByReviewIdAndReactionType(@Param("reviewId") Long reviewId, @Param("reactionType") ReactionType reactionType);

    // 查找指定用户对特定评论的反应记录
    @Query("SELECT r FROM ReactionEntity r WHERE r.reviewId = :review AND r.userKey = :user")
    ReactionEntity findByReviewIdAndUserKey(@Param("review") MoveReviewEntity review, @Param("user") MemberEntity user);
}




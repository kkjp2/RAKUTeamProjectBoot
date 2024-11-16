package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.entity.ReactionEntity;
import rakuproject.raku.domain.move.entity.ReviewReactionCountEntity;
import rakuproject.raku.domain.move.entity.enums.ReactionType;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.repository.ReactionRepository;
import rakuproject.raku.domain.move.repository.ReviewReactionCountRepository;

import java.util.Optional;

@Service
public class ReviewReactionCountService {
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private MoveReviewRepository moveReviewRepository;
    @Autowired
    private ReviewReactionCountRepository reviewReactionCountRepository;
    @Autowired
    private MemberRepository memberRepository;

    // 获取评论的点赞和点踩数量
    public ReactionCountResponse getReactionCount(Long reviewId, Long userKey) {
        MoveReviewEntity review = moveReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found for ID: " + reviewId));

        // 从 userKey 获取 MemberEntity 对象
        MemberEntity user = memberRepository.findById(userKey)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userKey));

        long likeCount = reactionRepository.countByReviewIdAndReactionType(review.getReviewId(), ReactionType.LIKE);
        long dislikeCount = reactionRepository.countByReviewIdAndReactionType(review.getReviewId(), ReactionType.DISLIKE);

        int reactionValue = 0; // 默认没有反应
        ReactionEntity userReaction = reactionRepository.findByReviewIdAndUserKey(review, user);

        if (userReaction != null) { // 检查是否有反应记录
            reactionValue = userReaction.getReactionType() == ReactionType.LIKE ? 1 : -1;
        }
        return new ReactionCountResponse(likeCount, dislikeCount, reactionValue);
    }

    // 更新或创建反应统计
    public void updateOrCreateReactionCount(MoveReviewEntity review, ReactionType reactionType, int increment, MemberEntity user) {
        ReviewReactionCountEntity count = reviewReactionCountRepository.findByReviewId(review)
                .orElseGet(() -> {
                    // 如果没有记录，创建新的 ReviewReactionCountEntity
                    ReviewReactionCountEntity newCount = new ReviewReactionCountEntity();
                    newCount.setReviewId(review);
                    return newCount;
                });

        // 根据 ReactionType 更新点赞或点踩计数
        if (reactionType == ReactionType.LIKE) {
            count.setLikeCount(count.getLikeCount() + increment);
        } else if (reactionType == ReactionType.DISLIKE) {
            count.setDislikeCount(count.getDislikeCount() + increment);
        }

        reviewReactionCountRepository.save(count);
    }
}

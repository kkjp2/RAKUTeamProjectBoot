package rakuproject.raku.domain.move.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
public class ReactionService {

    @Autowired
    private MoveReviewRepository moveReviewRepository;
    @Autowired
    private ReactionRepository reactionRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private ReviewReactionCountService reviewReactionCountService;
    @Autowired
    private ReviewReactionCountRepository reviewReactionCountRepository;


    // 点赞
    @Transactional
    public void likeComment(Long reviewId, Long userKey) {
        addOrUpdateReaction(reviewId, userKey, ReactionType.LIKE);
    }

    // 点踩
    @Transactional
    public void dislikeComment(Long reviewId, Long userKey) {
        addOrUpdateReaction(reviewId, userKey, ReactionType.DISLIKE);
    }

    @Transactional
    // 添加或更新点赞或点踩反应
    public void addOrUpdateReaction(Long reviewId, Long userKey, ReactionType newReactionType) {
        // 获取 MemberEntity 和 MoveReviewEntity
        MemberEntity user = memberRepository.findById(userKey)
                .orElseThrow(() -> new RuntimeException("没有对应的会员"));
        MoveReviewEntity review = moveReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("没有对应的评论"));

        // 检查是否已存在用户对该评论的反应
        ReactionEntity existingReaction = reactionRepository.findByReviewIdAndUserKey(review, user);

        if (existingReaction != null) {
            // 如果现有反应与新反应类型不同，重置之前的计数
            if (!existingReaction.getReactionType().equals(newReactionType)) {
                reviewReactionCountService.updateOrCreateReactionCount(review, existingReaction.getReactionType(), -1, user); // 减少旧的反应计数
                existingReaction.setReactionType(newReactionType); // 更新为新的反应类型
                reviewReactionCountService.updateOrCreateReactionCount(review, newReactionType, 1, user); // 增加新的反应计数
            }
        } else {
            // 创建新的 ReactionEntity
            ReactionEntity newReaction = new ReactionEntity();
            newReaction.setReviewId(review);
            newReaction.setUserKey(user);
            newReaction.setReactionType(newReactionType);
            reactionRepository.save(newReaction);

            // 增加新反应的计数
            reviewReactionCountService.updateOrCreateReactionCount(review, newReactionType, 1, user);
        }
    }

    public ReactionCountResponse cancelReaction(Long reviewId, Long userKey) {
        // 通过 reviewId 查找 MoveReviewEntity
        MoveReviewEntity review = moveReviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found for ID: " + reviewId));

        // 通过 userKey 查找 MemberEntity
        MemberEntity user = memberRepository.findById(userKey)
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + userKey));

        // 查找用户对该评论的反应记录
        ReactionEntity reaction = reactionRepository.findByReviewIdAndUserKey(review, user);
        if (reaction != null) {
            reactionRepository.delete(reaction); // 删除反应记录
        } else {
            throw new RuntimeException("Reaction not found for review ID: " + reviewId + " and user ID: " + userKey);
        }

        // 获取更新后的点赞和点踩计数
        long likeCount = reactionRepository.countByReviewIdAndReactionType(reviewId, ReactionType.LIKE);
        long dislikeCount = reactionRepository.countByReviewIdAndReactionType(reviewId, ReactionType.DISLIKE);

        return new ReactionCountResponse(likeCount, dislikeCount, 0); // 返回 reactionValue 为 0 表示无反应
    }

    public ReactionEntity getReactionByUserAndReview(Long userKey, Long reviewId) {
        MemberEntity user = memberRepository.findById(userKey)
                .orElseThrow(() -> new RuntimeException("User not found"));

        MoveReviewEntity review = new MoveReviewEntity();
        review.setReviewId(reviewId);

        return reactionRepository.findByReviewIdAndUserKey(review, user);
    }

    @Transactional
    public void deleteAllReactions() {
        // 删除所有点赞和点踩记录
        reactionRepository.deleteAll();

        // 删除所有统计记录
        reviewReactionCountRepository.deleteAll();
    }
}


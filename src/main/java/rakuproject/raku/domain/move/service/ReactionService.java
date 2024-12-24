package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.move.dto.ReactionDTO;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.entity.ReactionEntity;
import rakuproject.raku.domain.move.entity.ReviewReactionCountEntity;
import rakuproject.raku.domain.move.entity.enums.ReactionType;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.repository.ReactionRepository;
import rakuproject.raku.domain.move.repository.ReviewReactionCountRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final MoveReviewRepository moveReviewRepository;
    private final MemberRepository memberRepository;
    private final ReviewReactionCountRepository reactionCountRepository;

    @Transactional
    public void reactToReview(Long reviewId, Long userKey, String reactionType) {
        // 리뷰 가져오기
        MoveReviewEntity review = moveReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("아이디에 해당되는 리뷰 없음."));

        // userKey 로 회원 정보 가져오기
        MemberEntity user = memberRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보 없음."));

        // ReactionType 을 ReactionType 열거형으로 변환하기
        ReactionType type = ReactionType.valueOf(reactionType.toUpperCase());

        // 반응 기록 있는지 확인
        Optional<ReactionEntity> existingReaction = reactionRepository.findByReviewIdAndUserKey(review, user);

        if (existingReaction.isPresent()) {
            ReactionEntity reaction = existingReaction.get();

            if (reaction.getReactionType() == type) {
                // 이미 동일한 반응이 있을 경우 삭제(반응취소)
                reactionRepository.delete(reaction);
                updateReactionCount(review, type, -1);
            } else {
                // 반응이 다르면, 새로운 타입으로 업데이트.
                updateReactionCount(review, reaction.getReactionType(), -1);
                reaction.setReactionType(type);
                reactionRepository.save(reaction);
                updateReactionCount(review, type, 1);
            }
        } else {
            // 반응 기록이 없을 경우, 새로운 반응을 생성
            ReactionEntity newReaction = ReactionEntity.builder()
                    .reviewId(review)
                    .userKey(user)
                    .reactionType(type)
                    .createdAt(LocalDateTime.now())
                    .build();
            reactionRepository.save(newReaction);
            updateReactionCount(review, type, 1);
        }
    }

    // 좋아요 싫어요 수 카운트
    private void updateReactionCount(MoveReviewEntity review, ReactionType type, int delta) {

        ReviewReactionCountEntity countEntity = reactionCountRepository.findByReviewId(review)
                .orElseGet(() -> {
                    // 새 ReactionCount 엔티티 생성
                    ReviewReactionCountEntity newEntity = new ReviewReactionCountEntity();
                    newEntity.setReviewId(review);
                    newEntity.setLikeCount(0); // 초기 좋아요 0
                    newEntity.setDislikeCount(0); // 초기 싫어요 0
                    return reactionCountRepository.save(newEntity);
                });
        if (type == ReactionType.LIKE) {
            int newLikeCount = countEntity.getLikeCount() + delta;
            countEntity.setLikeCount(Math.max(newLikeCount, 0)); // 保证likeCount不会变成负数
        } else if (type == ReactionType.DISLIKE) {
            int newDislikeCount = countEntity.getDislikeCount() + delta;
            countEntity.setDislikeCount(Math.max(newDislikeCount, 0)); // 保证dislikeCount不会变成负数
        }
        reactionCountRepository.save(countEntity);
    }
}

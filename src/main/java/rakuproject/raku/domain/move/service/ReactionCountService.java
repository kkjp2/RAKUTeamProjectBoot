package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;
import rakuproject.raku.domain.move.entity.ReviewReactionCountEntity;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.repository.ReviewReactionCountRepository;

@Service
@RequiredArgsConstructor
@RestController
public class ReactionCountService {

    @Autowired
    private final MoveReviewRepository moveReviewRepository;
    @Autowired
    private final ReviewReactionCountRepository reactionCountRepository;
    @Autowired
    private final MemberRepository memberRepository;

    // 좋아요와 밟는 횟수 가져오기
    public ReactionCountResponse getReactionCount(Long reviewId) {
        // 根据 reviewId 查找评论 reviewId 로 리뷰 찾기
        MoveReviewEntity review = moveReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰를 찾을 수 없습니다."));

        ReviewReactionCountEntity countEntity = reactionCountRepository.findByReviewId(review)
                .orElseThrow(() -> new IllegalStateException("반응 개수 엔터티를 찾을 수 없습니다."));

        // 返回点赞和点踩的计数
        return new ReactionCountResponse(countEntity.getLikeCount(), countEntity.getDislikeCount());
    }
}

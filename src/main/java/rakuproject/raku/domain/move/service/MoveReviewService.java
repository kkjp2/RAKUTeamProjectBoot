package rakuproject.raku.domain.move.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.dto.ReactionCountResponse;
import rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO;
import rakuproject.raku.domain.move.entity.*;
import rakuproject.raku.domain.move.entity.enums.ReactionType;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.domain.move.repository.ReactionRepository;
import rakuproject.raku.domain.move.repository.ReviewReactionCountRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MoveReviewService {

    @Autowired
    private MoveReviewRepository moveReviewRepository;

    @Autowired
    private MoveCompanyRepository moveCompanyRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewReactionCountService reviewReactionCountService;

    public void addComment(MoveReviewDTO dto) {
        MoveReviewEntity review = new MoveReviewEntity();

        // 根据 companyId 获取 MoveCompanyEntity 实例
        MoveCompanyEntity company = moveCompanyRepository.findById((int) dto.getCompanyId().longValue())
                .orElseThrow(() -> new RuntimeException("Company not found for ID: " + dto.getCompanyId()));
        review.setCompany(company); // 设置公司实体

        // 根据 userKey 获取 MemberEntity 实例
        MemberEntity user = memberRepository.findById(dto.getUserKey())
                .orElseThrow(() -> new RuntimeException("User not found for ID: " + dto.getUserKey()));
        review.setUserKey(user); // 设置用户实体

        review.setRating(dto.getRating());
        review.setComment(dto.getComment());
        review.setServiceDate(dto.getServiceDate());
        review.setPrice(dto.getPrice());
        review.setRegion(dto.getRegion());

        moveReviewRepository.save(review);
    }

    public List<ReviewWithCompanyDTO> getAllReviewsWithCompanyName(Long userKey) {
        return moveReviewRepository.findAll().stream().map(review -> {
            String companyName = moveCompanyRepository.findById(review.getCompany().getId())
                    .map(MoveCompanyEntity::getName)
                    .orElse("Unknown Company");
            String companyLogo = moveCompanyRepository.findById(review.getCompany().getId())
                    .map(company -> {
                        UploadFileEntity file = company.getFileId();
                        if (file != null) {
                            return "/uploads/" + file.getFolderPath() + "/" + file.getUuid() + "_"+ file.getFileName();
                        }
                        return null; // 如果没有文件，返回 null
                    })
                    .orElse(null);

            // 使用 ReviewReactionCountService 获取点赞和点踩数量以及 reactionValue
            ReactionCountResponse reactionCountResponse = reviewReactionCountService.getReactionCount(review.getReviewId(), userKey);

            return new ReviewWithCompanyDTO(
                    review.getReviewId(),
                    review.getComment(),
                    review.getRating(),
                    review.getRegion(),
                    review.getPrice(),
                    review.getServiceDate(),
                    companyName,
                    companyLogo,
                    (int)reactionCountResponse.getLikeCount(), // 从 ReactionCountResponse 获取 likeCount
                    reactionCountResponse.getReactionValue()
            );
        }).collect(Collectors.toList());
    }


    public List<MoveReviewEntity> getCommentsByCompanyId(Long companyId) {
        return moveReviewRepository.findByCompanyId(companyId);
    }
}

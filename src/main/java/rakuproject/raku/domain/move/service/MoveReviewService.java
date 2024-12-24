package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.entity.*;
import rakuproject.raku.domain.move.exception.ResourceNotFoundException;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class MoveReviewService {

    @Autowired
    private final MoveReviewRepository moveReviewRepository;

    @Autowired
    private final MoveCompanyRepository moveCompanyRepository;

    @Autowired
    private final MemberRepository memberRepository;

    public void addComment(MoveReviewDTO dto) {
        MoveReviewEntity review = new MoveReviewEntity();

        if (dto.getCompanyId() == null) {
            throw new IllegalArgumentException("公司ID不能为空");
        }

        // Check if userKey is valid
        if (dto.getUserKey() == null) {
            throw new IllegalArgumentException("用户ID不能为空");
        }

        //companyId에서 MoveCompanyEntity 인스턴스 가져오기
        MoveCompanyEntity company = moveCompanyRepository.findById((int) dto.getCompanyId().longValue())
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다. ID : " + dto.getCompanyId()));
        review.setCompany(company); // 设置公司实体 회사 엔티티 설정.

        MemberEntity member = memberRepository.findById(dto.getUserKey())
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        MoveReviewEntity moveReviewEntity = MoveReviewEntity.builder()
                .company(company)
                .userKey(member)
                .rating(dto.getRating())
                .comment(dto.getComment())
                .serviceDate(dto.getServiceDate())
                .price(dto.getPrice())
                .region(dto.getRegion())
                .build();

        moveReviewRepository.save(moveReviewEntity);
    }

    //모든 리뷰 가져오기.
    public List<MoveReviewEntity> getAllReviews() {
        return moveReviewRepository.findAll();
    }

    //회사리뷰 가져오기.
    public List<MoveReviewEntity> getCommentsByCompanyId(Long companyId) {
        return moveReviewRepository.findByCompanyId(companyId);
    }
}

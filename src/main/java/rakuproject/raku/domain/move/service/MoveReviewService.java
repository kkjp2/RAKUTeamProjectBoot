package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.move.dto.MoveReviewDTO;
import rakuproject.raku.domain.move.entity.*;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.MoveReviewRepository;
import rakuproject.raku.global.security.MemberSecurity;

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
    private final MemberSecurity memberSecurity;

    public void addComment(MoveReviewDTO dto) {
        MoveReviewEntity review = new MoveReviewEntity();
        MemberDTO memberDTO = memberSecurity.getMember();
        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        //companyId에서 MoveCompanyEntity 인스턴스 가져오기
        MoveCompanyEntity company = moveCompanyRepository.findById((int) dto.getCompanyId().longValue())
                .orElseThrow(() -> new RuntimeException("회사를 찾을 수 없습니다. ID : " + dto.getCompanyId()));
        review.setCompany(company); // 设置公司实体 회사 엔티티 설정.

        MoveReviewEntity moveReviewEntity = MoveReviewEntity.builder()
                .company(company)
                .userKey(memberEntity)
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

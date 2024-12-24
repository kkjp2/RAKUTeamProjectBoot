package rakuproject.raku.domain.rental.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.rental.dto.RentalReviewDTO;
import rakuproject.raku.domain.rental.dto.response.RentalReviewResponseDTO;
import rakuproject.raku.domain.rental.entity.RentalBoardEntity;
import rakuproject.raku.domain.rental.entity.RentalReviewEntity;
import rakuproject.raku.domain.rental.repository.RentalBoardRepository;
import rakuproject.raku.domain.rental.repository.RentalReviewRepository;
import rakuproject.raku.global.security.MemberSecurity;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentalReviewService {
    @Autowired
    private final RentalReviewRepository rentalReviewRepository;
    @Autowired
    private final RentalBoardRepository rentalBoardRepository;
    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void register(RentalReviewDTO reviewDTO)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        RentalBoardEntity rentalBoard=rentalBoardRepository.findById(reviewDTO.getRentalBoardId())
                .orElseThrow(()-> new IllegalArgumentException("렌탈 게시판이 존재하지 않습니다"));
        RentalReviewEntity review= RentalReviewEntity.builder().
                rating(reviewDTO.getRating()).
                reviewContent(reviewDTO.getReviewContent()).
                startDate(reviewDTO.getStartDate()).
                endDate(reviewDTO.getEndDate()).
                member(memberEntity).
                rentalBoard(rentalBoard).
                build();
        rentalReviewRepository.save(review);
    }
    @Transactional(readOnly = true)
    public RentalReviewResponseDTO getReviewById(Long reviewId)
    {
        RentalReviewEntity review=rentalReviewRepository.findById(reviewId)
                .orElseThrow(()-> new IllegalArgumentException("리뷰가 존재하지 않습니다"));
        return new RentalReviewResponseDTO(review);
    }

    @Transactional(readOnly = true)
    public List<RentalReviewResponseDTO> getReviewsByRentalBoardId(Long rbId)
    {
        return rentalReviewRepository.findByRentalBoard_RbId(rbId).stream()
                .map(RentalReviewResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateReview(RentalReviewDTO reviewDTO)
    {
        MemberDTO memberDTO= memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        RentalReviewEntity review= rentalReviewRepository.findById(reviewDTO.getReviewId())
                .orElseThrow(()-> new IllegalArgumentException("리뷰가 존재하지 않습니다"));
        if(!review.getMember().equals(memberEntity)){
            throw new SecurityException("접근 할수없는 사용자");
        }
        review.setReviewContent(reviewDTO.getReviewContent());
        review.setRating(reviewDTO.getRating());
    }

    @Transactional
    public void deleteReview(Long reviewId)
    {
        MemberDTO memberDTO = memberSecurity.getMember();
        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        RentalReviewEntity review = rentalReviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("리뷰가 존재하지 않습니다."));

        if (!review.getMember().equals(memberEntity)) {
            throw new SecurityException("리뷰 작성자가 아니면 삭제할 수 없습니다.");
        }

        rentalReviewRepository.delete(review);

    }


}

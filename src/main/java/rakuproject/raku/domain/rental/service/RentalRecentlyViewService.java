package rakuproject.raku.domain.rental.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.rental.entity.RentalRecentlyViewEntity;
import rakuproject.raku.domain.rental.repository.RentalRecentlyViewRepository;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalRecentlyViewService {
    @Autowired
    private final RentalRecentlyViewRepository recentlyViewRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<RentalRecentlyViewEntity> getRentalRecentlyViewd(Long userKey)
    {
        MemberEntity member=memberRepository.findById(userKey)
                .orElseThrow(()-> new IllegalArgumentException("찾을 수 없습니다."));

        List<RentalRecentlyViewEntity> rentalRecentlyViewed=recentlyViewRepository.findRentalRecentlyViews(userKey);
        log.info("사용자 {}의 최근 본 게시글 조회 결과: {}", userKey, rentalRecentlyViewed);
        return rentalRecentlyViewed;
    }
}

package com.example.board_test.domain.rental.service;


import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.domain.rental.entity.RentalRecentlyViewEntity;
import com.example.board_test.domain.rental.repository.RentalRecentlyViewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

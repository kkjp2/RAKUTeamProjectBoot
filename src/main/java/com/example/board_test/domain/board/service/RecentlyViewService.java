package com.example.board_test.domain.board.service;


import com.example.board_test.domain.board.entity.RecentlyViewEntity;
import com.example.board_test.domain.board.repository.RecentlyViewRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.global.security.MemberSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentlyViewService {
    @Autowired
    private final RecentlyViewRepository recentlyViewRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberSecurity memberSecurity;

//    @Transactional(readOnly = true)
//    public List<RecentlyViewEntity> getRecentlyViewedPosts()
//    {
//        MemberDTO memberDTO=memberSecurity.getMember();
//        if(memberDTO==null){
//            throw new IllegalArgumentException("로그인 사용자만 조회할 수 있습니다.");
//        }
//        return recentlyViewRepository.findRecentlyViews(MemberMapper.createEntity(memberDTO));
//    }
    @Transactional(readOnly = true)
    public List<RecentlyViewEntity> getRecentlyViewed(Long userKey) {
        // 사용자 엔티티를 검증 (Optional 활용)
        MemberEntity member = memberRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 최근 본 게시글 가져오기
        List<RecentlyViewEntity> recentlyViewed = recentlyViewRepository.findRecentlyViews(userKey);

        log.info("사용자 {}의 최근 본 게시글 조회 결과: {}", userKey, recentlyViewed);
        return recentlyViewed;
    }
}

package rakuproject.raku.domain.mypage.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.mypage.repository.DeleteRepository;

@Service
@RequiredArgsConstructor
public class DeleteServiceImpl {

    @Autowired
    private final DeleteRepository deleteRepository;


    @Transactional
    public void deleteUser(Long userkey) {

        // 사용자 정보 확인 및 삭제
        MemberEntity user = deleteRepository.findByUserKey(userkey)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));

        deleteRepository.delete(user);
    }
}

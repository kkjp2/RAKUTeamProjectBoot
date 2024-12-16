package rakuproject.raku.domain.mypage.service;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.repository.IdEditRepository;
import rakuproject.raku.domain.mypage.repository.NickEditRepository;

@Service
@RequiredArgsConstructor
public class IdEditServiceImpl {
    @Autowired
    public final IdEditRepository idEditRepository;

    public void updateId(Long userKey, String newId) {
        MemberEntity user = idEditRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userKey));

        user.setId(newId);
        idEditRepository.save(user); // 변경된 닉네임 저장
    }
}

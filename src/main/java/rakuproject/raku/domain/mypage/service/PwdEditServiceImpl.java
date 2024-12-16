package rakuproject.raku.domain.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.repository.NickEditRepository;
import rakuproject.raku.domain.mypage.repository.PwdEditRepository;

@Service
@RequiredArgsConstructor
public class PwdEditServiceImpl {

    @Autowired
    public final PwdEditRepository pwdEditRepository;

    private final PasswordEncoder passwordEncoder;

    public void updatePwd(Long userKey, String newPwd) {
        MemberEntity user = pwdEditRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userKey));

        String encodedPassword = passwordEncoder.encode(newPwd);
        user.setPwd(encodedPassword);
        pwdEditRepository.save(user); // 변경된 닉네임 저장
    }
}

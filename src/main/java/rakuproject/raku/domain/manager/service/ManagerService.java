package rakuproject.raku.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {
    @Autowired
    private ManagerQueryService managerQueryService;

    // 메일로 사용자 검색
    public Optional<MemberEntity> searchByEmail(String email) {
        return managerQueryService.findByEmail(email);
    }

    // 닉네임으로 사용가 검색
    public List<MemberEntity> searchByNickname(String nickname) {
        return managerQueryService.findByNickname(nickname);
    }

    // 유저인가 admin 인가
    public List<MemberEntity> searchByRole(MemberRole role) {
        return managerQueryService.findByRole(role);
    }

}

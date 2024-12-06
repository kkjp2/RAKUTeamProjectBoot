package rakuproject.raku.domain.manager.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;
import rakuproject.raku.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {
    @Autowired
    private final ManagerQueryService managerQueryService;
    @Autowired
    private final MemberRepository memberRepository;

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

    //회원 삭제 기능
    public void deleteUser(String id) {
        // 检查用户是否存在
        MemberEntity member = memberRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("사용자가 존재하지 않습니다"));

        // 根据实体删除用户
        memberRepository.delete(member);
    }
}

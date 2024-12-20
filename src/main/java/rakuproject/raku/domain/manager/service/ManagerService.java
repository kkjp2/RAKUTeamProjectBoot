package rakuproject.raku.domain.manager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ManagerService {
    @Autowired
    private final ManagerQueryService managerQueryService;
    // 메일로 사용자 검색
    public Optional<MemberEntity> searchByEmail(String email) {

        return managerQueryService.findByEmail(email);
    }


}

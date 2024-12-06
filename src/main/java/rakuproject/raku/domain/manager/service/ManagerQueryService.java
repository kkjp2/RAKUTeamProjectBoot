package rakuproject.raku.domain.manager.service;

import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;

import java.util.List;
import java.util.Optional;

public interface ManagerQueryService {
    Optional<MemberEntity> findByEmail(String email);

    List<MemberEntity> findByNickname(String nickname);

    List<MemberEntity> findByRole(MemberRole role);
}

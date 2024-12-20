package rakuproject.raku.domain.manager.service;

import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public interface ManagerQueryService {
    Optional<MemberEntity> findByEmail(String email);
    List<MemberEntity> findByNickname(String nickname);
}

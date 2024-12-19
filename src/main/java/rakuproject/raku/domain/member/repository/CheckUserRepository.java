package rakuproject.raku.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface CheckUserRepository extends JpaRepository<MemberEntity, Long> {
    Optional<MemberEntity> findByUserKey(Long userkey); // 사용자 ID로 조회
}

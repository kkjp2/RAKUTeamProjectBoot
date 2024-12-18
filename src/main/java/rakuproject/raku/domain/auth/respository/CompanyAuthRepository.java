package rakuproject.raku.domain.auth.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface CompanyAuthRepository extends JpaRepository<CompanyMemberEntity, Long> {
    boolean existsById(String id);
    Optional<CompanyMemberEntity> findById(String id);
}

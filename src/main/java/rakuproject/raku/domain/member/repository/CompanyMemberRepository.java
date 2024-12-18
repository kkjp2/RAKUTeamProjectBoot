package rakuproject.raku.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.Optional;

public interface CompanyMemberRepository extends JpaRepository<CompanyMemberEntity, Long> {
    Optional<CompanyMemberEntity> findById(String id);
}

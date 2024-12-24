package rakuproject.raku.domain.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;

import java.util.Optional;

public interface CompanyFindRepository extends JpaRepository<CompanyMemberEntity, Long> {
    Optional<CompanyMemberEntity> findByRealtyMemberKey(Long realtyMemberKey);
}

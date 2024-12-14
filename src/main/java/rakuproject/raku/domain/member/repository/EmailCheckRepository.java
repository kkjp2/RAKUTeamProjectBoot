package rakuproject.raku.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;

public interface EmailCheckRepository extends JpaRepository<MemberEntity, Long> {
    boolean existsById(String id);
}

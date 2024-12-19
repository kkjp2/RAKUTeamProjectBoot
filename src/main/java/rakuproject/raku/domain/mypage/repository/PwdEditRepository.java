package rakuproject.raku.domain.mypage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;

public interface PwdEditRepository extends JpaRepository<MemberEntity, Long> {
}

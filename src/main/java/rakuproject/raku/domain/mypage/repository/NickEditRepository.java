package rakuproject.raku.domain.mypage.repository;

import rakuproject.raku.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NickEditRepository extends JpaRepository<MemberEntity, Long> {
    // 사용자 정의 메서드가 필요하다면 추가 가능
}

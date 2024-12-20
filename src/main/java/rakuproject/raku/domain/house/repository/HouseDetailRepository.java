package rakuproject.raku.domain.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.house.entity.HouseDetail;

import java.util.Optional;

public interface HouseDetailRepository extends JpaRepository<HouseDetail, Long> {

    // bBuildNumber 필드 기반으로 데이터 조회
    Optional<HouseDetail> findByBuildNumber(Long bBuildNumber);
}

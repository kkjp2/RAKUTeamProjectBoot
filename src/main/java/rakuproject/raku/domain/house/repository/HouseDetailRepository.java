package rakuproject.raku.domain.house.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.house.entity.HouseDetail;

import java.util.Optional;

@Repository
public interface HouseDetailRepository extends JpaRepository<HouseDetail, Long> {
    Optional<HouseDetail> findByBuildNumber(Long buildNumber);
}

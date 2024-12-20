package rakuproject.raku.domain.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rakuproject.raku.domain.rental.entity.RentalRecentlyViewEntity;

import java.util.List;

public interface RentalRecentlyViewRepository extends JpaRepository<RentalRecentlyViewEntity, Long> {
    @Query(value = "SELECT * FROM rental_recently WHERE user_key = :userKey ORDER BY r_id DESC", nativeQuery = true)
    List<RentalRecentlyViewEntity> findRentalRecentlyViews(@Param("userKey") Long userKey);
}

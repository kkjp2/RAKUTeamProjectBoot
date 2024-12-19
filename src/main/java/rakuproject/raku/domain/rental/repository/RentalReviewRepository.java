package rakuproject.raku.domain.rental.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.rental.entity.RentalReviewEntity;

import java.util.List;

public interface RentalReviewRepository extends JpaRepository<RentalReviewEntity, Long> {

    List<RentalReviewEntity> findByRentalBoard_RbId(Long rbId);
}

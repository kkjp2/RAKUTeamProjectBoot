package rakuproject.raku.domain.move.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveReviewEntity;

import java.util.List;

@Repository
public interface MoveReviewRepository extends JpaRepository<MoveReviewEntity, Long> {
    List<MoveReviewEntity> findByCompanyId(Long companyId);

    @Query("SELECT new rakuproject.raku.domain.move.dto.ReviewWithCompanyDTO( " +
            "r.reviewId, r.comment, r.rating, r.region, r.price, r.serviceDate, c.name, " +
            "c.fileId.fileName, c.fileId.folderPath, c.fileId.uuid) " +
            "FROM MoveReviewEntity r " +
            "JOIN r.company c " +
            "ORDER BY r.reviewId DESC")
    List<ReviewWithCompanyDTO> fetchReviewsWithLogos();


}


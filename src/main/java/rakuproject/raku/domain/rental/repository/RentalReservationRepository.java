package rakuproject.raku.domain.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.rental.entity.RentalReservationEntity;

public interface RentalReservationRepository extends JpaRepository<RentalReservationEntity, Long> {

}

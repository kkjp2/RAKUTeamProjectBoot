package rakuproject.raku.domain.rental.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.rental.entity.RentalChatRoomEntity;

@Repository
public interface RentalChatRoomRepository extends JpaRepository<RentalChatRoomEntity, Long> {

}

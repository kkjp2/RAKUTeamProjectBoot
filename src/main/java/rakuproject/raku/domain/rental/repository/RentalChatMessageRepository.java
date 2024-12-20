package rakuproject.raku.domain.rental.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.rental.entity.RentalChatMessageEntity;

import java.util.List;

@Repository
public interface RentalChatMessageRepository extends JpaRepository<RentalChatMessageEntity, Long> {
    List<RentalChatMessageEntity> findByChatRoom_RoomIdOrderByMsgSendTime(Long roomId);
}

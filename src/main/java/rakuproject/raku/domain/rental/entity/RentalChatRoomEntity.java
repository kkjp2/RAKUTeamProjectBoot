package rakuproject.raku.domain.rental.entity;



import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rental_chat_room")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @Column(nullable = false, updatable = false)
    private LocalDateTime created;

    @Column
    private LocalDateTime updated;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userAId", nullable = false)
    private MemberEntity userA; // 사용자 A

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userBId", nullable = false)
    private MemberEntity userB; // 사용자 B

    @PrePersist
    protected void onCreate()
    {
        this.created=LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate()
    {
        this.updated=LocalDateTime.now();
    }

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RentalChatMessageEntity> messages = new ArrayList<>();



}

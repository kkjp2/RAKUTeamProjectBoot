package rakuproject.raku.domain.rental.entity;


import jakarta.persistence.*;
import lombok.*;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental_chat_read")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentalChatReadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "msgId", nullable = false)
    private RentalChatMessageEntity message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private MemberEntity member;

    @Column(nullable = false)
    private LocalDateTime readTime;


}

package com.example.board_test.domain.rental.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "rental_chat_message")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long msgId;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private RentalChatRoomEntity chatRoom;

    @Column(columnDefinition = "TEXT")
    private String msgContent;

    @Column
    private String msgType;

    @Column(nullable = false)
    private Timestamp msgSendTime;

    @Column
    private String msgImage;

    @Column
    private Boolean readCheck;








}

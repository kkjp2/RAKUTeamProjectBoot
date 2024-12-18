package com.example.board_test.domain.rental.entity;

import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "roomId", nullable = false)
    private RentalChatRoomEntity chatRoom;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String msgContent;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MsgType msgType;

    @Column(nullable = false)
    private LocalDateTime msgSendTime;

    @Column
    private String msgImage;

    @Column(nullable = false)
    private Boolean readCheck=false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "senderId", nullable = false)
    private MemberEntity sender;

    public enum MsgType{
        TEXT,
        IMAGE,
        VIDEO,
        FILE
    }

    @PrePersist
    protected void onCreate()
    {
        if(this.msgSendTime == null)
            this.msgSendTime = LocalDateTime.now();
    }








}

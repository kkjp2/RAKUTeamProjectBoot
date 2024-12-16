package com.example.board_test.domain.rental.entity;

import com.example.board_test.domain.board.entity.TimeEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalImageEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rImgId;

    @Column(length = 255, nullable = false)
    private String rentalImage;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId")
    private RentalBoardEntity rentalBoard;


}
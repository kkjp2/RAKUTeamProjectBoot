package com.example.board_test.domain.rental.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental_recently")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RentalRecentlyViewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalRId;

    @ManyToOne
    @JoinColumn(name = "userKey",nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId",nullable = false)
    private RentalBoardEntity rentalBoard;


}

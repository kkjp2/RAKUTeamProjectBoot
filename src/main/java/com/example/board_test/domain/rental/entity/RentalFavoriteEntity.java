package com.example.board_test.domain.rental.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rental_favorite")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RentalFavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rentalFavId;

    @ManyToOne
    @JoinColumn(name = "userKey")
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "rbId")
    private RentalBoardEntity rentalBoard;

}

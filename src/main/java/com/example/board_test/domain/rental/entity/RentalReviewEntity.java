package com.example.board_test.domain.rental.entity;

import com.example.board_test.domain.board.entity.TimeEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental_review")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class RentalReviewEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    //평점
    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String reviewContent;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;




    @ManyToOne
    @JoinColumn(name = "rbId", nullable = false)
    private RentalBoardEntity rentalBoard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private MemberEntity member;

    public void Rating(int rating)
    {
        if(rating <1 || rating> 5)
        {
            throw new IllegalArgumentException("평점은 1에서 5까지");
        }
        this.rating=rating;
    }






}

package com.example.board_test.domain.rental.entity;

import com.example.board_test.domain.board.entity.TimeEntity;
import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "rental_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RentalBoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rbId;

    //렌탈 글 내용
    @Column(columnDefinition = "TEXT",nullable = false)
    private String rentalContent;

    //렌탈 제목
    @Column(length = 500, nullable = false)
    private String rentalTitle;

    //렌탈 조회수
    @Column(columnDefinition = "integer default 0",nullable = false)
    private int rentalViewCnt;

    //렌탈 글 임시저장
    @Column(nullable = false)
    private boolean rentalSave;

    //렌탈 글 쓸 지역 카테고리.
    @Column(length = 8)
    private int rentalCategory;


    @Column(length = 255)
    private String rentalImage;

    @Column(nullable = false)
    private int rentalPrice;

    @Column(nullable = false)
    private String rentalAddress;

    @Column(nullable = false)
    private String rentalHomeType;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey",nullable = false)
    private MemberEntity member;

    public void update(String
            rentalTitle, String rentalContent,
                       String rentalImage)
    {
        this.rentalTitle=rentalTitle;
        this.rentalContent=rentalContent;
        this.rentalImage=rentalImage;
    }

















}

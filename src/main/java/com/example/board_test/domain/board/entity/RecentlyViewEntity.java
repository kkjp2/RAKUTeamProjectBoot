package com.example.board_test.domain.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_recently")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class RecentlyViewEntity { //최근 본 게시글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rId;

    @ManyToOne
    @JoinColumn(name = "userKey", nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "nId", nullable = false)
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "fbId",nullable = false)
    private FestivalBoardEntity festivalBoard;




}

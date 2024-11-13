package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_recently")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class RecentlyViewEntity { //최근 본 게시글

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long r_id;

    @ManyToOne
    @JoinColumn(name = "user_key", nullable = false)
    private MemberEntity member;

    @ManyToOne
    @JoinColumn(name = "n_id", nullable = false)
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "f_id",nullable = false)
    private FestivalBoardEntity festivalBoard;




}

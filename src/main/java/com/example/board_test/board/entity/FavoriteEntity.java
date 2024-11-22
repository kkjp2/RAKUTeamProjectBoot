package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_favorite")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FavoriteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favId;
    @ManyToOne
    @JoinColumn(name = "user_key")
    private MemberEntity member;
    @ManyToOne
    @JoinColumn(name = "n_id")
    private BoardEntity board;
    @ManyToOne
    @JoinColumn(name = "f_id")
    private FestivalBoardEntity festivalBoard;



}

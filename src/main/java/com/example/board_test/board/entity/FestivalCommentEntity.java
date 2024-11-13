package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_festival_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FestivalCommentEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fc_id;
    @Column(length = 255)
    private String image;
    @Column(columnDefinition = "Text")
    private String comment;
    @Column(columnDefinition = "Text")
    private String siren;

    @ManyToOne
    @JoinColumn(name = "f_id")
    private FestivalBoardEntity festivalBoard;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private MemberEntity member;
}

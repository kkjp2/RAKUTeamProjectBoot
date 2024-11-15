package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_image")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@ToString
public class ImageEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long img_id;

    @Column(length = 255, nullable = false)
    private String image;

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

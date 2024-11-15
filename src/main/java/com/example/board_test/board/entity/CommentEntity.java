package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notice_comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class CommentEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long comment_id;

    @Column(length = 255)
    private String image;
    @Column(columnDefinition = "Text")
    private String commentText;
    @Column(columnDefinition = "Text")
    private String siren;

    @ManyToOne
    @JoinColumn(name = "n_id")
    private BoardEntity board;

    @ManyToOne
    @JoinColumn(name = "user_key")
    private MemberEntity member;

    public void update(String commentText, String image){
        this.commentText=commentText;
        this.image=image;
    }
}

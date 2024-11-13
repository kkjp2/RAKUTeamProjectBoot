package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "notice_festival_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class FestivalBoardEntity extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long f_id;
    @Column(length = 50,nullable = false)
    private String title;
    @Column(length = 255)
    private String image;
    @Column(columnDefinition = "Text", nullable = false)
    private String content;
    @Column(columnDefinition = "integer default 0")
    private int like;
    @Column(columnDefinition = "integer default 0")
    private int view;
    @Column(length = 8)
    private int category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_key")
    private MemberEntity member;


    @OneToMany(mappedBy = "festivalBoard",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("fc_id asc")
    private List<FestivalCommentEntity> festivalComments;

    public void update(String title, String content,String image)
    {
        this.title=title;
        this.content=content;
        this.image=image;
    }

}

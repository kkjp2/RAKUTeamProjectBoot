package com.example.board_test.board.entity;


import com.example.board_test.domain.member.entity.MemberEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "notice_board")
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long n_id;
    @Column(columnDefinition = "Text", nullable = false)
    private String content;
    @Column(length = 50,nullable = false)
    private String title;
    @Column(columnDefinition = "integer default 0")
    private int like;
    @Column(columnDefinition = "integer default 0")
    private int dislike;
    @Column(columnDefinition = "integer default 0")
    private int view;
    @Column(length = 255)
    private String image;
    @Column(nullable = false)
    private boolean save;
    @Column(length = 8)
    private int category;
    @Column(columnDefinition = "Text")
    private String siren;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_key")
    private MemberEntity member;


    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("comment_id asc")
    private List<CommentEntity> commentList;

    public void update(String title, String content,String image)
    {
        this.title=title;
        this.content=content;
        this.image=image;
    }






}

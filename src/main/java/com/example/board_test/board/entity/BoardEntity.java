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
public class BoardEntity extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nId;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(length = 500,nullable = false)
    private String title;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int likeCnt;

    @Column(columnDefinition = "integer default 0", nullable = false)
    private int dislikeCnt;

    @Column(columnDefinition = "integer default 0" ,nullable = false)
    private int viewCnt;

    @Column(length = 255)
    private String image;

    @Column(nullable = false)
    private boolean save;

    @Column(length = 8)
    private int category;

    @Column(columnDefinition = "TEXT")
    private String siren;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userKey", nullable = false)
    private MemberEntity member;


    @OneToMany(mappedBy = "board",fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("comm_id asc")
    private List<CommentEntity> commentList;

    public void update(String title, String content,String image)
    {
        this.title=title;
        this.content=content;
        this.image=image;
    }


}

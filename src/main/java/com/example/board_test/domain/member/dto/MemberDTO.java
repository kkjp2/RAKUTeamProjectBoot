package com.example.board_test.domain.member.dto;


import com.example.board_test.domain.member.entity.enums.MemberRole;
import lombok.*;


@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {
    private Long userKey;
    private String id;
    private String pwd;
    private String nick;
    private int address;
    private MemberRole role;
    private int alarm;
    private String bookmark;
    private String recent;
}

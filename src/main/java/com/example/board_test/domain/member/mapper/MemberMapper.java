package com.example.board_test.domain.member.mapper;

import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;


public class MemberMapper {
    


    public static MemberEntity createEntity(MemberDTO memberDTO){


        return   MemberEntity.builder()
                .id(memberDTO.getId())
                .pwd(memberDTO.getPwd())
                .nick(memberDTO.getNick())
                .address(memberDTO.getAddress())
                .role(memberDTO.getRole())
                .alarm(memberDTO.getAlarm())
                .bookmark(memberDTO.getBookmark())
                .recent(memberDTO.getRecent())
                .build();
    }

    public static MemberDTO createDTO(MemberEntity memberEntity){
        return MemberDTO.builder()
                .id(memberEntity.getId())
                .pwd(memberEntity.getPwd())
                .nick(memberEntity.getNick())
                .address(memberEntity.getAddress())
                .role(memberEntity.getRole())
                .alarm(memberEntity.getAlarm())
                .bookmark(memberEntity.getBookmark())
                .recent(memberEntity.getRecent())
                .build();
    }

}

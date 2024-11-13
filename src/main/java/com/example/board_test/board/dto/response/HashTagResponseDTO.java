package com.example.board_test.board.dto.response;


import com.example.board_test.board.entity.HashTagEntity;
import lombok.Getter;

@Getter
public class HashTagResponseDTO {
    private final Long hash_id;
    private final String keyword;



    public HashTagResponseDTO(HashTagEntity hashTag)
    {
        this.hash_id=hashTag.getHash_id();
        this.keyword=hashTag.getKeyword();

    }

}

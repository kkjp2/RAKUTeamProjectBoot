package com.example.board_test.domain.board.dto.response;


import com.example.board_test.domain.board.entity.HashTagEntity;
import lombok.Getter;

@Getter
public class HashTagResponseDTO {
    private final Long hash_id;
    private final String keyword;



    public HashTagResponseDTO(HashTagEntity hashTag)
    {
        this.hash_id=hashTag.getHashId();
        this.keyword=hashTag.getKeyword();

    }

}

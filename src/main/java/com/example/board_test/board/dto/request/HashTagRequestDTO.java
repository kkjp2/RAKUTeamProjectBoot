package com.example.board_test.board.dto.request;


import com.example.board_test.board.entity.HashTagEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HashTagRequestDTO {
    private Long hash_id;
    private String keyword;
    private int count;

    public HashTagEntity toEntity()
    {
        HashTagEntity hashTags=
                HashTagEntity.builder().
                        hash_id(hash_id).
                        keyword(keyword).
                        count(0).
                        build();
        return hashTags;
    }

}

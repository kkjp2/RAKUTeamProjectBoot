package rakuproject.raku.domain.board.dto.response;



import lombok.Getter;
import rakuproject.raku.domain.board.entity.HashTagEntity;

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

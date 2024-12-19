package rakuproject.raku.domain.board.dto;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestSampleDTO {
    private Long id;
    private String content;
    private String title;
}

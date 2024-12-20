package rakuproject.raku.domain.announcement.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Getter
@Setter
public class AnnDTO {

    private Long annkey;
    private Long userkey;
    private String title;
    private MultipartFile img;
    private String content;
    private String borndate;

}

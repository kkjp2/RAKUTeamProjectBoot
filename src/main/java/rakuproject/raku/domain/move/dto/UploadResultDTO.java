package rakuproject.raku.domain.move.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UploadResultDTO {
    private String fileName;    // 파일 이름
    private String uuid;        // 유유
    private String folderPath;  // 저장되는 파일 경로.
}

package rakuproject.raku.domain.move.mapper;

import org.springframework.stereotype.Component;
import rakuproject.raku.domain.move.dto.UploadResultDTO;
import rakuproject.raku.domain.move.entity.UploadFileEntity;

@Component
public class FileMapper {

    public UploadResultDTO toDTO(UploadFileEntity fileEntity) {
        UploadResultDTO dto = new UploadResultDTO();
        dto.setFileName(fileEntity.getFileName());
        dto.setUuid(fileEntity.getUuid());
        dto.setFolderPath(fileEntity.getFolderPath());
        return dto;
    }
}

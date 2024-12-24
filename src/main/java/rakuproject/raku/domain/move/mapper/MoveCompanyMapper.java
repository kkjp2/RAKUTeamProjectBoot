package rakuproject.raku.domain.move.mapper;

import org.springframework.stereotype.Component;
import rakuproject.raku.domain.move.dto.MoveCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.entity.UploadFileEntity;

@Component
public class MoveCompanyMapper {
    public MoveCompanyEntity toEntity(MoveCompanyDTO dto, UploadFileEntity fileEntity) {
        MoveCompanyEntity entity = new MoveCompanyEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setCeo(dto.getCeo());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPostalCode(dto.getPostalCode());
        entity.setAddress(dto.getAddress());
        entity.setDetailedAddress(dto.getDetailedAddress());
        entity.setBusinessNumber(dto.getBusinessNumber());
        entity.setService(dto.getService());
        entity.setMoveCity(dto.getMoveCity());
        entity.setIntroduction(dto.getIntroduction());
        entity.setFileId(fileEntity);
        return entity;
    }

    public MoveCompanyDTO toDTO(MoveCompanyEntity entity) {
        MoveCompanyDTO dto = new MoveCompanyDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCeo(entity.getCeo());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setPostalCode(entity.getPostalCode());
        dto.setAddress(entity.getAddress());
        dto.setDetailedAddress(entity.getDetailedAddress());
        dto.setBusinessNumber(entity.getBusinessNumber());
        dto.setService(entity.getService());
        dto.setMoveCity(entity.getMoveCity());
        dto.setIntroduction(entity.getIntroduction());
        if (entity.getFileId() != null) {
            dto.setFileId(entity.getFileId().getId());
            dto.setImgUrl("http://localhost:8080/uploads/" + entity.getFileId().getFolderPath() + "/" +
                    entity.getFileId().getUuid() + "_" + entity.getFileId().getFileName());
        } else {
            dto.setImgUrl("/Users/sueunjo/Documents/S/img/2.png");
        }
        return dto;
    }
}


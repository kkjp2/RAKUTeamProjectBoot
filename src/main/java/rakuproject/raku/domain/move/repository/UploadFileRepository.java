package rakuproject.raku.domain.move.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.entity.UploadFileEntity;

import java.util.List;
import java.util.Optional;

public interface UploadFileRepository extends JpaRepository<UploadFileEntity, Long> {
    // 根据文件名查询
    Optional<UploadFileEntity> findByUuid(String uuid);
    List<UploadFileEntity> findByCompanyId(MoveCompanyEntity companyId);
}

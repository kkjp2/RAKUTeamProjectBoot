package rakuproject.raku.domain.move.service;

import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.move.dto.UploadResultDTO;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.entity.UploadFileEntity;
import rakuproject.raku.domain.move.mapper.FileMapper;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.UploadFileRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileService {

    @Autowired
    private MoveCompanyRepository moveCompanyRepository;

    @Autowired
    private UploadFileRepository uploadFileRepository;

    @Value("${file.upload.path:/Users/sueunjo/Documents/upload}")
    private String uploadPath;

    @Autowired
    private FileMapper fileMapper;

    public UploadFileEntity uploadLogo(MultipartFile uploadFile, int companyId) {
        Optional<MoveCompanyEntity> companyOpt = moveCompanyRepository.findById(companyId);

        if (!companyOpt.isPresent()) {
            return null;
        }
        MoveCompanyEntity company = companyOpt.get();

        if (!uploadFile.getContentType().startsWith("image")) {
            return null;
        }

        String fileName = uploadFile.getOriginalFilename();
        String folderPath = makeFolder();
        String uuid = UUID.randomUUID().toString();
        String originalSaveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
        String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

        try {
            // 保存原始文件
            Path originalPath = Paths.get(originalSaveName);
            uploadFile.transferTo(originalPath);

            // 生成缩略图
            File thumbnailFile = new File(thumbnailSaveName);
            Thumbnailator.createThumbnail(originalPath.toFile(), thumbnailFile, 200, 200);

            // 创建文件记录
            UploadFileEntity uploadFileEntity = new UploadFileEntity();
            uploadFileEntity.setFileName(fileName);
            uploadFileEntity.setUuid(uuid);
            uploadFileEntity.setFolderPath(folderPath);
            uploadFileEntity.setUploadDate(LocalDate.now());
            uploadFileEntity.setCompanyId(company);

            UploadFileEntity savedFile = uploadFileRepository.save(uploadFileEntity);

            // 设置公司图标字段
            company.setFileId(savedFile);
            moveCompanyRepository.save(company);

            return savedFile;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private String makeFolder() {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("/", File.separator);
        File uploadPathFolder = new File(uploadPath, folderPath);

        if (!uploadPathFolder.exists()) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }
}


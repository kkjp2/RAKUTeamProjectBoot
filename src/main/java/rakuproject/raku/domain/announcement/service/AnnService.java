package rakuproject.raku.domain.announcement.service;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.announcement.dto.AnnDTO;
import rakuproject.raku.domain.announcement.entity.AnnEntity;
import rakuproject.raku.domain.announcement.dto.response.AnnViewResponse;
import rakuproject.raku.domain.announcement.repository.AnnRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnService {

    private final AnnRepository annRepository;

    @Autowired
    private EntityManager entityManager;

    @Value("${file.upload.dir}") // 파일 업로드 디렉토리
    private String uploadDir;

    private String makeFolder() {
        String str = LocalDate.now().format(
                DateTimeFormatter.ofPattern("yyyy/MM/dd")
        );
        // str = 2024/10/18
        String folderPath = str.replace("/", File.separator);

        File uploadPathFolder = new File(uploadDir,folderPath);

        if(!uploadPathFolder.exists()){
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    // 파일 저장 후 경로 반환
    public String saveFile(MultipartFile file) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        // 고유 파일명 생성
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String folderPath = makeFolder();
        Path filePath = Paths.get(uploadDir,folderPath, uniqueFileName);

        // 파일 저장
        file.transferTo(filePath.toFile());

        return filePath.toString(); // 경로 반환
    }

    public Long createAnn(AnnDTO annDTO) throws IOException {
        // 파일 경로만 저장
        String imgPath = saveFile(annDTO.getImg());

        AnnEntity annEntity = AnnEntity.builder()
                .borndate(annDTO.getBorndate())
                .img(imgPath)
                .content(annDTO.getContent())
                .title(annDTO.getTitle())
                .userkey(entityManager.getReference(MemberEntity.class, annDTO.getUserkey()))
                .annkey(annDTO.getAnnkey())
                .build();;

        // 회원 정보 저장
       annRepository.save(annEntity);

        return annEntity.getAnnkey();
    }


    public AnnViewResponse viewAnn(){

        List<AnnEntity> annEntity = annRepository.findAll();


        return new AnnViewResponse(annEntity);

    }
}

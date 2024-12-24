package rakuproject.raku.domain.company.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.company.dto.CompanyDTO;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.company.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl {

    private final CompanyRepository companyRepository;

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

    public Long registerCompany(CompanyDTO companyDTO) throws IOException {
        // 파일 경로만 저장
        String imgPath = saveFile(companyDTO.getImg());
        String mapPath = saveFile(companyDTO.getMap());

        CompanyEntity companyEntity = CompanyEntity.builder()
                .realtyCompanyKey(companyDTO.getRealtyCompanyKey())
                .license(companyDTO.getLicense())
                .name(companyDTO.getName())
                .img(imgPath)
                .number(companyDTO.getNumber())
                .zipcode(companyDTO.getZipcode())
                .address(companyDTO.getAddress())
                .openhour(companyDTO.getOpenhour())
                .holiday(companyDTO.getHoliday())
                .hompage(companyDTO.getHompage())
                .content(companyDTO.getContent())
                .fax(companyDTO.getFax())
                .map(mapPath)
                .build();;

        // 회원 정보 저장
        companyRepository.save(companyEntity);

        return companyEntity.getRealtyCompanyKey();
    }

}

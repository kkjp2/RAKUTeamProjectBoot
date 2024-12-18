package rakuproject.raku.domain.member.service;


import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.company.entity.CompanyEntity;
import rakuproject.raku.domain.company.repository.CompanyRepository;
import rakuproject.raku.domain.member.dto.CompanyMemberDTO;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.member.repository.CompanyMemberRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyMemberService {

    private final CompanyMemberRepository companyMemberRepository;
    private final PasswordEncoder passwordEncoder;

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


    public Long registerMember(CompanyMemberDTO companyMemberDTO) throws IOException {

        String imgPath = saveFile(companyMemberDTO.getImg());

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(companyMemberDTO.getPwd());
        companyMemberDTO.setPwd(encodedPassword);

        CompanyMemberEntity companyEntity = CompanyMemberEntity.builder()
                .realtyMemberKey(companyMemberDTO.getRealtyMemberKey())
                .realtyCompanyKey(entityManager.getReference(CompanyEntity.class, companyMemberDTO.getRealtyCompanyKey()))
                .name(companyMemberDTO.getName())
                .content(companyMemberDTO.getContent())
                .sex(companyMemberDTO.getSex())
                .age(companyMemberDTO.getAge())
                .experience(companyMemberDTO.getExperience())
                .id(companyMemberDTO.getId())
                .pwd(companyMemberDTO.getPwd())
                .img(imgPath)
                .build();;

        // 회원 정보 저장
        companyMemberRepository.save(companyEntity);

        return companyEntity.getRealtyMemberKey();
    }
}

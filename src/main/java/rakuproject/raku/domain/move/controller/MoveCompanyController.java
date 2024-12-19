package rakuproject.raku.domain.move.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.move.dto.MoveCompanyDTO;
import rakuproject.raku.domain.move.dto.UploadResultDTO;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.entity.UploadFileEntity;
import rakuproject.raku.domain.move.mapper.MoveCompanyMapper;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.domain.move.repository.UploadFileRepository;
import rakuproject.raku.domain.move.service.FileService;
import rakuproject.raku.domain.move.service.MoveCompanyService;
import rakuproject.raku.domain.mypage.service.UserServiceImpl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@Slf4j
@RequestMapping("/move/company")
public class MoveCompanyController {

    @Autowired
    private final MoveCompanyService moveCompanyService;
    @Autowired
    private final MoveCompanyRepository moveCompanyRepository;
    @Autowired
    private final UploadFileRepository uploadFileRepository;

    private final String uploadPath;
    @Autowired
    private MoveCompanyMapper mapper;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private FileService fileService;

    public MoveCompanyController(MoveCompanyService moveCompanyService,
                                 MoveCompanyRepository moveCompanyRepository,
                                 UploadFileRepository uploadFileRepository,
                                 @Value("${file.upload.dir}")  String uploadPath) {
        this.moveCompanyService = moveCompanyService;
        this.moveCompanyRepository = moveCompanyRepository;
        this.uploadFileRepository = uploadFileRepository;
        this.uploadPath = uploadPath;
    }

    // 회사 정보 업로드
//    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Integer> createCompany(@RequestHeader("Authorization") String authorizationHeader ,@RequestBody MoveCompanyDTO companyDTO) {
//        String token = authorizationHeader.substring(7);
//        Long userKey = userService.getUserKeyFromToken(token);
//        companyDTO.setUserKey(userKey);
//        // 정보 저장
//        MoveCompanyDTO savedCompany = moveCompanyService.createCompany(companyDTO);
//        return new ResponseEntity<>(savedCompany.getId(), HttpStatus.CREATED);
//    }
    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> createCompany(@RequestHeader("Authorization") String authorizationHeader,
                                                @ModelAttribute MoveCompanyDTO companyDTO,
                                                @RequestParam("uploadFile") MultipartFile uploadFile) {
        // Authorization Header에서 token 추출
        String token = authorizationHeader.substring(7);
        Long userKey = userService.getUserKeyFromToken(token);  // userKey 추출
        companyDTO.setUserKey(userKey);  // companyDTO에 userKey 설정

        // 기업 정보 저장
        MoveCompanyDTO companyId = moveCompanyService.createCompany(companyDTO);

        // 로고 업로드 처리 (새로운 API 호출)
        if (uploadFile != null && !uploadFile.isEmpty()) {
            // 로고 업로드 API 호출
            UploadFileEntity logoFile = fileService.uploadLogo(uploadFile, companyId.getId());

            if (logoFile == null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Logo upload failed");
            }

            // 로고 URL이나 다른 정보 저장
            companyDTO.setImgUrl(logoFile.getUrl());
        }

        return new ResponseEntity<>("Company registered with ID: " + companyId, HttpStatus.CREATED);
    }


    // 회사리스트 출력.
//    @GetMapping("/companies")
//    public ResponseEntity<List<MoveCompanyDTO>> getAllCompanies() {
//        List<MoveCompanyDTO> companies = moveCompanyService.getAllCompanies();
//        return new ResponseEntity<>(companies, HttpStatus.OK);
//    }

    @GetMapping("/companies")
    public List<MoveCompanyEntity> getAllCompanies() {
        List<MoveCompanyEntity> companies = moveCompanyRepository.findAll();
        return companies;
    }

    // 지역/페이지 별로 회사 보이게.
    @GetMapping("/companies/cityFind")
    public ResponseEntity<Page<MoveCompanyDTO>> getCompaniesByCity(
            @RequestParam("moveCity") String city,
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        Page<MoveCompanyEntity> companies = moveCompanyService.getCompaniesByCity(city, page, size);

        //DTO
        List<MoveCompanyDTO> companyDTOs = companies.stream()
                .map(mapper::toDTO) // 使用 mapper 的 toDTO 方法
                .collect(Collectors.toList());

        // 페이징 DTO 응답
        Page<MoveCompanyDTO> companyDTOPage = new PageImpl<>(companyDTOs, PageRequest.of(page, size), companies.getTotalElements());

        return new ResponseEntity<>(companyDTOPage, HttpStatus.OK);
    }

    // 아이디로 회사 정보 얻기.
    @GetMapping("/companies/{id}")
    public ResponseEntity<MoveCompanyDTO> getCompanyById(@PathVariable("id") Integer id) {
        MoveCompanyEntity company = moveCompanyService.getCompanyById(id);
        MoveCompanyDTO dto = mapper.toDTO(company);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping("/getImage")
    public ResponseEntity<byte[]> getImage(@RequestParam("uuid") String uuid) {
        try {
            Optional<UploadFileEntity> optionalFile = uploadFileRepository.findByUuid(uuid);

            if (optionalFile.isEmpty()) {
                log.warn("데이터베이스에서 파일을 찾을 수 없습니다 uuid : {}", uuid);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            UploadFileEntity fileEntity = optionalFile.get();

            // 파일 경로
            String filePath = uploadPath + File.separator + fileEntity.getFolderPath()
                    + File.separator + fileEntity.getUuid() + "_" + fileEntity.getFileName();
            File file = new File(filePath);

            // 파일이 존재하는지 검사
            if (!file.exists()) {
                log.warn("요청된 파일이 없습니다: {}", filePath);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", Files.probeContentType(file.toPath()));

            return new ResponseEntity<>(Files.readAllBytes(file.toPath()), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("이미지 검색 중 오류 발생: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 회사정보 삭제.
    @DeleteMapping("/companies/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable("id") Integer id) {
        try {
            moveCompanyService.deleteCompany(id);
            return new ResponseEntity<>("削除が成功しました。", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("削除する会社が見つかりません。", HttpStatus.NOT_FOUND);
        }
    }

}

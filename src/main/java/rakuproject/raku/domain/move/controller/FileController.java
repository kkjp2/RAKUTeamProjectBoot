package rakuproject.raku.domain.move.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rakuproject.raku.domain.move.dto.UploadResultDTO;
import rakuproject.raku.domain.move.entity.UploadFileEntity;
import rakuproject.raku.domain.move.mapper.FileMapper;
import rakuproject.raku.domain.move.repository.UploadFileRepository;
import rakuproject.raku.domain.move.service.FileService;

@RestController
@RequestMapping("/move/file")
@RequiredArgsConstructor
@Slf4j
public class FileController {

    @Autowired
    private final FileService fileService;

    @Autowired
    private final UploadFileRepository uploadFileRepository;

    @Autowired
    private final FileMapper fileMapper;


    // Logo
    @PostMapping(value = "/uploadLogo", consumes = {"multipart/form-data"})
    public ResponseEntity<UploadFileEntity> uploadCompanyLogo(
            @RequestPart("uploadFile") MultipartFile uploadFile,
            @RequestParam("companyId") int companyId) {

        // 파일 저장 및 회사 연결
        UploadFileEntity savedFile = fileService.uploadLogo(uploadFile, companyId);

        if (savedFile == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
        return new ResponseEntity<>(savedFile, HttpStatus.CREATED);
    }

    @GetMapping("/files/{uuid}")
    public ResponseEntity<UploadResultDTO> getFileByUuid(@PathVariable String uuid) {
        UploadFileEntity fileEntity = uploadFileRepository.findByUuid(uuid)
                .orElseThrow(() -> new RuntimeException("파일을 찾을 수 없습니다"));

        UploadResultDTO fileDTO = fileMapper.toDTO(fileEntity);
        return new ResponseEntity<>(fileDTO, HttpStatus.OK);
    }

}


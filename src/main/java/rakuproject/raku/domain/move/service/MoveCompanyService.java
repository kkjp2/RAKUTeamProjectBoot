package rakuproject.raku.domain.move.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.move.dto.MoveCompanyDTO;
import rakuproject.raku.domain.move.entity.MoveCompanyEntity;
import rakuproject.raku.domain.move.exception.ResourceNotFoundException;
import rakuproject.raku.domain.move.mapper.MoveCompanyMapper;
import rakuproject.raku.domain.move.repository.MoveCompanyRepository;
import rakuproject.raku.global.security.MemberSecurity;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MoveCompanyService {

    @Autowired
    private final MoveCompanyRepository moveCompanyRepository;

    @Autowired
    private final MoveCompanyMapper mapper;

    public MoveCompanyDTO createCompany(MoveCompanyDTO companyDTO) {
        MoveCompanyEntity entity = mapper.toEntity(companyDTO, null);
        MoveCompanyEntity savedEntity = moveCompanyRepository.save(entity);
        return mapper.toDTO(savedEntity);
    }

    public List<MoveCompanyDTO> getAllCompanies() {
        List<MoveCompanyEntity> entities = moveCompanyRepository.findAll();
        return entities.stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList());
    }

    //지역/페이지별로 회사 정보 얻기
    public Page<MoveCompanyEntity> getCompaniesByCity(String city, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return moveCompanyRepository.findByMoveCityContaining(city, pageRequest);
    }

    //아이디로 회사 정보 찾기/얻기
    public MoveCompanyEntity getCompanyById(Integer id) {
        return moveCompanyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("会社が見つかりませんでした。ID: " + id));
    }

    //회사/기업 삭제.
    public void deleteCompany(Integer id) {
        if (!moveCompanyRepository.existsById(id)) {
            throw new ResourceNotFoundException("会社が見つかりませんでした。ID: " + id);
        }
        moveCompanyRepository.deleteById(id);
    }

}

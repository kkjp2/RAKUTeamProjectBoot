package rakuproject.raku.domain.member.mapper;

import rakuproject.raku.domain.member.dto.CompanyMemberDTO;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.CompanyMemberEntity;
import rakuproject.raku.domain.member.entity.MemberEntity;

public class CompanyMemberMapper {
    public static CompanyMemberDTO createDTO(CompanyMemberEntity companyMemberEntity){
        return CompanyMemberDTO.builder()
                .realtyMemberKey(companyMemberEntity.getRealtyMemberKey())
                .id(companyMemberEntity.getId())
                .pwd(companyMemberEntity.getPwd())
                .build();
    }
}

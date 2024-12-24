//package rakuproject.raku.domain.company.mapper;
//
//import jakarta.persistence.Column;
//import rakuproject.raku.domain.company.dto.CompanyDTO;
//import rakuproject.raku.domain.company.entity.CompanyEntity;
//
//public class CompanyMapper {
//
//    public static CompanyEntity createEntity(CompanyDTO companyDTO){
//
//
//
//        return   CompanyEntity.builder()
//                .realtyCompanyKey(companyDTO.getRealtyCompanyKey())
//                .license(companyDTO.getLicense())
//                .name(companyDTO.getName())
//                .img(companyDTO.getImg())
//                .number(companyDTO.getNumber())
//                .zipcode(companyDTO.getZipcode())
//                .address(companyDTO.getAddress())
//                .openhour(companyDTO.getOpenhour())
//                .holiday(companyDTO.getHoliday())
//                .hompage(companyDTO.getHompage())
//                .content(companyDTO.getContent())
//                .fax(companyDTO.getFax())
//                .map(companyDTO.getMap())
//                .build();
//    }
//
//    public static CompanyDTO createDTO(CompanyEntity companyEntity){
//        return CompanyDTO.builder()
//                .realtyCompanyKey(companyEntity.getRealtyCompanyKey())
//                .license(companyEntity.getLicense())
//                .name(companyEntity.getName())
//                .img(companyEntity.getImg())
//                .number(companyEntity.getNumber())
//                .zipcode(companyEntity.getZipcode())
//                .address(companyEntity.getAddress())
//                .openhour(companyEntity.getOpenhour())
//                .holiday(companyEntity.getHoliday())
//                .hompage(companyEntity.getHompage())
//                .content(companyEntity.getContent())
//                .fax(companyEntity.getFax())
//                .map(companyEntity.getMap())
//                .build();
//    }
//
//}

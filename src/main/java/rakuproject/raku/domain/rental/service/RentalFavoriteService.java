package rakuproject.raku.domain.rental.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.rental.dto.request.RentalFavoriteRequestDTO;
import rakuproject.raku.domain.rental.entity.RentalBoardEntity;
import rakuproject.raku.domain.rental.entity.RentalFavoriteEntity;
import rakuproject.raku.domain.rental.repository.RentalBoardRepository;
import rakuproject.raku.domain.rental.repository.RentalFavoriteRepository;
import rakuproject.raku.global.security.MemberSecurity;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalFavoriteService {
    @Autowired
    private final RentalBoardRepository rentalBoardRepository;
    @Autowired
    private final RentalFavoriteRepository rentalFavoriteRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void addFavorite(RentalFavoriteRequestDTO requestDTO)
    {
        MemberEntity memberEntity= getMemberEntity(requestDTO.getMemberId());
        if(requestDTO.getRentalBoardId() != null)
        {
            handleRentalFavorite(requestDTO.getRentalBoardId(),memberEntity);
        }else{
            throw new IllegalArgumentException("유효하지 않습니다.");
        }
    }

    private MemberEntity getMemberEntity(Long memberId)
    {
        return memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("회원 정보를 찾을 수 없습니다."));

    }
    private void handleRentalFavorite(Long rentalID,MemberEntity member)
    {
        RentalBoardEntity rentalBoard=rentalBoardRepository.findById(rentalID)
                .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 렌탈글입니다."+rentalID));
        rentalFavoriteRepository.findByMemberAndRentalBoard(member,rentalBoard)
                .ifPresent(rentalFavorite->{
                    throw new IllegalArgumentException("이미 추가된 렌탈글입니다.");
                });
        RentalFavoriteEntity rentalFavorite=RentalFavoriteEntity.builder()
                .member(member)
                .rentalBoard(rentalBoard)
                .build();
        rentalFavoriteRepository.save(rentalFavorite);
    }
    @Transactional
    public void removeRentalFavorite(Long rentalFavId)
    {
        rentalFavoriteRepository.deleteById(rentalFavId);
    }




}

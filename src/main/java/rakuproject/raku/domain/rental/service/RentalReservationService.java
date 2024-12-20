package rakuproject.raku.domain.rental.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.domain.rental.dto.response.RentalReservationResponseDTO;
import rakuproject.raku.domain.rental.entity.RentalBoardEntity;
import rakuproject.raku.domain.rental.entity.RentalReservationEntity;
import rakuproject.raku.domain.rental.repository.RentalBoardRepository;
import rakuproject.raku.domain.rental.repository.RentalReservationRepository;
import rakuproject.raku.global.security.MemberSecurity;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
@Slf4j
public class RentalReservationService {
    @Autowired
    private final RentalReservationRepository rentalReservationRepository;
    @Autowired
    private final RentalBoardRepository rentalBoardRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public RentalReservationResponseDTO register(Long rbId)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        RentalBoardEntity rentalBoard=rentalBoardRepository.findById(rbId)
                .orElseThrow(()->new IllegalArgumentException("해당 렌탈 게시글이 존재하지 않습니다."));

        RentalReservationEntity reservation=RentalReservationEntity
                .builder().
                resDate(LocalDateTime.now()).
                startDate(rentalBoard.getStartDate()).
                endDate(rentalBoard.getEndDate()).
                resPrice(rentalBoard.getRentalPrice()).
                resAddress(rentalBoard.getRentalAddress()).
                resHomeType(rentalBoard.getRentalHomeType()).
                rentalBoard(rentalBoard).
                member(memberEntity).
                build();
        rentalReservationRepository.save(reservation);
        return new RentalReservationResponseDTO(reservation);
    }




}

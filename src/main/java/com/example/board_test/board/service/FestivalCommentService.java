package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.FestivalCommentDTO;
import com.example.board_test.board.dto.request.FestivalCommentRequestDTO;
import com.example.board_test.board.dto.response.FestivalCommentResponseDTO;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.entity.FestivalCommentEntity;
import com.example.board_test.board.repository.FestivalBoardRepository;
import com.example.board_test.board.repository.FestivalCommentRepository;
import com.example.board_test.domain.member.dto.MemberDTO;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.mapper.MemberMapper;
import com.example.board_test.domain.member.repository.MemberRepository;
import com.example.board_test.global.security.MemberSecurity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Slf4j
public class FestivalCommentService {

    @Autowired
    private final FestivalCommentRepository festivalCommentRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;
    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void register(FestivalCommentRequestDTO festivalCommentDTO, Long id)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        FestivalCommentEntity festivalCommentEntity=FestivalCommentEntity.builder()
                .comment(festivalCommentDTO.getComment())
                .image(festivalCommentDTO.getImage())
                .festivalBoard(festivalBoard)
                .member(memberEntity)
                .build();
        festivalCommentRepository.save(festivalCommentEntity);
    }
    @Transactional
    public void update(FestivalCommentRequestDTO festivalCommentRequestDTO)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity=MemberMapper.createEntity(memberDTO);
        FestivalCommentEntity festivalComment=festivalCommentRepository
                .findById(festivalCommentRequestDTO.getFc_id())
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        festivalComment.update(festivalCommentRequestDTO.getComment(),
                festivalCommentRequestDTO.getImage());
    }

    @Transactional(readOnly = true)
    public FestivalCommentResponseDTO findById(Long fc_id)
    {
        FestivalCommentEntity festivalComments=festivalCommentRepository.findById(fc_id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        return new FestivalCommentResponseDTO(festivalComments);
    }
    public List<FestivalCommentDTO> getFestivalCommentByFestivalId(Long fid)
    {
        List<FestivalCommentEntity> festivalComments=festivalCommentRepository.findByFestivalBoard_fbId(fid);
        return festivalComments.stream()
                .map(festivalComment -> new FestivalCommentDTO(
                        festivalComment.getFcId(),festivalComment.getComment()
                )).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long fid)
    {
        festivalCommentRepository.deleteById(fid);
    }




//    @Transactional(readOnly = true)
//    public List<FestivalCommentResponseDTO> findById(Long fc_id)
//    {
//        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(fc_id)
//                .orElseThrow(()-> new IllegalArgumentException("??"));
//        List<FestivalCommentEntity> festivalComments=festivalBoard.getFestivalComments();
//        return festivalComments.stream().map(FestivalCommentResponseDTO::new).collect(Collectors.toList());
//    }
//
//    @Transactional
//    public Long updateFestivalComment(Long fc_id, Long fb_id, FestivalCommentRequestDTO festivalCommentRequestDTO)
//    {
//        return null;
////        FestivalCommentEntity festivalComment=festivalCommentRepository.findByPostsIdAndId(fb_id,fc_id)
////                .orElseThrow(()-> new IllegalArgumentException("안대요~"));
////        festivalComment.update(festivalCommentRequestDTO.getComment(),festivalCommentRequestDTO.getImage());
////        return festivalComment.getFc_id();
//    }
//
//    @Transactional
//    public void deleteFestivalComment(Long fc_id)
//    {
//        festivalCommentRepository.deleteById(fc_id);
//    }



}

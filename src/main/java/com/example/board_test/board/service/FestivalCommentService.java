package com.example.board_test.board.service;


import com.example.board_test.board.dto.request.FestivalCommentRequestDTO;
import com.example.board_test.board.dto.response.FestivalCommentResponseDTO;
import com.example.board_test.board.entity.FestivalBoardEntity;
import com.example.board_test.board.entity.FestivalCommentEntity;
import com.example.board_test.board.repository.FestivalBoardRepository;
import com.example.board_test.board.repository.FestivalCommentRepository;
import com.example.board_test.domain.member.entity.MemberEntity;
import com.example.board_test.domain.member.repository.MemberRepository;
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


    @Transactional
    public Long createFestivalComment(FestivalCommentRequestDTO festivalCommentRequestDTO, String nickname, Long id)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()-> new IllegalArgumentException("안돼안돼안돼"));
        festivalCommentRequestDTO.setMember(member);
        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("흐음.."));
        festivalCommentRequestDTO.setFestivalBoard(festivalBoard);
        FestivalCommentEntity festivalComment=festivalCommentRequestDTO.toEntity();
        festivalCommentRepository.save(festivalComment);
        return festivalComment.getFc_id();
    }

    @Transactional(readOnly = true)
    public List<FestivalCommentResponseDTO> findById(Long fc_id)
    {
        FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(fc_id)
                .orElseThrow(()-> new IllegalArgumentException("??"));
        List<FestivalCommentEntity> festivalComments=festivalBoard.getFestivalComments();
        return festivalComments.stream().map(FestivalCommentResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public Long updateFestivalComment(Long fc_id, Long fb_id, FestivalCommentRequestDTO festivalCommentRequestDTO)
    {
        return null;
//        FestivalCommentEntity festivalComment=festivalCommentRepository.findByPostsIdAndId(fb_id,fc_id)
//                .orElseThrow(()-> new IllegalArgumentException("안대요~"));
//        festivalComment.update(festivalCommentRequestDTO.getComment(),festivalCommentRequestDTO.getImage());
//        return festivalComment.getFc_id();
    }

    @Transactional
    public void deleteFestivalComment(Long fc_id)
    {
        festivalCommentRepository.deleteById(fc_id);
    }



}

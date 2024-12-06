package com.example.board_test.board.service;

import com.example.board_test.board.dto.request.CommentDTO;
import com.example.board_test.board.dto.request.CommentRequestDTO;
import com.example.board_test.board.dto.response.CommentResponseDTO;
import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.CommentEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.board.repository.CommentRepository;
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
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final MemberSecurity memberSecurity;


    @Transactional
    public void register(CommentRequestDTO commentRequestDTO, Long id)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);

        BoardEntity board=boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("not board"));

        CommentEntity commentEntity= CommentEntity.builder().
                commentText(commentRequestDTO.getCommentText()).
                image(commentRequestDTO.getImage()).
                board(board).
                member(memberEntity).
                build();
        commentRepository.save(commentEntity);
    }

    @Transactional
    public void update(CommentRequestDTO commentRequestDTO)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity=MemberMapper.createEntity(memberDTO);
        CommentEntity comment=commentRepository.findById(commentRequestDTO.getComment_id())
                .orElseThrow(()->new IllegalArgumentException("not found"));
        comment.update(commentRequestDTO.getCommentText(),commentRequestDTO.getImage());

    }

    //댓글 목록
    @Transactional(readOnly = true)
    public CommentResponseDTO findById(Long c_id)
    {
        CommentEntity comments=commentRepository.findById(c_id)
                .orElseThrow(()-> new IllegalArgumentException("not found"));
        return new CommentResponseDTO(comments);
    }


    public List<CommentDTO> getCommentByBoardId(Long cid)
    {
        List<CommentEntity> comments=commentRepository.findByBoard_nId(cid);
        return comments.stream()
                .map(comment -> new CommentDTO(
                        comment.getCommId(),comment.getCommentText()
                        ))
                .collect(Collectors.toList());
    }



    @Transactional
    public void deleteComment(Long c_id)
    {
        commentRepository.deleteById(c_id);
    }


    
}

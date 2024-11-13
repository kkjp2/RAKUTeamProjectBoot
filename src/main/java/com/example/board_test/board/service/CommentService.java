package com.example.board_test.board.service;

import com.example.board_test.board.dto.request.CommentRequestDTO;
import com.example.board_test.board.dto.response.CommentResponseDTO;
import com.example.board_test.board.entity.BoardEntity;
import com.example.board_test.board.entity.CommentEntity;
import com.example.board_test.board.repository.BoardRepository;
import com.example.board_test.board.repository.CommentRepository;
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
public class CommentService {
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final BoardRepository boardRepository;

    
    //댓글 작성
    @Transactional
    public Long createComment(CommentRequestDTO commentRequestDTO, String nickname, Long id)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()-> new IllegalArgumentException("댓글 실패"));
        commentRequestDTO.setMember(member);
        BoardEntity board=boardRepository.findById(id).orElseThrow(
                ()->new IllegalArgumentException("not found board"));
        commentRequestDTO.setBoard(board);
        CommentEntity comment=commentRequestDTO.toEntity();
        commentRepository.save(comment);
        return comment.getComment_id();
    }

    //댓글 목록
    @Transactional(readOnly = true)
    public List<CommentResponseDTO> findById(Long c_id)
    {
        BoardEntity board=boardRepository.findById(c_id)
                .orElseThrow(()->new IllegalArgumentException("この投稿が見つからないか、削除されました"));
        List<CommentEntity> comments=board.getCommentList();
        return comments.stream().map(CommentResponseDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public  Long updateComment(Long c_id,Long board_id, CommentRequestDTO commentRequestDTO)
    {
        CommentEntity comment=commentRepository.findByPostsIdAndId(board_id,c_id)
                .orElseThrow(()-> new IllegalArgumentException("この投稿が見つからないか、削除されました"));
        comment.update(commentRequestDTO.getCommentText(),commentRequestDTO.getImage());
        return comment.getComment_id();
    }

    @Transactional
    public void deleteComment(Long c_id)
    {
        commentRepository.deleteById(c_id);
    }


    
}

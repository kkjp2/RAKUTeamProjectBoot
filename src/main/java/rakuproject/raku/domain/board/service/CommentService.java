package rakuproject.raku.domain.board.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.board.dto.request.CommentDTO;
import rakuproject.raku.domain.board.dto.request.CommentRequestDTO;
import rakuproject.raku.domain.board.dto.response.CommentResponseDTO;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.entity.CommentEntity;
import rakuproject.raku.domain.board.repository.BoardRepository;
import rakuproject.raku.domain.board.repository.CommentRepository;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.global.security.MemberSecurity;

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

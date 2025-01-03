package rakuproject.raku.domain.board.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.board.dto.request.BoardRegisterRequest;
import rakuproject.raku.domain.board.dto.request.LikeRequestDTO;
import rakuproject.raku.domain.board.dto.response.BoardResponseDTO;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.repository.BoardRepository;
import rakuproject.raku.domain.member.dto.MemberDTO;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.mapper.MemberMapper;
import rakuproject.raku.domain.member.repository.MemberRepository;
import rakuproject.raku.global.security.MemberSecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    @Autowired
    private final BoardRepository boardRepository;

    @Autowired
    private final MemberRepository memberRepository;

    @Autowired
    private final MemberSecurity memberSecurity;

    @Transactional
    public void register(BoardRegisterRequest request){
        MemberDTO memberDTO = memberSecurity.getMember();
        MemberEntity memberEntity = MemberMapper.createEntity(memberDTO);

        BoardEntity boardEntity = BoardEntity.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .image(request.getImage())
                .category(request.getCategory())
                .commentList(new ArrayList<>())
                .member(
                        memberEntity
                ).build();
        boardRepository.save(boardEntity);
    }
    //게시물 조회
    @Transactional(readOnly = true)
    public BoardResponseDTO findById(Long id)
    {
        BoardEntity board= boardRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("게시글을 찾을수없거나 삭제되었습니다:("));
        return new BoardResponseDTO(board);
    }

    public List<BoardResponseDTO> findBoardsByCategory(int category) {
        if (category < 1 || category > 8) {
            throw new IllegalArgumentException("잘못된 카테고리 번호입니다.");
        }
        List<BoardEntity> boards = boardRepository.findByCategory(category);
        return boards.stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }



//    @Transactional
//    public Long updateBoard(Long n_id, BoardRequestDTO dto)
//    {
//        BoardEntity boardEntity=boardRepository.findById(n_id)
//                .orElseThrow(()->new IllegalArgumentException("not found not found"));
//        boardEntity.update(dto.getTitle(),dto.getContent(),dto.getImage());
//        return boardEntity.getNId();
//    }


    //업데이트
    @Transactional
    public void update(BoardRegisterRequest request)
    {
        MemberDTO memberDTO=memberSecurity.getMember();
        MemberEntity memberEntity= MemberMapper.createEntity(memberDTO);
        BoardEntity boardEntity=boardRepository.findById(request.getId())
                .orElseThrow(()-> new IllegalArgumentException("not found"));
//        BoardEntity boardEntity= BoardEntity.builder().title(request.getTitle())
//            .content(request.getContent())
//            .image(request.getImage()).
//                build();
        boardEntity.update(request.getTitle(), request.getContent(), request.getImage());
    }


    //게시물 리스트
    public List<BoardResponseDTO> getAllBoards()
    {
        return boardRepository.findAll()
                .stream()
                .map(BoardResponseDTO::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void deleteBoard(Long n_id)
    {
        boardRepository.deleteById(n_id);
    }

//    @Transactional
//    public void addLike(Long n_id, Long id)
//    {
//        MemberEntity member= memberRepository.findById(id)
//                .orElseThrow(()->new IllegalArgumentException("없음"));
//        BoardEntity board=boardRepository.findById(n_id)
//                        .orElseThrow(()-> new IllegalArgumentException("존재하지 않는 게시글"));
//        board.setLikeCnt(board.getLikeCnt() + 1);
//        boardRepository.Like(n_id,member);
//    }
    @Transactional
    public void addLike(LikeRequestDTO likeRequestDTO) {
        // 1. 게시글 조회
        BoardEntity board = boardRepository.findById(likeRequestDTO.getBoardId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시글입니다."));

        // 2. 사용자 조회
        MemberEntity member = memberRepository.findById(likeRequestDTO.getMemberId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // 3. 좋아요 수 증가
        board.setLikeCnt(board.getLikeCnt() + 1);

        // 4. 데이터 저장
        boardRepository.save(board); // likeCnt 증가된 BoardEntity 저장
    }

    @Transactional
    public void addDisLike(Long n_id, String nickname)
    {
        MemberEntity member=memberRepository.findById(nickname)
                .orElseThrow(()->new IllegalArgumentException("없음"));
        boardRepository.DisLike(n_id,member);
    }


    @Transactional
    public void incrementViewCount(Long id)
    {
        boardRepository.View(id);
    }




}

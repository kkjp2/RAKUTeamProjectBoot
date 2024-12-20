package rakuproject.raku.domain.board.service;



import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.board.dto.request.ImageRequestDTO;
import rakuproject.raku.domain.board.dto.response.ImageResponseDTO;
import rakuproject.raku.domain.board.entity.BoardEntity;
import rakuproject.raku.domain.board.entity.FestivalBoardEntity;
import rakuproject.raku.domain.board.entity.ImageEntity;
import rakuproject.raku.domain.board.repository.BoardRepository;
import rakuproject.raku.domain.board.repository.FestivalBoardRepository;
import rakuproject.raku.domain.board.repository.ImageRepository;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.repository.MemberRepository;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ImageService {

    @Autowired
    private final ImageRepository imageRepository;
    @Autowired
    private final BoardRepository boardRepository;
    @Autowired
    private final FestivalBoardRepository festivalBoardRepository;
    @Autowired
    private final MemberRepository memberRepository;

    @Transactional
    public Long addImage(ImageRequestDTO imageRequestDTO)
    {
        MemberEntity member= memberRepository.findById(imageRequestDTO.getMemberId())
                .orElseThrow(()-> new IllegalArgumentException("멤버 없음"));

        if((imageRequestDTO.getBoardId() != null) ==(imageRequestDTO.getFestivalBoardId() != null))
        {
            throw new IllegalArgumentException("이미지는 한가지에만 할당하세요");
        }
        ImageEntity image= ImageEntity.builder()
                .image(imageRequestDTO.getImage())
                .member(member)
                .build();
        if(imageRequestDTO.getBoardId() != null)
        {
            BoardEntity board=boardRepository.findById(imageRequestDTO.getBoardId())
                    .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다"));
            image.setBoard(board);
        }
        if(imageRequestDTO.getFestivalBoardId() != null)
        {
            FestivalBoardEntity festivalBoard=festivalBoardRepository.findById(imageRequestDTO.getFestivalBoardId())
                    .orElseThrow(()-> new IllegalArgumentException("해당 축제 게시글이 없습니다.."));
            image.setFestivalBoard(festivalBoard);
        }
        imageRepository.save(image);
        return image.getImgId();
    }




    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getImagesByBoard(BoardEntity board)
    {
        return imageRepository.findByBoard(board)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ImageResponseDTO> getImagesByFestivalBoard(FestivalBoardEntity festivalBoard)
    {
        return imageRepository.findByFestivalBoard(festivalBoard)
                .stream()
                .map(ImageResponseDTO::new)
                .collect(Collectors.toList());
    }

}

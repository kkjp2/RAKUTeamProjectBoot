package com.example.board_test.domain.board.service;


import com.example.board_test.domain.board.entity.HashTagEntity;
import com.example.board_test.domain.board.repository.HashtagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HashTagService {
    @Autowired
    private final HashtagRepository hashtagRepository;

    @Transactional
    public void searchTag(String keyword)
    {
        HashTagEntity hashTag=hashtagRepository.findByKeyword(keyword);

        if(hashTag != null) {
            hashTag.setCount(hashTag.getCount()+1);
        }
        else{
            hashTag=HashTagEntity.builder()
                    .keyword(keyword)
                    .count(1)
                    .build();
        }
        hashtagRepository.save(hashTag);
    }
    @Transactional(readOnly = true)
    public List<HashTagEntity> getTop10HashTags()
    {
        return hashtagRepository.findTop10ByCount();
    }

//    @Transactional
//    public void searchKeyword(HashTagRequestDTO hashTagRequestDTO)
//    {
//        String keyword=hashTagRequestDTO.getKeyword();
//        if(hashtagRepository.existsByKeyword(keyword))
//        {
//            hashtagRepository.SearchCount(keyword);
//        }
//        else{
//            HashTagEntity newHashTag= hashTagRequestDTO.toEntity();
//            hashtagRepository.save(newHashTag);
//        }
//    }



}

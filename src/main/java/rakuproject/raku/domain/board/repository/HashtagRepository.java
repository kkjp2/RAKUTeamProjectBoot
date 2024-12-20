package rakuproject.raku.domain.board.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import rakuproject.raku.domain.board.entity.HashTagEntity;

import java.util.List;

@Repository
public interface HashtagRepository extends JpaRepository<HashTagEntity, Long> {

   HashTagEntity findByKeyword(String keyword);

   @Query("SELECT h FROM HashTagEntity h ORDER BY h.count DESC LIMIT 10")
    List<HashTagEntity> findTop10ByCount();


    @Modifying
    @Query("UPDATE HashTagEntity h SET h.count =h.count +1 WHERE h.keyword = :keyword")
    void SearchCount(@Param("keyword") String keyword);

    boolean existsByKeyword(String keyword);

}

package rakuproject.raku.domain.manager.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ManagerQueryServiceImpl implements ManagerQueryService {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Optional<MemberEntity> findByEmail(String email) {
        String query = "SELECT m FROM MemberEntity m WHERE m.id = :email";
        TypedQuery<MemberEntity> typedQuery = entityManager.createQuery(query, MemberEntity.class);
        typedQuery.setParameter("email", email);
        return typedQuery.getResultList().stream().findFirst();
    }

    @Override
    public List<MemberEntity> findByNickname(String nickname) {
        return List.of();
    }
}

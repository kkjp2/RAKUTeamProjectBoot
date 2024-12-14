package rakuproject.raku.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import rakuproject.raku.domain.member.repository.EmailCheckRepository;

@Service
@RequiredArgsConstructor
public class EmailCheckService {

    private final EmailCheckRepository emailCheckRepository;

    public boolean isEmailDuplicate(String id) {
        return emailCheckRepository.existsById(id);

    }
}


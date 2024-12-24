package rakuproject.raku.domain.mypage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.mypage.repository.AddressEditRepository;
import rakuproject.raku.domain.mypage.repository.IdEditRepository;

@Service
@RequiredArgsConstructor
public class AddressEditService {
    @Autowired
    public final AddressEditRepository addressEditRepository;

    public void updateId(Long userKey, int newAddress) {
        MemberEntity user = addressEditRepository.findById(userKey)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + userKey));

        user.setAddress(newAddress);
        addressEditRepository.save(user); // 변경된 닉네임 저장
    }

}

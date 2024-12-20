package rakuproject.raku.domain.manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rakuproject.raku.domain.manager.service.ManagerService;
import rakuproject.raku.domain.member.entity.MemberEntity;

import java.util.List;
import java.util.Optional;

public class ManagerController {
    @Autowired
    private ManagerService managerService;

    // 메일로 사용자 검색
    @GetMapping("/searchByEmail")
    public ResponseEntity<Optional<MemberEntity>> searchByEmail(@RequestParam String email){
        Optional<MemberEntity> member = managerService.searchByEmail(email);
        return member.isPresent()?ResponseEntity.ok(member):ResponseEntity.notFound().build();
    }

    // 닉네임으로 사용가 검색
    @GetMapping("/searchByNickname")
    public ResponseEntity<List<MemberEntity>> searchByNickname(@RequestParam String nickname){
        List<MemberEntity> members = managerService.searchByNickname(nickname);
        return members.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(members);
    }
}

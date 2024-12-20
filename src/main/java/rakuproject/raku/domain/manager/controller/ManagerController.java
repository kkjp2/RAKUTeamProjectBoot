package rakuproject.raku.domain.manager.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import rakuproject.raku.domain.manager.service.ManagerService;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;

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

    // 닉네임으로 사용자 검색
    @GetMapping("/searchByNickname")
    public ResponseEntity<List<MemberEntity>> searchByNickname(@RequestParam String nickname){
        List<MemberEntity> members = managerService.searchByNickname(nickname);
        return members.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(members);
    }

    // 유저인가 관리자인가 확인
    @GetMapping("/searchByRole")
    public ResponseEntity<List<MemberEntity>> searchByRole(@RequestParam MemberRole role){
        List<MemberEntity> members = managerService.searchByRole(role);
        return members.isEmpty()?ResponseEntity.notFound().build():ResponseEntity.ok(members);
    }

    //회원 삭제 기능
    @DeleteMapping("/deleteUserByEmail")
    public ResponseEntity<String> deleteUserByEmail(@RequestParam String id) {
        try {
            managerService.deleteUser(id);
            return ResponseEntity.ok("사용자를 성공적으로 삭제 하였습니다");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

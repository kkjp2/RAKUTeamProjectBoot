package rakuproject.raku.domain.member;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import rakuproject.raku.domain.member.entity.MemberEntity;
import rakuproject.raku.domain.member.entity.enums.MemberRole;
import rakuproject.raku.domain.member.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class MemberEntityTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    void testInsertMembers() {
        IntStream.rangeClosed(1, 30).forEach(i -> {
            MemberEntity memberEntity = MemberEntity.builder()
                    .id("user" + i + "@example.com")           // 设置用户名
                    .pwd(passwordEncoder.encode("1111"))              // 设置密码
                    .nick("USER" + i)          // 设置昵称
                    .address(100 + i)          // 示例地址
                    .role(MemberRole.USER)     // 假设角色为 USER
                    .alarm(1)                  // 默认报警设置为1
                    .borndate(LocalDate.of(1990, 1, (i % 28) + 1)) // 设置出生日期
                    .accessdate(LocalDate.now()) // 设置当前访问日期
                    .bookmark("bookmark" + i)  // 示例书签
                    .recent("recent" + i)      // 示例最近活动
                    .build();
            memberRepository.save(memberEntity);
        });
    }


    @Test
    void batchUpdateMembersToEmailFormat() {
        List<MemberEntity> members = memberRepository.findAll();
        members.forEach(member -> {
            String originalId = member.getId();
            member.setId(originalId + "@example.com");
        });
        memberRepository.saveAll(members);
    }

}


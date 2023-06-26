package example.usermanagement.service;

import example.usermanagement.domain.Member;
import example.usermanagement.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() {
        // Given
        Member newMember = new Member();
        newMember.setName("David");

        // When
        Long saveId = memberService.join(newMember);

        // Then
        Member member = memberService.findOneMember(saveId).get();
        Assertions.assertThat(newMember.getName()).isEqualTo(member.getName());
    }

    @Test
    void testDuplicate() {
        // Given
        Member newMember1 = new Member();
        newMember1.setName("David");

        Member newMember2 = new Member();
        newMember2.setName("David");

        // When
        memberService.join(newMember1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(newMember2));

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
/*
        try {
            memberService.join(newMember2);
            fail();
        } catch (Exception e) {
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
    }

}
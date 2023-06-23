package example.usermanagement.service;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import example.usermanagement.domain.Member;
import example.usermanagement.repository.MemberRepository;
import example.usermanagement.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemoryMemberRepository memberRepository;
    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); // Dependency Injection (DI)
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }

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

    @Test
    void findAllMembers() {
    }

    @Test
    void findOneMember() {
    }
}
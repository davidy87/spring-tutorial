package example.usermanagement.service;

import example.usermanagement.domain.Member;
import example.usermanagement.repository.*;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     * @param member
     * @return member id
     */
    public Long join(Member member) {
        // 회원 이름 중복 불가
        checkDuplicate(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void checkDuplicate(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 멤버 조회
     * @return
     */
    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOneMember(Long id) {
        return memberRepository.findById(id);
    }
}

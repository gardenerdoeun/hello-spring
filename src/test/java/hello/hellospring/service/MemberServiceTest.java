package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepositoty;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepositoty memoryMemberRepositoty;
    @BeforeEach
    public void beforeEach(){
        memoryMemberRepositoty = new MemoryMemberRepositoty();
        memberService = new MemberService(memoryMemberRepositoty);
    }
    @AfterEach // 메서드 실행이 끝날때마다 동작(콜백 같은 개념)
    public void aterEach(){
        memoryMemberRepositoty.clearStore();
    }

    @Test
    void 회원가입() {
        //given(무언가 주어진 상황)
        Member member = new Member();
        member.setName("hello");
        //when(실행 했을 때)
        Long saveId = memberService.join(member);
        //then(해당 결과가 나와야함)
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1);
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//        try{
//            memberService.join(member2);
//            fail();//실패
//        } catch (IllegalStateException e){
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }
        //then
    }

    @Test
    void 전체회원조회() {
    }

    @Test
    void findOne() {
    }
}
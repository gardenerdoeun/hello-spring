package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

// Test 클래스는 Public 안해도 됨.
class MemoryMemberRepositoryTest {
    MemoryMemberRepositoty repository = new MemoryMemberRepositoty();

    @AfterEach // 메서드 실행이 끝날때마다 동작(콜백 같은 개념)
    public void aterEach(){
        repository.clearStore();
    }

    //save 함수가 동작하는 지 확인한다.
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // optional에서 값을 꺼낼땐 get()을 사용

        // 검증하기
        //Assertions.assertEquals(member, result);//System.out.println("result = " + (result == member));
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }


    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
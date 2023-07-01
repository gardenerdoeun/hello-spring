package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

//    jpa를 사용하기 위해 EntityManager를 주입 받아야 함(jpa는 EntityManager 통해서 모든 걸 동작함)
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        //단건이 아닌 리스트는 jpql 객체 지향 쿼리 언어를 사용?
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 기존의 sql과 달리 객체를 대상으로 쿼리를 보내면 sql로 번역이 됨.
       return em.createQuery("select m from Member m", Member.class)
               .getResultList();
    }
}

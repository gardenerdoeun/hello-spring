package hello.hellospring.service;

import hello.hellospring.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    // jdbc 사용시
//    //스프링이 제공해주는 DataSource 사용
//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }

//    //jpa
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

    //스프링 데이터 JPA
    private final MemberRepository memberRepository;
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    //자바 코드로 직접 스프링 빈 등록하기
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }
//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepositoty();
////        // 순수 JDBC
////        return new JdbcMemberRepository(dataSource);
////
//        // JDBCTemplate
////        return new JdbcTemplateMemberRepository((dataSource));
//
//        //JPA
////        return new JpaMemberRepository(em);
//    }
}

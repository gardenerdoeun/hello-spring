package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // memberservice를 가져와 연결시켜줌(의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

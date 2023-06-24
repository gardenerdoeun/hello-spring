package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired // memberservice를 가져와 연결시켜줌(의존관계 주입)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //Get : 조회할 때 주로 사용
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

//    Post : Form에 데이터를 넣어서 전달 할 때 사용
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();

        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);
        return "members/memberList";

    }
}

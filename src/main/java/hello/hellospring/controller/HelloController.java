package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //http body부에 데이터를 직접 넣어 줄것이라고 정의하는 것. ( 아래 return 값에 html 문법을 직접 넣는 것도 가능)
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello spring" -> 이전과 차이점 : view 없이 바로 전달
    }

    @GetMapping("hello-api")
    @ResponseBody //param을 넘기면 HttpMessageConverter라는 게 동작함(객체와 Text을 구분해서 동작함)
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello(); // 객체 생성
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}

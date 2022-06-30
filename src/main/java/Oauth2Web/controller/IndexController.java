package Oauth2Web.controller;

import Oauth2Web.Dto.MemberDto;
import Oauth2Web.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller // 컨트롤러 역할을 한다고 선언(Templates 영역)
public class IndexController {

    @Autowired //자동 빈 생성 [ 자동 생성자 이용한 객체에 메모리 할당 ]
    private IndexService indexService; // indexService 객체  메모리 할당


    @GetMapping // 메인 페이지(url)  맵핑
    public String main(){
        return "main";
    }

    @GetMapping("/member/login") // 로그인 페이지(url) 맵핑
    public String login(){
        return "login";
    }

    @GetMapping("/member/signup") // 회원가입 페이지(url) 맵핑
    public String signup(){
        return "signup";
    }

    @PostMapping("/member/signupcontroller") // 회원가입 처리 맵핑(Service 영역)
    public  String signupcontroller(MemberDto memberDto){ // memberDto 인수로 받음 : 회원가입 시 id 와 pw
        indexService.signup(memberDto); //  indexService 객체 내 있는 sign 메소드 실행(인수는 입력받은 memberDto)
        return "redirect:/"; // 최상위 페이지로 이동
    }

}

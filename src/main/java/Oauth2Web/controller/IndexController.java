package Oauth2Web.controller;

import Oauth2Web.Dto.MemberDto;
import Oauth2Web.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@org.springframework.stereotype.Controller
public class IndexController {

    @Autowired
    private IndexService indexService;


    @GetMapping
    public String main(){
        return "main";
    }

    @GetMapping("/member/login")
    public String login(){
        return "login";
    }

    @GetMapping("/member/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/member/signupcontroller")
    public  String signupcontroller(MemberDto memberDto){
        indexService.signup(memberDto);
        return "redirect:/";
    }

}

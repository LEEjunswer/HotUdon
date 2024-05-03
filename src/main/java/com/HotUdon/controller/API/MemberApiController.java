package com.HotUdon.controller.API;

import com.HotUdon.model.Member;
import com.HotUdon.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
public class MemberApiController {

    MemberServiceImpl memberService;
     HttpSession session;
    @RequestMapping(value = "/joinFormId",method = RequestMethod.POST)
    public String checkJoinFormLoginId(Member member){
        if (memberService.findByLoginId(member.getLoginId()) != null){
            return "fail";
        }
        return "suc";

    }
    @RequestMapping(value= "joinFormNick", method =  RequestMethod.POST)
    public String checkJoinFormNick(Member member){
        if(memberService.findByNickname(member.getNickName() )!= null){
            return "fail";
        }
        return "suc";
    }

// model은 보안에 맞게 넣기 @Aut
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public RedirectView checkLogin(Model  model, Member member){

        if(memberService.findByLoginIdAndPassword(member.getLoginId(), member.getPassword()) != null) {

            return new RedirectView("home");
        }
        return  new RedirectView("loginForm");
    }

}

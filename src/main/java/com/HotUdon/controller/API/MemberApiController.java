package com.HotUdon.controller.API;

import com.HotUdon.model.Member;
import com.HotUdon.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberServiceImpl memberService;
    private final HttpSession session;

    @PostMapping(value = "/joinFormId")
    public String checkJoinFormLoginId(@RequestParam String loginId){
        if (memberService.findByLoginId(loginId) != null){
            return "fail";
        }
        return "suc";

    }
    @PostMapping(value= "/joinFormNick")
    public String checkJoinFormNick(@RequestParam String nickname){
        if(memberService.findByNickname(nickname)!= null){
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

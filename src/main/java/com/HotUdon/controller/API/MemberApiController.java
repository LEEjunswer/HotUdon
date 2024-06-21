package com.HotUdon.controller.API;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberServiceImpl memberService;
    private final HttpSession session;

        @PostMapping(value = "/joinFormId")
        public ResponseEntity<Map<String, String>> checkLoginId(@RequestParam String loginId){
            Map<String,String> response = new HashMap<>();
            try {
                memberService.findByLoginId(loginId);
                response.put("status", "fail");
                return ResponseEntity.ok().body(response);
            } catch (RuntimeException e) {
                response.put("status", "suc");
                return ResponseEntity.ok().body(response);
            }

        }
    @PostMapping(value= "/joinFormNick")
    public ResponseEntity<Map<String,String>> checkNickname(@RequestParam String nickname){
            Map<String,String> response = new HashMap<>();
        System.out.println("nickname = " + nickname);
        try{
            response.put("status","fail");
            memberService.findByNickname(nickname);
            return ResponseEntity.ok().body(response);
        }catch(RuntimeException e) {
            response.put("status","suc");
            return ResponseEntity.ok().body(response);
        }
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

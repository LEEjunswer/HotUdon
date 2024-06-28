package com.HotUdon.controller.API;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        @PostMapping(value = "/joinFormId")
        public ResponseEntity<Map<String, String>> checkLoginId(@RequestParam String loginId){
            System.out.println("loginId = " + loginId);
            Map<String,String> response = new HashMap<>();
            if(memberService.findByLoginId(loginId) != null) {
                response.put("status", "fail");
                return ResponseEntity.ok().body(response);
            }
                response.put("status", "suc");
                return ResponseEntity.ok().body(response);
        }
    @PostMapping(value= "/joinFormNick")
    public ResponseEntity<Map<String,String>> checkNickname(@RequestParam String nickname){
            Map<String,String> response = new HashMap<>();
        if(memberService.findByNickname(nickname) != null) {
            response.put("status", "fail");
            return ResponseEntity.ok().body(response);
        }
            response.put("status","suc");
            return ResponseEntity.ok().body(response);

        }


}

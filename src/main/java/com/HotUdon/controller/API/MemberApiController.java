package com.HotUdon.controller.API;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.member.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
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
        @PostMapping(value = "/updateProfile")
    public ResponseEntity<Map<String,String>> updateProfile(@AuthenticationPrincipal PrincipalDetails principalDetails, @RequestParam("profileImage") MultipartFile profileImage, HttpSession session) throws IOException {
            if(profileImage.isEmpty()){
                throw new IllegalArgumentException("프로필 이미지가 존재하지 않습니다.");

            }
            Map<String,String> response = new HashMap<>();
            if(principalDetails == null){
                response.put("msg","잘못된 접근입니다.");
                return ResponseEntity.ok(response);
            }
            Member member = principalDetails.getMember();
            String updateProfile = memberService.updateProfile(member.getId(),profileImage);
            if(updateProfile != null) {
                response.put("msg", "정상적으로 프로필이미지가 변경되었습니다.");
                session.setAttribute("profile",updateProfile);
                return ResponseEntity.ok(response);
            }
            response.put("msg", "잘못된 접근으로 인하여 프로필 이미지 변경이 취소되었습니다.");
            return ResponseEntity.ok(response);
        }


}

package com.HotUdon.controller.Front;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.service.member.MemberServiceImpl;
import com.HotUdon.session.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping("/join")
    public String joinForm(HttpSession session, RedirectAttributes redirectAttributes){

        if( session.getAttribute(SessionConst.USER_ID) != null){
            redirectAttributes.addFlashAttribute("error","잘못된 접근입니다");
            return "index";

        }

        return "member/join";
    }
    @PostMapping("/join")
    public String getJoinForm(RedirectAttributes redirectAttributes, Model model, MemberDTO memberDTO) {
        memberService.save(memberDTO);

        redirectAttributes.addFlashAttribute("suc","성공적으로 회원가입이 완료되었습니다");
        return "index";
    }
    @GetMapping("/login")
    public  String loginForm(HttpSession session, RedirectAttributes redirectAttributes){
        if(session.getAttribute("id") != null) {
            redirectAttributes.addFlashAttribute("error","잘못된 접근입니다");
            return "index";
        }
         return "loginForm";
    }
}

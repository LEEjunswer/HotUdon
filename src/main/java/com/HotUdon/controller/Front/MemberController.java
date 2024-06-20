package com.HotUdon.controller.Front;

import com.HotUdon.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberServiceImpl memberService;

    @GetMapping("/join")
    public String joinForm(HttpSession session, RedirectAttributes redirectAttributes){
        if(session.getAttribute("id") != null){
            redirectAttributes.addFlashAttribute("error","잘못된 접근입니다");
            return "index";

        }

        return "joinForm";
    }
    @PostMapping("/join")
    public String getJoinForm(RedirectAttributes redirectAttributes, Model model) {


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

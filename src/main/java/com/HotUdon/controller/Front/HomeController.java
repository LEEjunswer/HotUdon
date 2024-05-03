package com.HotUdon.controller.Front;

import com.HotUdon.service.MemberServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@AllArgsConstructor
@Slf4j
public class HomeController {

     private  HttpSession  httpSession;
     private  final MemberServiceImpl memberService;

     @GetMapping("/")
    public String Home(){


         return "index";
     }
}

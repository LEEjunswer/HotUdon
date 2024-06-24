package com.HotUdon.controller.Front;


import com.HotUdon.config.oauth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

     @GetMapping("/home")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails){
         if (principalDetails != null) {
             System.out.println("Logged in user: " + principalDetails.getUsername());
         } else {
             System.out.println("PrincipalDetails is null");
         }
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         if (authentication != null) {
             Object principal = authentication.getPrincipal();
             if (principal instanceof PrincipalDetails) {
                 principalDetails = (PrincipalDetails) principal;
                 System.out.println("Logged in user from SecurityContextHolder: " + principalDetails.getUsername());
             } else {
                 System.out.println("Principal is not an instance of PrincipalDetails: " + principal);
             }
         } else {
             System.out.println("No authentication found in SecurityContextHolder");

     }
         return "index";
     }

}

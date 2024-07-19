package com.HotUdon.controller.Front;


import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import com.HotUdon.service.chatRoom.ChatRoomService;
import com.HotUdon.service.notification.NotificationService;
import com.HotUdon.service.register.RegisterService;
import com.HotUdon.util.AddressParser;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final NotificationService notificationService;
    private final ChatRoomService chatRoomService;
    private final RegisterService registerService;
    private boolean logincheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null){
            return false;
        }
        return true;
    }
     @GetMapping("/home")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails,HttpSession session,Model model){
        if(!logincheck(principalDetails)){
            Page<RegisterDTO> registerPage = registerService.getNotSoldOutProduct(5,5);
            model.addAttribute("product",registerPage);
            return "index";
        }
            Member member =principalDetails.getMember();
             List<NotificationDTO> myDibsProducts =notificationService.myDibsProducts(member.getId());
          String myLocation  =  AddressParser.addressExtraction(member.getAddress());
            Page<RegisterDTO> myRegisterPage = registerService.getNotSoldOutProductMyLocation(myLocation,5,5);
             String profile = member.getProfileImg();
             int unreadCount = chatRoomService.findByUnreadCount(member.getId());
            /* String profile = */
            //로그인 이후에는 우리동네 보여주기
             model.addAttribute("product",myRegisterPage);
             session.setAttribute("profile",profile);
             session.setAttribute("unreadCount",unreadCount);
             session.setAttribute("myDibs",myDibsProducts.size());
             return "index";
     }

}

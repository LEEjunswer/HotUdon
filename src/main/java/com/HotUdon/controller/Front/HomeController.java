package com.HotUdon.controller.Front;


import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.dto.NotificationDTO;
import com.HotUdon.service.chatRoom.ChatRoomService;
import com.HotUdon.service.notification.NotificationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    private boolean logincheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails == null){
            return false;
        }
        return true;
    }
     @GetMapping("/home")
    public String home(@AuthenticationPrincipal PrincipalDetails principalDetails,HttpSession session){
         if (logincheck(principalDetails)) {
            Long memberId =principalDetails.getMember().getId();
             List<NotificationDTO> myDibsProducts =notificationService.myDibsProducts(memberId);
             int unreadCount = chatRoomService.findByUnreadCount(memberId);
             System.out.println("unreadCount = " + unreadCount);
             session.setAttribute("unreadCount",unreadCount);
             session.setAttribute("myDibs",myDibsProducts.size());
             return "index";
         }


         return "index";
     }

}

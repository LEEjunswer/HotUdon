package com.HotUdon.controller.Front;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.model.Member;
import com.HotUdon.service.chatMessage.ChatMessageService;
import com.HotUdon.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/chatRoom")
public class ChatRoomController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private boolean loginCheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails != null){
            return  true;
        }
        return false;
    }

    @GetMapping("/myMessages")
    public String myChatRooms(@AuthenticationPrincipal PrincipalDetails principalDetails,Model model){
        if(!loginCheck(principalDetails)){
             return "member/loginForm";
        }
        Member member = principalDetails.getMember();
        List<ChatRoomDTO> chatRoomDTOList =chatRoomService.findAllByMemberId(member.getId());
        model.addAttribute("m",member);
        model.addAttribute("chatRooms",chatRoomDTOList);
        return "chat/myMessage";
    }
}



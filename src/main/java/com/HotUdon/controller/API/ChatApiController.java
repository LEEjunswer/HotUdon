package com.HotUdon.controller.API;

import com.HotUdon.config.oauth.PrincipalDetails;
import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.dto.MemberDTO;
import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.service.chatMessage.ChatMessageService;
import com.HotUdon.service.chatRoom.ChatRoomService;
import com.HotUdon.service.member.MemberService;
import com.HotUdon.service.register.RegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatApiController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
    private final MemberService memberService;
    private final RegisterService registerService;
    private boolean loginCheck(@AuthenticationPrincipal PrincipalDetails principalDetails){
        if(principalDetails != null){
            return true;
        }
        return  false;
    }
    @PostMapping("/chat")
    public ResponseEntity<Map<String, Object>> createChat(@AuthenticationPrincipal PrincipalDetails principalDetails
    , @RequestParam Long memberId, @RequestParam Long registerId ){
       Map<String,Object> response =new HashMap<>();
        if(!loginCheck(principalDetails)){
            response.put("msg", "잘못된 접근입니다.");
           return ResponseEntity.ok(response);
        }
        ChatRoomDTO check = chatRoomService.createChatRoom(memberId, registerId);
        if(check != null){
            System.out.println("check = " + check);
           MemberDTO memberDTO = memberService.findById(memberId);
            RegisterDTO registerDTO = registerService.findById(registerId);
            System.out.println("memberDTO = " + memberDTO);
           response.put("msg,", memberDTO.getNickName()+"채팅방에 입장하셧습니다");
            response.put("chatRoom", check);
            response.put("buyer",memberDTO);
            response.put("seller",registerDTO);
           return ResponseEntity.ok(response);
        }
        /*실패 로직*/
        response.put("msg","채팅방 생성이 실패하였습니다");
        ResponseEntity.ok(response);
        return ResponseEntity.ok(response);

    }
   /* @GetMapping("/{roomId}/chat")
    public ResponseEntity<Map<String,String>> referenceChat(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                            @PathVariable("roomId")Long roomId,@RequestParam Long memberId,@RequestParam Long registerId){
        Map<String,String> response = new HashMap<>();
        if(!loginCheck(principalDetails)){
            response.put("msg","잘못된 접근입니다.");
            return ResponseEntity.ok(response);
        }
        if(principalDetails.getMember().getId().equals(memberId)){
            response.put("msg","구매자");
            return ResponseEntity.ok(response);
        }
        if(principalDetails.getMember().getId().equals(registerId)){
            response.put("msg","판매자");
            return  ResponseEntity.ok(response);
        }
        response.put("msg","잘못된 접근입니다");
        return ResponseEntity.ok(response);
    }*/
    @PostMapping("/{roomId}/chat")
    public ResponseEntity<Map<String,String>> sendChat(@AuthenticationPrincipal PrincipalDetails principalDetails,
                                                       @PathVariable("roomId")Long roomId,
                                                       @RequestBody ChatMessageDTO chatMessageDTO){
        Map<String, String> response = new HashMap<>();
        if (principalDetails == null) {
            response.put("msg", "잘못된 접근입니다.");
            return ResponseEntity.ok(response);
        }

        ChatRoomDTO chatRoom = chatRoomService.findById(roomId);
        if (chatRoom == null) {
            response.put("msg", "채팅방이 존재하지 않습니다.");
            return ResponseEntity.ok(response);
        }
        MemberDTO sender;
        MemberDTO receiver;
        RegisterDTO registerDTO = registerService.findById(chatRoom.getRegisterId()); //판매자
         MemberDTO memberDTO = memberService.findById(chatRoom.getMemberId());  //구매자
        if (principalDetails.getMember().getId().equals(registerDTO.getMemberDTO().getId())) {
            sender = registerDTO.getMemberDTO();
            receiver = memberDTO;
        } else if (principalDetails.getMember().getId().equals(memberDTO.getId())) {
            sender = memberDTO;
            receiver = registerDTO.getMemberDTO();
        } else {
            response.put("msg", "잘못된 접근입니다.");
            return ResponseEntity.ok(response);
        }
        chatMessageService.sendMessage(chatMessageDTO);
        return ResponseEntity.ok(response);
    }

}

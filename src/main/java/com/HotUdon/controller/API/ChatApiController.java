package com.HotUdon.controller.API;

import com.HotUdon.service.chatMessage.ChatMessageService;
import com.HotUdon.service.chatRoom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class ChatApiController {
    private final ChatRoomService chatRoomService;
    private final ChatMessageService chatMessageService;
}

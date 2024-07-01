package com.HotUdon.service.chatRoom;

import com.HotUdon.dto.ChatMessageDTO;
import com.HotUdon.dto.ChatRoomDTO;
import com.HotUdon.mapper.ChatRoomMapper;
import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import com.HotUdon.repository.chatRoom.ChatRoomRepository;
import com.HotUdon.repository.member.MemberRepository;
import com.HotUdon.repository.register.RegisterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomServiceImpl implements ChatRoomService{

    private final MemberRepository memberRepository;

    private final RegisterRepository registerRepository;

    private final ChatRoomRepository chatRoomRepository;

    @Override
    public ChatRoomDTO createChatRoom(Long memberId, Long registerId) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Register register = registerRepository.findById(registerId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid register ID"));
        Optional<ChatRoom> existingChatRoom = chatRoomRepository.findByMemberAndRegister(member, register);
        if (existingChatRoom.isPresent()) {
            return ChatRoomMapper.mapEntityToDto(existingChatRoom.get());
        }
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setMember(member);
        chatRoom.setRegister(register);

        return ChatRoomMapper.mapEntityToDto(chatRoomRepository.save(chatRoom));
    }

    @Override
    public List<ChatRoomDTO> findAllByMemberId(Long memberId) {
       List<ChatRoomDTO> chatRoomDTOList =chatRoomRepository.findByAllMessageMemberId(memberId).stream().map(ChatRoomMapper :: mapEntityToDto).collect(Collectors.toList());
        for (ChatRoomDTO chatRoomDTO : chatRoomDTOList) {
            List<ChatMessageDTO> messages = chatRoomDTO.getMessages();
            if (messages != null && !messages.isEmpty()) {
                ChatMessageDTO lastMessage = messages.get(messages.size() - 1);
                chatRoomDTO.setLastMessage(lastMessage);
                chatRoomDTO.setRelativeTime(calculateRelativeTime(chatRoomDTO.getLastMessage().getUpdateDate()));
            } else {
                chatRoomDTO.setLastMessage(null);
            }
        }
      return chatRoomDTOList;

    }

    @Override
    public ChatRoomDTO findById(Long roomId) {
      Optional<ChatRoom> chatRoomOptional = chatRoomRepository.findById(roomId);
      if(chatRoomOptional.isPresent()){
        ChatRoom chatRoom=   chatRoomOptional.get();
          return ChatRoomMapper.mapEntityToDto(chatRoom);
      }

        return null;
    }

    @Override
    public int findByUnreadCount(Long memberId) {
            int unreadCountBuyerMessage = chatRoomRepository.findAllByRegisterIdInMemberIdCount(memberId);
            int unreadCountSellerMessage = chatRoomRepository.findAllByMemberIdCount(memberId);
        return unreadCountSellerMessage+unreadCountBuyerMessage;
    }

    public String calculateRelativeTime(String dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
        LocalDateTime messageTime = LocalDateTime.parse(dateTime, formatter);
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(messageTime, now);
        long minutes = duration.toMinutes();
        long hours = duration.toHours();
        long days = duration.toDays();

        if (minutes < 60) {
            return minutes + " 분 전";
        } else if (hours < 24) {
            return hours + " 시간 전";
        } else {
            return days + " 일 전";
        }
    }
}

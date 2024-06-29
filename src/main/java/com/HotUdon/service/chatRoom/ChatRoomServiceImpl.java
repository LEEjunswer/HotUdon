package com.HotUdon.service.chatRoom;

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

import java.util.List;
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
        ChatRoom chatRoom = new ChatRoom();
        chatRoom.setMember(member);
        chatRoom.setRegister(register);

        return ChatRoomMapper.mapEntityToDto(chatRoomRepository.save(chatRoom));
    }

    @Override
    public List<ChatRoomDTO> findAllByMemberId(Long memberId) {
      return   chatRoomRepository.findAllByMemberId(memberId).stream().map(ChatRoomMapper :: mapEntityToDto).collect(Collectors.toList());

    }

}

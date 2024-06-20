package com.HotUdon.dto;


import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatRoomDTO {


    private Long id;

    private Register register;  // 이 채팅방의 판매자 (상품 등록자)

    private MemberDTO member; //  채팅방의 구매자
}

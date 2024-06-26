package com.HotUdon.service.member;

import com.HotUdon.dto.MemberDTO;
import com.HotUdon.model.Member;

public interface MemberService {
    MemberDTO findByLoginIdAndPassword(String loginId, String password);
    void save(MemberDTO memberDTO);
    MemberDTO findById(Long id);
    MemberDTO findByLoginId (String id);
    MemberDTO findByNickname(String nickName);
    void updateMember(Long id, MemberDTO memberDTO);



}

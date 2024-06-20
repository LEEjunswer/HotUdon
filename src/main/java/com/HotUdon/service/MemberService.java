package com.HotUdon.service;

import com.HotUdon.dto.MemberDTO;

public interface MemberService {
    MemberDTO findByLoginIdAndPassword(String loginId, String password);
    int save(MemberDTO memberDTO);
    MemberDTO findById(Long id);
    MemberDTO findByLoginId (String id);
    MemberDTO findByNickname(String nick);
}

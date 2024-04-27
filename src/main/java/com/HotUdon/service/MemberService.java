package com.HotUdon.service;

import com.HotUdon.dto.MemberDTO;

public interface MemberService {
    MemberDTO findByLoginIdAndPassword(String loginId, String password);
    MemberDTO save(MemberDTO memberDTO);
    MemberDTO findById(Long id);
}

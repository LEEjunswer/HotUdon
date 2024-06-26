package com.HotUdon.service.register;

import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;

public interface RegisterService {
    Long save(RegisterDTO registerDTO, Member member);
    RegisterDTO findById(Long id);
}

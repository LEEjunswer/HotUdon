package com.HotUdon.service.register;

import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;
import org.springframework.data.domain.Page;

public interface RegisterService {
    Long save(RegisterDTO registerDTO, Member member);
    RegisterDTO findById(Long id);
    Page<RegisterDTO> findBySearchProduct(String search,int page, int size);

}

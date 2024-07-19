package com.HotUdon.service.register;

import com.HotUdon.dto.RegisterDTO;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterService {
    Long save(RegisterDTO registerDTO, Member member);
    RegisterDTO findById(Long id);
    Page<RegisterDTO> findBySearchProduct(String search,int page, int size);
    Page<RegisterDTO> getNotSoldOutProduct(int page, int size);
    Page<RegisterDTO> getNotSoldOutProductMyLocation(String location,int page, int size);
}

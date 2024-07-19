package com.HotUdon.repository.register;

import com.HotUdon.model.Register;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface RegisterCustomRepository {
    Page<Register> searchRegisters(String keyword, Pageable pageable);
    Page<Register> getNotSoldOutProduct(Pageable pageable);
    Page<Register> getNotSoldOutProductMyLocation(String location,Pageable pageable);
}

package com.HotUdon.repository.register;

import com.HotUdon.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisterRepository extends JpaRepository<Register,Long>, RegisterCustomRepository{
}

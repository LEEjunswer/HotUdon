package com.HotUdon.repository.register;

import com.HotUdon.model.ChatRoom;
import com.HotUdon.model.Member;
import com.HotUdon.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register,Long>, RegisterCustomRepository{


}

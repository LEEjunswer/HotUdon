package com.HotUdon.repository;

import com.HotUdon.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member ,Long> {

    Member findByLoginIdAndPassword(String LoginId,String password);
    Optional<Member> findById(Long id);
    Optional<Member> findByLoginId(String id);
    List<Member> findAllBy();
    Optional<Member> findByNickName(String nickname);

}

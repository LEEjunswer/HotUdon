package com.HotUdon.repository.register;

import com.HotUdon.model.Register;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import  static com.HotUdon.model.QRegister.register;

@Repository
@RequiredArgsConstructor
public class RegisterCustomRepositoryImpl  implements RegisterCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Register> searchRegisters(String keyword, Pageable pageable) {
       // List말고 QueryResults 쓰고 .fetchResult 를 쓸 수 있다
        List<Register> results= jpaQueryFactory
                .selectFrom(register)
                .where(register.title.containsIgnoreCase(keyword))
                .orderBy(register.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        long total = jpaQueryFactory
                .selectFrom(register)
                .where(register.title.containsIgnoreCase(keyword))
                .fetch().size();

        return new PageImpl<>(results, pageable, total);
    }

    @Override
    public Page<Register> getNotSoldOutProduct(Pageable pageable) {
        List<Register> results = jpaQueryFactory
                .selectFrom(register)
                .where(register.productStatus.eq(0))
                .orderBy(register.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = results.size();

        return new PageImpl<>(results,pageable,total);
    }
    /*우리동내 판매*/
    public Page<Register> getNotSoldOutProductMyLocation(String location,Pageable pageable) {
        List<Register> results = jpaQueryFactory
                .selectFrom(register)
                .where(register.productStatus.eq(0),register.sellerLocation.eq(location))
                .orderBy(register.regDate.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        long total = results.size();

        return new PageImpl<>(results,pageable,total);
    }
}

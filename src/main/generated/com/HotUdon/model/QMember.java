package com.HotUdon.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -228221953L;

    public static final QMember member = new QMember("member1");

    public final StringPath address = createString("address");

    public final StringPath addressDetail = createString("addressDetail");

    public final NumberPath<Integer> cash = createNumber("cash", Integer.class);

    public final BooleanPath dormantAccount = createBoolean("dormantAccount");

    public final StringPath email = createString("email");

    public final NumberPath<Integer> grade = createNumber("grade", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath loginDay = createString("loginDay");

    public final StringPath loginId = createString("loginId");

    public final StringPath nickName = createString("nickName");

    public final StringPath password = createString("password");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final NumberPath<Integer> postCode = createNumber("postCode", Integer.class);

    public final StringPath profileImg = createString("profileImg");

    public final StringPath regDate = createString("regDate");

    public final EnumPath<Role> role = createEnum("role", Role.class);

    public final NumberPath<Integer> Status = createNumber("Status", Integer.class);

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}


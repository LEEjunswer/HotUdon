package com.HotUdon.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRegister is a Querydsl query type for Register
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRegister extends EntityPathBase<Register> {

    private static final long serialVersionUID = -318735672L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRegister register = new QRegister("register");

    public final QAuction auction;

    public final BooleanPath auctionCheck = createBoolean("auctionCheck");

    public final ListPath<FileUpload, QFileUpload> files = this.<FileUpload, QFileUpload>createList("files", FileUpload.class, QFileUpload.class, PathInits.DIRECT2);

    public final NumberPath<Long> Id = createNumber("Id", Long.class);

    public final StringPath info = createString("info");

    public final QMember member;

    public final StringPath powerRegister = createString("powerRegister");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final StringPath productMethod = createString("productMethod");

    public final NumberPath<Integer> productStatus = createNumber("productStatus", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> regDate = createDateTime("regDate", java.time.LocalDateTime.class);

    public final StringPath sellerLocation = createString("sellerLocation");

    public final StringPath title = createString("title");

    public QRegister(String variable) {
        this(Register.class, forVariable(variable), INITS);
    }

    public QRegister(Path<? extends Register> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRegister(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRegister(PathMetadata metadata, PathInits inits) {
        this(Register.class, metadata, inits);
    }

    public QRegister(Class<? extends Register> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.auction = inits.isInitialized("auction") ? new QAuction(forProperty("auction"), inits.get("auction")) : null;
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
    }

}


package com.HotUdon.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QDeal is a Querydsl query type for Deal
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QDeal extends EntityPathBase<Deal> {

    private static final long serialVersionUID = -1899945199L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QDeal deal = new QDeal("deal");

    public final StringPath buyerDelete = createString("buyerDelete");

    public final NumberPath<Double> buyerGrade = createNumber("buyerGrade", Double.class);

    public final StringPath buyerReview = createString("buyerReview");

    public final StringPath endDate = createString("endDate");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QMember member;

    public final QRegister register;

    public final StringPath sellerDelete = createString("sellerDelete");

    public final NumberPath<Double> sellerGrade = createNumber("sellerGrade", Double.class);

    public final StringPath sellerReview = createString("sellerReview");

    public QDeal(String variable) {
        this(Deal.class, forVariable(variable), INITS);
    }

    public QDeal(Path<? extends Deal> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QDeal(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QDeal(PathMetadata metadata, PathInits inits) {
        this(Deal.class, metadata, inits);
    }

    public QDeal(Class<? extends Deal> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new QMember(forProperty("member")) : null;
        this.register = inits.isInitialized("register") ? new QRegister(forProperty("register"), inits.get("register")) : null;
    }

}


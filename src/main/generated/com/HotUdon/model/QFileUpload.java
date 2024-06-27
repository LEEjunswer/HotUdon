package com.HotUdon.model;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFileUpload is a Querydsl query type for FileUpload
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileUpload extends EntityPathBase<FileUpload> {

    private static final long serialVersionUID = -1932281470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFileUpload fileUpload = new QFileUpload("fileUpload");

    public final StringPath fileName = createString("fileName");

    public final StringPath filePath = createString("filePath");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QRegister register;

    public QFileUpload(String variable) {
        this(FileUpload.class, forVariable(variable), INITS);
    }

    public QFileUpload(Path<? extends FileUpload> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFileUpload(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFileUpload(PathMetadata metadata, PathInits inits) {
        this(FileUpload.class, metadata, inits);
    }

    public QFileUpload(Class<? extends FileUpload> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.register = inits.isInitialized("register") ? new QRegister(forProperty("register"), inits.get("register")) : null;
    }

}


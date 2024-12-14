package com.example.board_test.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QImageEntity is a Querydsl query type for ImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QImageEntity extends EntityPathBase<ImageEntity> {

    private static final long serialVersionUID = -437205421L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QImageEntity imageEntity = new QImageEntity("imageEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    public final QBoardEntity board;

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final QFestivalBoardEntity festivalBoard;

    public final StringPath image = createString("image");

    public final NumberPath<Long> imgId = createNumber("imgId", Long.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public QImageEntity(String variable) {
        this(ImageEntity.class, forVariable(variable), INITS);
    }

    public QImageEntity(Path<? extends ImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QImageEntity(PathMetadata metadata, PathInits inits) {
        this(ImageEntity.class, metadata, inits);
    }

    public QImageEntity(Class<? extends ImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoardEntity(forProperty("board"), inits.get("board")) : null;
        this.festivalBoard = inits.isInitialized("festivalBoard") ? new QFestivalBoardEntity(forProperty("festivalBoard"), inits.get("festivalBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


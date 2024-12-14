package com.example.board_test.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalCommentEntity is a Querydsl query type for FestivalCommentEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalCommentEntity extends EntityPathBase<FestivalCommentEntity> {

    private static final long serialVersionUID = 1377407871L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestivalCommentEntity festivalCommentEntity = new QFestivalCommentEntity("festivalCommentEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    public final StringPath comment = createString("comment");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final NumberPath<Long> fcId = createNumber("fcId", Long.class);

    public final QFestivalBoardEntity festivalBoard;

    public final StringPath image = createString("image");

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath siren = createString("siren");

    public QFestivalCommentEntity(String variable) {
        this(FestivalCommentEntity.class, forVariable(variable), INITS);
    }

    public QFestivalCommentEntity(Path<? extends FestivalCommentEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestivalCommentEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestivalCommentEntity(PathMetadata metadata, PathInits inits) {
        this(FestivalCommentEntity.class, metadata, inits);
    }

    public QFestivalCommentEntity(Class<? extends FestivalCommentEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.festivalBoard = inits.isInitialized("festivalBoard") ? new QFestivalBoardEntity(forProperty("festivalBoard"), inits.get("festivalBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


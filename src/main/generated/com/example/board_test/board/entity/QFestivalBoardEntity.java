package com.example.board_test.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFestivalBoardEntity is a Querydsl query type for FestivalBoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFestivalBoardEntity extends EntityPathBase<FestivalBoardEntity> {

    private static final long serialVersionUID = 1208183808L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFestivalBoardEntity festivalBoardEntity = new QFestivalBoardEntity("festivalBoardEntity");

    public final QTimeEntity _super = new QTimeEntity(this);

    public final NumberPath<Integer> category = createNumber("category", Integer.class);

    public final StringPath content = createString("content");

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final NumberPath<Long> f_id = createNumber("f_id", Long.class);

    public final ListPath<FestivalCommentEntity, QFestivalCommentEntity> festivalComments = this.<FestivalCommentEntity, QFestivalCommentEntity>createList("festivalComments", FestivalCommentEntity.class, QFestivalCommentEntity.class, PathInits.DIRECT2);

    public final StringPath image = createString("image");

    public final NumberPath<Integer> likeCnt = createNumber("likeCnt", Integer.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final StringPath title = createString("title");

    public final NumberPath<Integer> viewCnt = createNumber("viewCnt", Integer.class);

    public QFestivalBoardEntity(String variable) {
        this(FestivalBoardEntity.class, forVariable(variable), INITS);
    }

    public QFestivalBoardEntity(Path<? extends FestivalBoardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFestivalBoardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFestivalBoardEntity(PathMetadata metadata, PathInits inits) {
        this(FestivalBoardEntity.class, metadata, inits);
    }

    public QFestivalBoardEntity(Class<? extends FestivalBoardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


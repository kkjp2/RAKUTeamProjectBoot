package com.example.board_test.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QFavoriteEntity is a Querydsl query type for FavoriteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFavoriteEntity extends EntityPathBase<FavoriteEntity> {

    private static final long serialVersionUID = -1298959088L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QFavoriteEntity favoriteEntity = new QFavoriteEntity("favoriteEntity");

    public final QBoardEntity board;

    public final NumberPath<Long> fav_id = createNumber("fav_id", Long.class);

    public final QFestivalBoardEntity festivalBoard;

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public QFavoriteEntity(String variable) {
        this(FavoriteEntity.class, forVariable(variable), INITS);
    }

    public QFavoriteEntity(Path<? extends FavoriteEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QFavoriteEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QFavoriteEntity(PathMetadata metadata, PathInits inits) {
        this(FavoriteEntity.class, metadata, inits);
    }

    public QFavoriteEntity(Class<? extends FavoriteEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoardEntity(forProperty("board"), inits.get("board")) : null;
        this.festivalBoard = inits.isInitialized("festivalBoard") ? new QFestivalBoardEntity(forProperty("festivalBoard"), inits.get("festivalBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


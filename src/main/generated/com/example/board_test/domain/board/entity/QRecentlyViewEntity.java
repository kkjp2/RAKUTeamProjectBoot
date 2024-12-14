package com.example.board_test.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRecentlyViewEntity is a Querydsl query type for RecentlyViewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRecentlyViewEntity extends EntityPathBase<RecentlyViewEntity> {

    private static final long serialVersionUID = -553641125L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRecentlyViewEntity recentlyViewEntity = new QRecentlyViewEntity("recentlyViewEntity");

    public final QBoardEntity board;

    public final QFestivalBoardEntity festivalBoard;

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final NumberPath<Long> rId = createNumber("rId", Long.class);

    public QRecentlyViewEntity(String variable) {
        this(RecentlyViewEntity.class, forVariable(variable), INITS);
    }

    public QRecentlyViewEntity(Path<? extends RecentlyViewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRecentlyViewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRecentlyViewEntity(PathMetadata metadata, PathInits inits) {
        this(RecentlyViewEntity.class, metadata, inits);
    }

    public QRecentlyViewEntity(Class<? extends RecentlyViewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.board = inits.isInitialized("board") ? new QBoardEntity(forProperty("board"), inits.get("board")) : null;
        this.festivalBoard = inits.isInitialized("festivalBoard") ? new QFestivalBoardEntity(forProperty("festivalBoard"), inits.get("festivalBoard")) : null;
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


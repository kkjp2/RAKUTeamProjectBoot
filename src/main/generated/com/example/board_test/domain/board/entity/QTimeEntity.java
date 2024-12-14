package com.example.board_test.domain.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeEntity is a Querydsl query type for TimeEntity
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QTimeEntity extends EntityPathBase<TimeEntity> {

    private static final long serialVersionUID = 315825467L;

    public static final QTimeEntity timeEntity = new QTimeEntity("timeEntity");

    public final StringPath createdDate = createString("createdDate");

    public final StringPath modifiedDate = createString("modifiedDate");

    public QTimeEntity(String variable) {
        super(TimeEntity.class, forVariable(variable));
    }

    public QTimeEntity(Path<? extends TimeEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeEntity(PathMetadata metadata) {
        super(TimeEntity.class, metadata);
    }

}


package com.example.board_test.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QHashTagEntity is a Querydsl query type for HashTagEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QHashTagEntity extends EntityPathBase<HashTagEntity> {

    private static final long serialVersionUID = -1464872930L;

    public static final QHashTagEntity hashTagEntity = new QHashTagEntity("hashTagEntity");

    public final NumberPath<Integer> count = createNumber("count", Integer.class);

    public final NumberPath<Long> hashId = createNumber("hashId", Long.class);

    public final StringPath keyword = createString("keyword");

    public QHashTagEntity(String variable) {
        super(HashTagEntity.class, forVariable(variable));
    }

    public QHashTagEntity(Path<? extends HashTagEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHashTagEntity(PathMetadata metadata) {
        super(HashTagEntity.class, metadata);
    }

}


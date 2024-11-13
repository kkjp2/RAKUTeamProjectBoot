package com.example.board_test.board.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QTestSample is a Querydsl query type for TestSample
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QTestSample extends EntityPathBase<TestSample> {

    private static final long serialVersionUID = -1875455539L;

    public static final QTestSample testSample = new QTestSample("testSample");

    public final StringPath content = createString("content");

    public final NumberPath<Long> n_id = createNumber("n_id", Long.class);

    public final StringPath title = createString("title");

    public QTestSample(String variable) {
        super(TestSample.class, forVariable(variable));
    }

    public QTestSample(Path<? extends TestSample> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTestSample(PathMetadata metadata) {
        super(TestSample.class, metadata);
    }

}


package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalReviewEntity is a Querydsl query type for RentalReviewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalReviewEntity extends EntityPathBase<RentalReviewEntity> {

    private static final long serialVersionUID = -1690440904L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalReviewEntity rentalReviewEntity = new QRentalReviewEntity("rentalReviewEntity");

    public final com.example.board_test.domain.board.entity.QTimeEntity _super = new com.example.board_test.domain.board.entity.QTimeEntity(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Integer> rating = createNumber("rating", Integer.class);

    public final QRentalBoardEntity rentalBoard;

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QRentalReviewEntity(String variable) {
        this(RentalReviewEntity.class, forVariable(variable), INITS);
    }

    public QRentalReviewEntity(Path<? extends RentalReviewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalReviewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalReviewEntity(PathMetadata metadata, PathInits inits) {
        this(RentalReviewEntity.class, metadata, inits);
    }

    public QRentalReviewEntity(Class<? extends RentalReviewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


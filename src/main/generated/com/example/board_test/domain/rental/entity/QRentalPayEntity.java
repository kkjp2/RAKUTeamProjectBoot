package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalPayEntity is a Querydsl query type for RentalPayEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalPayEntity extends EntityPathBase<RentalPayEntity> {

    private static final long serialVersionUID = 2114613742L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalPayEntity rentalPayEntity = new QRentalPayEntity("rentalPayEntity");

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final NumberPath<Integer> pay = createNumber("pay", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> payDate = createDateTime("payDate", java.time.LocalDateTime.class);

    public final StringPath payType = createString("payType");

    public final QRentalBoardEntity rentalBoard;

    public final NumberPath<Long> rPayId = createNumber("rPayId", Long.class);

    public QRentalPayEntity(String variable) {
        this(RentalPayEntity.class, forVariable(variable), INITS);
    }

    public QRentalPayEntity(Path<? extends RentalPayEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalPayEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalPayEntity(PathMetadata metadata, PathInits inits) {
        this(RentalPayEntity.class, metadata, inits);
    }

    public QRentalPayEntity(Class<? extends RentalPayEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


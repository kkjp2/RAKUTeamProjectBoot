package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalReservationEntity is a Querydsl query type for RentalReservationEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalReservationEntity extends EntityPathBase<RentalReservationEntity> {

    private static final long serialVersionUID = 1215178514L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalReservationEntity rentalReservationEntity = new QRentalReservationEntity("rentalReservationEntity");

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final QRentalBoardEntity rentalBoard;

    public final StringPath resAddress = createString("resAddress");

    public final DateTimePath<java.time.LocalDateTime> resDate = createDateTime("resDate", java.time.LocalDateTime.class);

    public final StringPath resHomeType = createString("resHomeType");

    public final NumberPath<Long> resId = createNumber("resId", Long.class);

    public final NumberPath<Integer> resPrice = createNumber("resPrice", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QRentalReservationEntity(String variable) {
        this(RentalReservationEntity.class, forVariable(variable), INITS);
    }

    public QRentalReservationEntity(Path<? extends RentalReservationEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalReservationEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalReservationEntity(PathMetadata metadata, PathInits inits) {
        this(RentalReservationEntity.class, metadata, inits);
    }

    public QRentalReservationEntity(Class<? extends RentalReservationEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


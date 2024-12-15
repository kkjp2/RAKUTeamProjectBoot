package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalRecentlyViewEntity is a Querydsl query type for RentalRecentlyViewEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalRecentlyViewEntity extends EntityPathBase<RentalRecentlyViewEntity> {

    private static final long serialVersionUID = 1368250957L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalRecentlyViewEntity rentalRecentlyViewEntity = new QRentalRecentlyViewEntity("rentalRecentlyViewEntity");

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final QRentalBoardEntity rentalBoard;

    public final NumberPath<Long> rentalRId = createNumber("rentalRId", Long.class);

    public QRentalRecentlyViewEntity(String variable) {
        this(RentalRecentlyViewEntity.class, forVariable(variable), INITS);
    }

    public QRentalRecentlyViewEntity(Path<? extends RentalRecentlyViewEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalRecentlyViewEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalRecentlyViewEntity(PathMetadata metadata, PathInits inits) {
        this(RentalRecentlyViewEntity.class, metadata, inits);
    }

    public QRentalRecentlyViewEntity(Class<? extends RentalRecentlyViewEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


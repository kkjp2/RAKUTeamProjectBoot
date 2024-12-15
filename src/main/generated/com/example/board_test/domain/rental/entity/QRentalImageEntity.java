package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalImageEntity is a Querydsl query type for RentalImageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalImageEntity extends EntityPathBase<RentalImageEntity> {

    private static final long serialVersionUID = 1071518625L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalImageEntity rentalImageEntity = new QRentalImageEntity("rentalImageEntity");

    public final com.example.board_test.domain.board.entity.QTimeEntity _super = new com.example.board_test.domain.board.entity.QTimeEntity(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final QRentalBoardEntity rentalBoard;

    public final StringPath rentalImage = createString("rentalImage");

    public final NumberPath<Long> rImgId = createNumber("rImgId", Long.class);

    public QRentalImageEntity(String variable) {
        this(RentalImageEntity.class, forVariable(variable), INITS);
    }

    public QRentalImageEntity(Path<? extends RentalImageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalImageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalImageEntity(PathMetadata metadata, PathInits inits) {
        this(RentalImageEntity.class, metadata, inits);
    }

    public QRentalImageEntity(Class<? extends RentalImageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


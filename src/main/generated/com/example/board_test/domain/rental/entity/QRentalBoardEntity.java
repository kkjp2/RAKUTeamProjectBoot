package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalBoardEntity is a Querydsl query type for RentalBoardEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalBoardEntity extends EntityPathBase<RentalBoardEntity> {

    private static final long serialVersionUID = 1475790060L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalBoardEntity rentalBoardEntity = new QRentalBoardEntity("rentalBoardEntity");

    public final com.example.board_test.domain.board.entity.QTimeEntity _super = new com.example.board_test.domain.board.entity.QTimeEntity(this);

    //inherited
    public final StringPath createdDate = _super.createdDate;

    public final DateTimePath<java.time.LocalDateTime> endDate = createDateTime("endDate", java.time.LocalDateTime.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    //inherited
    public final StringPath modifiedDate = _super.modifiedDate;

    public final NumberPath<Long> rbId = createNumber("rbId", Long.class);

    public final StringPath rentalAddress = createString("rentalAddress");

    public final NumberPath<Integer> rentalCategory = createNumber("rentalCategory", Integer.class);

    public final StringPath rentalContent = createString("rentalContent");

    public final StringPath rentalHomeType = createString("rentalHomeType");

    public final StringPath rentalImage = createString("rentalImage");

    public final NumberPath<Integer> rentalPrice = createNumber("rentalPrice", Integer.class);

    public final BooleanPath rentalSave = createBoolean("rentalSave");

    public final StringPath rentalTitle = createString("rentalTitle");

    public final NumberPath<Integer> rentalViewCnt = createNumber("rentalViewCnt", Integer.class);

    public final DateTimePath<java.time.LocalDateTime> startDate = createDateTime("startDate", java.time.LocalDateTime.class);

    public QRentalBoardEntity(String variable) {
        this(RentalBoardEntity.class, forVariable(variable), INITS);
    }

    public QRentalBoardEntity(Path<? extends RentalBoardEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalBoardEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalBoardEntity(PathMetadata metadata, PathInits inits) {
        this(RentalBoardEntity.class, metadata, inits);
    }

    public QRentalBoardEntity(Class<? extends RentalBoardEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
    }

}


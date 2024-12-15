package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalFavoriteEntity is a Querydsl query type for RentalFavoriteEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalFavoriteEntity extends EntityPathBase<RentalFavoriteEntity> {

    private static final long serialVersionUID = 581498684L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalFavoriteEntity rentalFavoriteEntity = new QRentalFavoriteEntity("rentalFavoriteEntity");

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final QRentalBoardEntity rentalBoard;

    public final NumberPath<Long> rentalFavId = createNumber("rentalFavId", Long.class);

    public QRentalFavoriteEntity(String variable) {
        this(RentalFavoriteEntity.class, forVariable(variable), INITS);
    }

    public QRentalFavoriteEntity(Path<? extends RentalFavoriteEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalFavoriteEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalFavoriteEntity(PathMetadata metadata, PathInits inits) {
        this(RentalFavoriteEntity.class, metadata, inits);
    }

    public QRentalFavoriteEntity(Class<? extends RentalFavoriteEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.rentalBoard = inits.isInitialized("rentalBoard") ? new QRentalBoardEntity(forProperty("rentalBoard"), inits.get("rentalBoard")) : null;
    }

}


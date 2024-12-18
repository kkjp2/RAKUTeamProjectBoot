package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalChatReadEntity is a Querydsl query type for RentalChatReadEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalChatReadEntity extends EntityPathBase<RentalChatReadEntity> {

    private static final long serialVersionUID = -2069019346L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalChatReadEntity rentalChatReadEntity = new QRentalChatReadEntity("rentalChatReadEntity");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity member;

    public final QRentalChatMessageEntity message;

    public final DateTimePath<java.time.LocalDateTime> readTime = createDateTime("readTime", java.time.LocalDateTime.class);

    public QRentalChatReadEntity(String variable) {
        this(RentalChatReadEntity.class, forVariable(variable), INITS);
    }

    public QRentalChatReadEntity(Path<? extends RentalChatReadEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalChatReadEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalChatReadEntity(PathMetadata metadata, PathInits inits) {
        this(RentalChatReadEntity.class, metadata, inits);
    }

    public QRentalChatReadEntity(Class<? extends RentalChatReadEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("member")) : null;
        this.message = inits.isInitialized("message") ? new QRentalChatMessageEntity(forProperty("message"), inits.get("message")) : null;
    }

}


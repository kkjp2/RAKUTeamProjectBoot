package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalChatRoomEntity is a Querydsl query type for RentalChatRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalChatRoomEntity extends EntityPathBase<RentalChatRoomEntity> {

    private static final long serialVersionUID = -641588045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalChatRoomEntity rentalChatRoomEntity = new QRentalChatRoomEntity("rentalChatRoomEntity");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final ListPath<RentalChatMessageEntity, QRentalChatMessageEntity> messages = this.<RentalChatMessageEntity, QRentalChatMessageEntity>createList("messages", RentalChatMessageEntity.class, QRentalChatMessageEntity.class, PathInits.DIRECT2);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updated = createDateTime("updated", java.time.LocalDateTime.class);

    public final com.example.board_test.domain.member.entity.QMemberEntity userA;

    public final com.example.board_test.domain.member.entity.QMemberEntity userB;

    public QRentalChatRoomEntity(String variable) {
        this(RentalChatRoomEntity.class, forVariable(variable), INITS);
    }

    public QRentalChatRoomEntity(Path<? extends RentalChatRoomEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalChatRoomEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalChatRoomEntity(PathMetadata metadata, PathInits inits) {
        this(RentalChatRoomEntity.class, metadata, inits);
    }

    public QRentalChatRoomEntity(Class<? extends RentalChatRoomEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.userA = inits.isInitialized("userA") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("userA")) : null;
        this.userB = inits.isInitialized("userB") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("userB")) : null;
    }

}


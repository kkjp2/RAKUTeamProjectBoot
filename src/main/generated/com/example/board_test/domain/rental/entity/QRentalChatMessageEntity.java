package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRentalChatMessageEntity is a Querydsl query type for RentalChatMessageEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalChatMessageEntity extends EntityPathBase<RentalChatMessageEntity> {

    private static final long serialVersionUID = -393521515L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRentalChatMessageEntity rentalChatMessageEntity = new QRentalChatMessageEntity("rentalChatMessageEntity");

    public final QRentalChatRoomEntity chatRoom;

    public final StringPath msgContent = createString("msgContent");

    public final NumberPath<Long> msgId = createNumber("msgId", Long.class);

    public final StringPath msgImage = createString("msgImage");

    public final DateTimePath<java.time.LocalDateTime> msgSendTime = createDateTime("msgSendTime", java.time.LocalDateTime.class);

    public final EnumPath<RentalChatMessageEntity.MsgType> msgType = createEnum("msgType", RentalChatMessageEntity.MsgType.class);

    public final BooleanPath readCheck = createBoolean("readCheck");

    public final com.example.board_test.domain.member.entity.QMemberEntity sender;

    public QRentalChatMessageEntity(String variable) {
        this(RentalChatMessageEntity.class, forVariable(variable), INITS);
    }

    public QRentalChatMessageEntity(Path<? extends RentalChatMessageEntity> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRentalChatMessageEntity(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRentalChatMessageEntity(PathMetadata metadata, PathInits inits) {
        this(RentalChatMessageEntity.class, metadata, inits);
    }

    public QRentalChatMessageEntity(Class<? extends RentalChatMessageEntity> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatRoom = inits.isInitialized("chatRoom") ? new QRentalChatRoomEntity(forProperty("chatRoom"), inits.get("chatRoom")) : null;
        this.sender = inits.isInitialized("sender") ? new com.example.board_test.domain.member.entity.QMemberEntity(forProperty("sender")) : null;
    }

}


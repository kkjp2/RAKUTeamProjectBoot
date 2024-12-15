package com.example.board_test.domain.rental.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QRentalChatRoomEntity is a Querydsl query type for RentalChatRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRentalChatRoomEntity extends EntityPathBase<RentalChatRoomEntity> {

    private static final long serialVersionUID = -641588045L;

    public static final QRentalChatRoomEntity rentalChatRoomEntity = new QRentalChatRoomEntity("rentalChatRoomEntity");

    public final DateTimePath<java.time.LocalDateTime> created = createDateTime("created", java.time.LocalDateTime.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final DateTimePath<java.time.LocalDateTime> updated = createDateTime("updated", java.time.LocalDateTime.class);

    public QRentalChatRoomEntity(String variable) {
        super(RentalChatRoomEntity.class, forVariable(variable));
    }

    public QRentalChatRoomEntity(Path<? extends RentalChatRoomEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRentalChatRoomEntity(PathMetadata metadata) {
        super(RentalChatRoomEntity.class, metadata);
    }

}


package com.example.board_test.domain.member.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QMemberEntity is a Querydsl query type for MemberEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMemberEntity extends EntityPathBase<MemberEntity> {

    private static final long serialVersionUID = 1816143596L;

    public static final QMemberEntity memberEntity = new QMemberEntity("memberEntity");

    public final DatePath<java.time.LocalDate> accessdate = createDate("accessdate", java.time.LocalDate.class);

    public final NumberPath<Integer> address = createNumber("address", Integer.class);

    public final NumberPath<Integer> alarm = createNumber("alarm", Integer.class);

    public final StringPath bookmark = createString("bookmark");

    public final DatePath<java.time.LocalDate> borndate = createDate("borndate", java.time.LocalDate.class);

    public final StringPath id = createString("id");

    public final StringPath nick = createString("nick");

    public final StringPath pwd = createString("pwd");

    public final StringPath recent = createString("recent");

    public final EnumPath<com.example.board_test.domain.member.entity.enums.MemberRole> role = createEnum("role", com.example.board_test.domain.member.entity.enums.MemberRole.class);

    public final NumberPath<Long> userKey = createNumber("userKey", Long.class);

    public QMemberEntity(String variable) {
        super(MemberEntity.class, forVariable(variable));
    }

    public QMemberEntity(Path<? extends MemberEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMemberEntity(PathMetadata metadata) {
        super(MemberEntity.class, metadata);
    }

}


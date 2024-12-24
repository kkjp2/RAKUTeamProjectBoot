package rakuproject.raku.domain.board.entity;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class TimeEntity {

    @CreatedDate
    @Column(updatable = false , nullable = false)
    private String createdDate;

    @LastModifiedDate
    @Column(nullable = false)
    private String modifiedDate;

    @PrePersist
    public void onPrePersist()
    {
        this.createdDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd"));
        this.modifiedDate=this.createdDate;
    }

    @PreUpdate
    public void onPreUpdate()
    {
        this.modifiedDate=LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
    }
}

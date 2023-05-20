package com.example.challenge2.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    @Getter
    @Setter
    @CreatedDate
    protected LocalDateTime createdAt;

    @Getter
    @Setter
    @LastModifiedDate
    protected LocalDateTime updatedAt;

    @Getter
    @Setter
    @Column
    protected LocalDateTime deletedAt;

    public void refresh() {
        updatedAt = LocalDateTime.now();
    }

    public void delete() {
        deletedAt = LocalDateTime.now();
    }
}

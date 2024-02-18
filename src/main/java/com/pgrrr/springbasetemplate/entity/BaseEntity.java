package com.pgrrr.springbasetemplate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @PrePersist
    protected void onRegister() {
        regDate = LocalDateTime.now();
        modDate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onModify() {
        modDate = LocalDateTime.now();
    }

}

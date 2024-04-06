package com.saidul.Splitwise.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String createdAt;

    @PrePersist
    public void prePersist(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = formatDate(now);
    }

    private String formatDate(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MM yyyy");
        return dateTime.format(formatter);
    }
}
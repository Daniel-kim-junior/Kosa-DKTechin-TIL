package com.example.springnews.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class News {
    @Id
    @GeneratedValue
    @Column(name = "news_id")
    private int id;

    private String writer;

    private String title;

    private String content;

    @Column(name = "write_date")
    @CreationTimestamp
    private LocalDateTime writeDate;

    private int cnt;

}

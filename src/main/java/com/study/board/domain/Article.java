package com.study.board.domain;


import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class Article {

    private Long id;
    private String title;
    private String content;
    private String hashTag;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;

}

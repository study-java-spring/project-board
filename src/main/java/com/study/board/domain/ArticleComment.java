package com.study.board.domain;

import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
public class ArticleComment {

    private Long id;
    private Article article;
    private String content;

    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime modifiedAt;
    private String modifiedBy;
}

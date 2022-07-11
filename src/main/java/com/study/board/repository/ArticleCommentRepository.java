package com.study.board.repository;

import com.study.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}

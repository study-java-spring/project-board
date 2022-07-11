package com.study.board.repository;

import com.study.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

interface ArticleRepository extends JpaRepository<Article, Long> {
}

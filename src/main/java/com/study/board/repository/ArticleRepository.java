package com.study.board.repository;

import com.study.board.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface ArticleRepository extends JpaRepository<Article, Long> {
}

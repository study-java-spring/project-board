package com.study.board.repository;

import com.study.board.domain.ArticleComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
interface ArticleCommentRepository extends JpaRepository<ArticleComment, Long> {
}

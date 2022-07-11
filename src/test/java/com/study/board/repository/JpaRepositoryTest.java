package com.study.board.repository;

import com.study.board.config.JpaConfig;
import com.study.board.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("testdb")
@DisplayName("JPA 연결 테스트")
@Import(JpaConfig.class)
@DataJpaTest
class JpaRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpaRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository
    ) {
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }

    @DisplayName("Select 테스트")
    @Test
    void givenTestData_whenSelecting_thenWorksFine() {

        // Given

        // When
        List<Article> articles = articleRepository.findAll();

        // Then
        assertThat(articles)
                .isNotNull()
                .hasSize(5);

    }


    @DisplayName("Insert 테스트")
    @Test
    void givenTestData_whenInserting_thenWorksFine() {

        // Given
        long previousCount = articleRepository.count();
        Article article = Article.of("new article", "new Content", "#spring");

        // When
        Article savedArticle = articleRepository.save(article);
        // Then

        assertThat(articleRepository.count()).isEqualTo(previousCount + 1);

    }

    @DisplayName("Update 테스트")
    @Test
    void givenTestData_whenUpdating_thenWorksFine() {

      // Given
        Article article = articleRepository.findById(1L).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));
        String updateHashTag = "#Springboot";
        article.setHashTag(updateHashTag);

      // When
        Article savedArticle = articleRepository.saveAndFlush(article);

      // Then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashTag", updateHashTag);
    }

    @DisplayName("Delete 테스트")
    @Test
    void givenTestData_whenDeleting_thenWorksFine() {

        // Given
        Article article = articleRepository.findById(1L).orElseThrow(() -> new RuntimeException("아이디가 존재하지 않습니다."));
        long previousArticleCount = articleRepository.count();
        long previousArticleCommentCount = articleCommentRepository.count();
        int deletedCommentsSize = article.getArticleComments().size();

        // When
        articleRepository.delete(article);

        // Then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount - 1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleCommentCount - deletedCommentsSize);
    }

}
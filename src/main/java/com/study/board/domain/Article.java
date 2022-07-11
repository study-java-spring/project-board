package com.study.board.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashTag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Column(nullable = false, length = 500) private String title;
    @Setter @Column(nullable = false, length = 10000) private String content;

    @Setter private String hashTag;

    @ToString.Exclude //순환참조를 발생시킬 수 있어서 제외시킨다.
    @OrderBy("id")
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @CreatedDate @Column(nullable = false) private LocalDateTime createdAt;
    @CreatedBy @Column(nullable = false, length = 100) private String createdBy;
    @LastModifiedDate @Column(nullable = false) private LocalDateTime modifiedAt;
    @LastModifiedBy @Column(nullable = false, length = 100) private String modifiedBy;

    private Article(String title, String content, String hashTag) {
        this.title = title;
        this.content = content;
        this.hashTag = hashTag;
    }

    //Factory Method
    public static Article of(String title, String content, String hashTag) {
        return new Article(title, content, hashTag);
    }

    //equals와 hashCode는 항상 함께 override되어야 한다.

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return id != null && id.equals(article.id); //영속화 되지 않은 엔티티는 다른값으로 취급한다.
    }

    //해당 메모리 주소값을 반환한다.
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

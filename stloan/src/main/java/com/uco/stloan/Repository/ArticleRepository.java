package com.uco.stloan.Repository;

import com.uco.stloan.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {
    @Query(value = "SELECT * FROM ARTICLES WHERE ref=?",nativeQuery = true)
    public Article findByRef(String ref);

}

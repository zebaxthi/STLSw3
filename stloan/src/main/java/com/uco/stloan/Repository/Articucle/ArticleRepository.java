package com.uco.stloan.Repository.Articucle;

import com.uco.stloan.model.article.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article,String> {
   /* @Query(value = "SELECT * FROM ARTICLES WHERE ref=?",nativeQuery = true)
    public Articulo findByRef(String ref);

    @Query(value = "DELETE FROM ARTICLES WHERE ref=?;",nativeQuery = true)
    public void deleteByRef(String ref);*/
}

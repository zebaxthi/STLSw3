package com.uco.stloan.Services.Article;


import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Article;
import java.util.List;

public interface ArticleService {

    public List<Article> findAll();
    public Article findById(Long id);
    public Article save(Article article);

    public void deleteById(Long id);

    public boolean partialUpdate( long id, String key, String value) throws NotFoundEx;

}

package com.uco.stloan.Services.Articucle;

import com.uco.stloan.Repository.Articucle.ArticleRepository;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticucleImpl implements ArticleServices {

    private final ArticleRepository articleRepository;
    @Autowired
    public ArticucleImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll ( ) {
        return articleRepository.findAll();
    }

    @Override
    public Article findById (String ref ) {
        return null; //articuloRepository.findByRef(ref);
    }

    @Override
    public Article save (Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void deleteById ( String ref ) {
        //articuloRepository.deleteByRef(ref);
    }

    @Override
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Article> optional = articleRepository.findById(String.valueOf(id));
        if (optional.isPresent()) {
            Article article = optional.get();

            if (key.equalsIgnoreCase("ref")) {
                article.setRef(value);
            }
            if (key.equalsIgnoreCase("name")) {
                article.setName(value);
            }
            if (key.equalsIgnoreCase("quantity")) {
                article.setQuantity(Integer.parseInt(value));
            }

            articleRepository.save(article);
            return true;
        } else {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
    }
}

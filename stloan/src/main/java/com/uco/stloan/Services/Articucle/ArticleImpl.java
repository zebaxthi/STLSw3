package com.uco.stloan.Services.Articucle;

import com.uco.stloan.Repository.ArticleRepository;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Service
public class ArticleImpl implements ArticleServices {

    private final ArticleRepository articleRepository;
    @Autowired
    public ArticleImpl ( ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll ( ) {
        return articleRepository.findAll();
    }

    @Override
    public Article findById (Long id ) {
        return articleRepository.findById(id).orElse(null);
    }

    @Override
    public Article save(Article article) {
        return articleRepository.save(article);
    }




    @Override
    public void deleteById ( Long id ) {
        articleRepository.deleteById(id);
    }



    @Override
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Article> optional = articleRepository.findById(id);
        if (!optional.isPresent()) {
            throw new NotFoundEx("RESOURCE_NOT_FOUND");
        }
        Article article = optional.get();

        Map<String, Consumer<String>> setters = new HashMap<>();
        setters.put("ref", article::setRef);
        setters.put("name", article::setName);
        setters.put("quantity", s -> article.setQuantity(Integer.parseInt(s)));

        if (!setters.containsKey(key)) {
            throw new NotFoundEx("FIELD_NOT_FOUND");
        }
        setters.get(key).accept(value);

        articleRepository.save(article);
        return true;
    }

    /*
    public boolean partialUpdate(long id, String key, String value) throws NotFoundEx {
        Optional<Article> optional = articleRepository.findById(id);
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
    }*/
}

package com.uco.stloan.controller;



import com.uco.stloan.Services.Articucle.ArticleServices;

import com.uco.stloan.dto.ArticleDTO;
import com.uco.stloan.dto.PatchDTO;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/rest/articulos")
public class ArticleController {

    @Autowired
    private ArticleServices articleService;

    @GetMapping
    public ResponseEntity<?> listArticles() {
        List<Article> articles = articleService.findAll();
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ArticleDTO article) {

        Article newArticle = new Article(article.getRef(),article.getName(),article.getQuantity(),article.getStatus());
        return new ResponseEntity<>(articleService.save(newArticle), HttpStatus.CREATED);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) Long id ){
        articleService.deleteById (id);
    }

    @PutMapping
    public ResponseEntity<?> edit(@Valid @RequestBody ArticleDTO article,
                                        @RequestParam(required = true) Long id ){

        Article articleDB = null;
        Article articleCurrent;

        articleDB = articleService.findById(id);
        if(articleDB == null){
            return new ResponseEntity<>(articleService.findById(id), HttpStatus.BAD_REQUEST);
        }
        articleCurrent = new Article(article.getRef(),article.getName(),article.getQuantity(),article.getStatus());

        articleDB.setRef(articleCurrent.getRef());
        articleDB.setName(articleCurrent.getName());
        articleDB.setQuantity(articleCurrent.getQuantity());
        articleDB.setStatus(articleCurrent.getStatus());


        articleDB = articleService.save(articleDB);
        return new ResponseEntity<>(articleDB, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDTO dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = articleService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }



}

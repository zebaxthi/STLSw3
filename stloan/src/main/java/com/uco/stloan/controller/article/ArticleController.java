package com.uco.stloan.controller.article;



import com.uco.stloan.Services.Articucle.ArticleServices;
import com.uco.stloan.dto.PatchDto;
import com.uco.stloan.exception.NotFoundEx;
import com.uco.stloan.exception.NotYetImplementedEx;
import com.uco.stloan.model.article.Article;
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
    public ResponseEntity<Article> create(@Valid @RequestBody Article articles) {
        return new ResponseEntity<>(articleService.save(articles), HttpStatus.OK);
    }

    @DeleteMapping
    public void delete ( @RequestParam(required = true) String ref ){
        articleService.deleteById (ref);
    }

    @PutMapping
    public ResponseEntity<Article> edit(@Valid @RequestBody Article article,
                                        @RequestParam(required = true) String ref ){

        Article articleDB = null;
        Article articleCurrent;

        articleDB = articleService.findById(ref);
        if(articleDB == null){
            return new ResponseEntity<>(articleService.findById(ref), HttpStatus.BAD_REQUEST);
        }
        articleCurrent = new Article(article.getRef(),article.getNombre(),article.getCantidad());


        articleDB.setId(articleCurrent.getId());
        articleDB.setNombre(articleCurrent.getNombre());
        articleDB.setCantidad(articleCurrent.getCantidad());




        articleDB = articleService.save(articleDB);
        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Boolean> updatePartially(@PathVariable(name = "id") int id,
                                                   @RequestBody PatchDto dto) throws NotYetImplementedEx, NotFoundEx {
        // skipping validations for brevity
        if (dto.getOp().equalsIgnoreCase("update")) {
            boolean result = articleService.partialUpdate(id, dto.getKey(), dto.getValue());
            return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
        } else {
            throw new NotYetImplementedEx("NOT_YET_IMPLEMENTED");
        }
    }



}

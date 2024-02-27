package com.example.blogbackend.Service;

import com.example.blogbackend.Modal.Article;
import com.example.blogbackend.Modal.Draft;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ArticleService {
    ResponseEntity<String> GetApiCall();


    ResponseEntity<String> GetLatest(String id);


    ResponseEntity<String> Getvideos();

    Article save(Article article, MultipartFile file) throws IOException;

    Draft DraftSave(Draft article, MultipartFile file) throws IOException;

    List<Article> FindAll();

    void deleteByID(String id);


    Article updateArticle(String id, Article updatedArticle);

    Article FindAllById(String id);


}

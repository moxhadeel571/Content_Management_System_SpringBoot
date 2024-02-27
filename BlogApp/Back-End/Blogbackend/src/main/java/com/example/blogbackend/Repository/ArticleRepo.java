package com.example.blogbackend.Repository;

import com.example.blogbackend.Modal.Article;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository("ArticleRepo")
public interface ArticleRepo extends MongoRepository<Article, String> {
}

package com.example.blogbackend.Service;

import com.example.blogbackend.Modal.Article;
import com.example.blogbackend.Modal.Draft;
import org.bson.types.ObjectId;

import java.util.List;

public interface DraftService {
    List<Draft> FindAll();

    void deleteByID(ObjectId id);
}

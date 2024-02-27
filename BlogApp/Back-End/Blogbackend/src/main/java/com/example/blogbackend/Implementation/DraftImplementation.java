package com.example.blogbackend.Implementation;

import com.example.blogbackend.Modal.Article;
import com.example.blogbackend.Modal.Draft;
import com.example.blogbackend.Repository.DraftRepository;
import com.example.blogbackend.Service.DraftService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DraftImplementation implements DraftService {
    private DraftRepository draftRepository;
@Autowired
    public DraftImplementation(DraftRepository draftRepository) {
        this.draftRepository = draftRepository;
    }

    @Override
    public List<Draft> FindAll() {
        return draftRepository.findAll();
    }

    @Override
    public void deleteByID(ObjectId id) {
         draftRepository.deleteById(id);
    }
}

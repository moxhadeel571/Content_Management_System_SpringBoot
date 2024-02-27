package com.example.blogbackend.Repository;

import com.example.blogbackend.Modal.Draft;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DraftRepository extends MongoRepository<Draft, ObjectId> {
}

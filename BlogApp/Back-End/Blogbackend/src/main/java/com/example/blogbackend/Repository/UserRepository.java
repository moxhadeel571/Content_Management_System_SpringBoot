package com.example.blogbackend.Repository;

import com.example.blogbackend.Modal.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
//    @Query("{ 'email' : ?0 }")
    User findFirstByEmail(@Param("email") String email);

}
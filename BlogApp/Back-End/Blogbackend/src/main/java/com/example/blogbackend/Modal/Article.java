package com.example.blogbackend.Modal;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Data
@Document(collection = "articles")
public class Article {
    private String typeOf;
    @MongoId
    private String id;
    private String title;
    private String description;
    private String body_html;
    private String body_markdown;
    private String readablePublishDate;
    private String slug;
    private String path;
    private String url;
    private int commentsCount;
    private int publicReactionsCount;
    private Long collectionId;
    private LocalDateTime publishedTimestamp;
    private int positiveReactionsCount;
    private byte[] fileImage;
    private String filename;
    private String  filecontent;
    private String socialImage;
    private String canonicalUrl;
    private LocalDateTime createdAt;
    private LocalDateTime editedAt;
    private LocalDateTime crosspostedAt;
    private LocalDateTime publishedAt;
    private LocalDateTime lastCommentAt;
    private int readingTimeMinutes;
    private List<String> tagList;
    private String[] tags;
    @DBRef
    private List<Draft> drafts;





//    private User user;
//    private FlareTag flareTag;
//}
//
//@Data
//class User {
//    private String name;
//    private String username;
//    private String twitterUsername;
//    private String githubUsername;
//    private long userId;
//    private String websiteUrl;
//    private String profileImage;
//    private String profileImage90;
//}
//
//@Data
//class FlareTag {
//    private String name;
//    private String bgColorHex;
//    private String textColorHex;
}

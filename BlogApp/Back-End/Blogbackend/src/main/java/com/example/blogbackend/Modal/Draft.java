package com.example.blogbackend.Modal;

import lombok.*;
import org.bson.types.ObjectId;
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
@Document(collection = "draft")
public class Draft {
    @MongoId
    private ObjectId id;
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
    private String status;
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
}

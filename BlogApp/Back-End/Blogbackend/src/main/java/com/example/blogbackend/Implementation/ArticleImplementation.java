package com.example.blogbackend.Implementation;

import com.example.blogbackend.Modal.Article;
import com.example.blogbackend.Modal.Draft;
import com.example.blogbackend.Repository.ArticleRepo;
import com.example.blogbackend.Repository.DraftRepository;
import com.example.blogbackend.Service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ArticleImplementation implements ArticleService {
    private DraftRepository draftRepository;
    private ArticleRepo articleRepo;
    @Value("${dev.to.api.key}")
    private String apiKey;

    private final String DEV_TO_BASE_URL = "https://dev.to/api/articles";
@Autowired
    public ArticleImplementation(DraftRepository draftRepository, ArticleRepo articleRepo) {
    this.draftRepository = draftRepository;
    this.articleRepo = articleRepo;
    }

    @Override
    public ResponseEntity<String> GetApiCall(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("api-key", apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent", "YourAppName/1.0");
        String requestBody = "{\"parameter\": \"value\"}"; // Replace with your JSON request body if needed
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(DEV_TO_BASE_URL, HttpMethod.GET, requestEntity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    @Override
    public ResponseEntity<String> GetLatest(String id){
         String DEV_TO_BASE_URL_ID = "https://dev.to/api/articles/"+id;

        HttpHeaders headers=new HttpHeaders();
        headers.set("api-key",apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent","YourAppName/1.0");
        String requestBody="{\"parameter\":\"value\"}";
        HttpEntity<String> requestEntity=new HttpEntity<>(requestBody,headers);
        RestTemplate restTemplate=new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(DEV_TO_BASE_URL_ID, HttpMethod.GET, requestEntity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }
    @Override
    public ResponseEntity<String> Getvideos(){
         String DEV_TO_BASE_URL_ID = "https://dev.to/api/videos";

        HttpHeaders headers=new HttpHeaders();
        headers.set("api-key",apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("User-Agent","YourAppName/1.0");
        String requestBody="{\"parameter\":\"value\"}";
        HttpEntity<String> requestEntity=new HttpEntity<>(requestBody,headers);
        RestTemplate restTemplate=new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.exchange(DEV_TO_BASE_URL_ID, HttpMethod.GET, requestEntity, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
        }
    }

    @Override
    public Article save(Article article, MultipartFile file) throws IOException {
        if (article != null && file != null) { // Check if both article and file are not null
            Article newArticle = new Article();
            newArticle.setFilecontent(file.getContentType());
            newArticle.setFilename(file.getOriginalFilename());
            newArticle.setFileImage(file.getBytes());
            // Copy fields from the input article to the new article
            newArticle.setCreatedAt(article.getCreatedAt());
            newArticle.setId(article.getId());
            newArticle.setDescription(article.getDescription());
            newArticle.setTitle(article.getTitle());
            newArticle.setTagList(article.getTagList());
            newArticle.setCreatedAt(LocalDateTime.now());
            newArticle.setBody_html(article.getBody_html());
            newArticle.setBody_markdown(article.getBody_markdown());

            // Save the new article
            Article savedArticle = articleRepo.save(newArticle);

            // Return the saved article
            return savedArticle;
        } else {
            throw new IllegalArgumentException("Input article or file is null");
        }
    }

    @Override
    public Draft DraftSave(Draft article, MultipartFile file) throws IOException {
        if (article != null && file != null) { // Check if both article and file are not null
            Draft newArticle = new Draft();
            newArticle.setFilecontent(file.getContentType());
            newArticle.setFilename(file.getOriginalFilename());
            newArticle.setFileImage(file.getBytes());

            // Copy fields from the input article to the new article
            newArticle.setCreatedAt(article.getCreatedAt());
            newArticle.setId(article.getId());
            newArticle.setDescription(article.getDescription());
            newArticle.setTitle(article.getTitle());
            newArticle.setTagList(article.getTagList());
            newArticle.setCreatedAt(LocalDateTime.now());
            newArticle.setBody_html(article.getBody_html());
            newArticle.setBody_markdown(article.getBody_markdown());

            // Save the new article
            Draft savedArticle = draftRepository.save(newArticle);

            // Return the saved article
            return savedArticle;
        } else {
            throw new IllegalArgumentException("Input article or file is null");
        }
    }

    @Override
    public List<Article> FindAll() {
        return articleRepo.findAll();
    }

    @Override
    public void deleteByID(String id) {
        articleRepo.deleteById(id);
    }

    @Override
    public Article updateArticle(String id, Article updatedArticle) {
        Optional<Article> optionalArticle = articleRepo.findById(id);
        if (optionalArticle.isPresent()) {
            Article existingArticle = optionalArticle.get();
            // Update properties of existing article with those from updatedArticle
            existingArticle.setTitle(updatedArticle.getTitle());
            existingArticle.setDescription(updatedArticle.getDescription());
            existingArticle.setBody_html(updatedArticle.getBody_html());
            existingArticle.setBody_markdown(updatedArticle.getBody_markdown());
            existingArticle.setCreatedAt(LocalDateTime.now());

            // Save the updated article
            return articleRepo.save(existingArticle);
        } else {
            // Handle case where article with given ID is not found
            throw new IllegalArgumentException("Article with ID " + id + " not found");
        }
    }

    @Override
    public Article FindAllById(String id) {
        return articleRepo.findById(id).orElse(null);
    }





}

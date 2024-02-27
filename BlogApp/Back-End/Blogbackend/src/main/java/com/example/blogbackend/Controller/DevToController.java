    package com.example.blogbackend.Controller;
    import com.example.blogbackend.Modal.Article;
    import com.example.blogbackend.Modal.Draft;
    import com.example.blogbackend.Service.ArticleService;
    import com.example.blogbackend.Service.DraftService;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import jakarta.annotation.PostConstruct;
    import org.bson.types.ObjectId;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.core.io.InputStreamResource;
    import org.springframework.http.*;
    import org.springframework.security.access.annotation.Secured;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.web.bind.annotation.*;
    import org.springframework.web.multipart.MultipartFile;

    import java.io.ByteArrayInputStream;
    import java.io.IOException;
    import java.util.List;
    import java.util.Optional;

    @RestController
    @CrossOrigin(origins = "http://localhost:3000") // Replace with your frontend URL
    @RequestMapping("/dev")
    public class DevToController {
            private ArticleService articleService;
            private DraftService draftService;
            @Autowired
            public DevToController(ArticleService articleService, DraftService draftService) {
                this.articleService = articleService;
                this.draftService = draftService;

            }
            @GetMapping("/articles")
            public ResponseEntity<String> getArticles() {
                return articleService.GetApiCall();
            }

            @GetMapping("/{id}")
            public ResponseEntity<String> getLatest(@PathVariable("id") String id) {
                return articleService.GetLatest(id);
            }

        @GetMapping("/videos")
            public ResponseEntity<String> getvideos() {
                return articleService.Getvideos();
            }
        @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
        public ResponseEntity uploadLogo(@RequestParam("file") MultipartFile image, Article article) {
            if (image == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Image is required.");
            }
            try {
                 articleService.save(article, image);
                return ResponseEntity.ok("Form submitted successfully.");
            } catch (IOException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage());
            }
        }

        @PostMapping(value = "/Draft",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
            public ResponseEntity<String> DraftForm(@RequestPart("file") MultipartFile file, Draft article) throws IOException {
                // Save the file content to the article
                articleService.DraftSave(article, file);
                return ResponseEntity.ok("Form saved successfully.");
            }


        @GetMapping(value= "/Blogs")
        public List<Article> getDraft(){
            return articleService.FindAll();
        }
        @GetMapping(value= "/BlogsView/{id}")
        public Article getDraft(@PathVariable String id){
            return articleService.FindAllById(id);
        }
        @DeleteMapping(value="/dev/Delete/{id}")
        public ResponseEntity<?> deleteArticle(@PathVariable String id) {
            try {
                articleService.deleteByID(id);
                return ResponseEntity.ok("Article deleted successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting article: " + e.getMessage());
            }
        }

@DeleteMapping(value="/Delete/{id}")
public void deleteArticle(@PathVariable ObjectId id) {
    draftService.deleteByID(id);
}

        @PostMapping("/EditById/{id}")
        public ResponseEntity<String> updateArticle(@PathVariable("id") String id, @RequestBody Article updatedArticle) {
            try {
                // Use the provided ID to update the article in your database
                articleService.updateArticle(id, updatedArticle);
                return ResponseEntity.ok("Article updated successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error updating article: " + e.getMessage());
            }
        }

        @GetMapping("/GetImage/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") String id) {
        try {
            Optional<Article> productsOptional = Optional.ofNullable(articleService.FindAllById(id));

            if (productsOptional.isPresent()) {
                Article product = productsOptional.get();

                // Fetch the image data for the product
                byte[] fileData = product.getFileImage(); // Assuming you want the first image

                // Set appropriate headers for the image response
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the MediaType as needed
                headers.setContentLength(fileData.length);

                return new ResponseEntity<>(fileData, headers, HttpStatus.OK);
            } else {
                // Handle the case when the product with the given ID is not found
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            // Handle exceptions and return appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    }



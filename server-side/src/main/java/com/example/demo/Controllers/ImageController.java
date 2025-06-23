package com.example.demo.Controllers;

import com.example.demo.Services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@RestController
@RequestMapping({"/image"})
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping({"/upload"})
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = imageService.saveImage(file);
            return ResponseEntity.ok(fileName);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

    @GetMapping({"/{imageName}"})
    public ResponseEntity<Resource> getImage(@PathVariable String imageName) {
        try {
            Resource resource = imageService.loadImage(imageName);
            Path path = resource.getFile().toPath();
            String contentType = imageService.getContentType(path);

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType != null ? contentType : "application/octet-stream"))
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

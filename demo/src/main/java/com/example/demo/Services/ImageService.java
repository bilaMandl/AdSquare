package com.example.demo.Services;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageService {

    private final String uploadDir = "uploads/ads";

    public String saveImage(MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        String originalFilename = file.getOriginalFilename();
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String uuidName = UUID.randomUUID().toString() + extension;

        Path filePath = uploadPath.resolve(uuidName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uuidName;
    }

    public Resource loadImage(String imageName) throws IOException {
        Path imagePath = Paths.get(uploadDir).resolve(imageName);

        if (!Files.exists(imagePath)) {
            throw new FileNotFoundException("Image not found: " + imageName);
        }

        return new UrlResource(imagePath.toUri());
    }

    public String getContentType(Path path) throws IOException {
        return Files.probeContentType(path);
    }
}

package com.teamconnect.service.impl;

import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.model.nosql.File;
import com.teamconnect.repository.couchbase.FileRepository;
import com.teamconnect.service.FileService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    private final FileRepository fileRepository;

    @Value("${file.upload.dir}")
    private String uploadDir;

    public FileServiceImpl(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @PostConstruct
    public void init() {
        try {
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
        } catch (IOException e) {
            throw new RuntimeException("Upload dizini oluşturulamadı!", e);
        }
    }

    @Override
    public File storeFile(MultipartFile file, FilePurposeType filePurposeType, String ownerId) throws IOException {
        validateFile(file, filePurposeType.getValue());

        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = file.getOriginalFilename();
        String fileExtension = getFileExtension(fileName);
        String newFileName = UUID.randomUUID() + fileExtension;

        Path targetLocation = uploadPath.resolve(newFileName).normalize().toAbsolutePath();

        Files.createDirectories(targetLocation.getParent());

        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        File fileEntity = new File();
        fileEntity.setId(UUID.randomUUID().toString());
        fileEntity.setOriginalName(fileName);
        fileEntity.setStoredFileName(newFileName);
        fileEntity.setContentType(file.getContentType());
        fileEntity.setSize(file.getSize());
        fileEntity.setFilePurpose(filePurposeType);
        fileEntity.setOwnerId(ownerId);
        fileEntity.setDeleted(false);

        return fileRepository.save(fileEntity);
    }

    @Override
    public Resource getFileAsResource(String fileId) throws MalformedURLException, FileNotFoundException {
        File file = findFileById(fileId);

        Path filePath = Paths.get(uploadDir).resolve(file.getStoredFileName()).normalize();


        Resource resource = new UrlResource(filePath.toUri());

        if (!resource.exists()) {
            throw new FileNotFoundException("File not found in folder: " + file.getStoredFileName());
        }

        return resource;
    }

    private File findFileById(String fileId) {
        return fileRepository.findById(fileId)
            .orElseThrow(() -> new RuntimeException("File not found."));
    }

    private String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private void validateFile(MultipartFile file, String filePurpose) {
        if (file.isEmpty()) {
            throw new RuntimeException("Dosya boş olamaz.");
        }
        if ("PROFILE".equals(filePurpose) && file.getSize() > 2_000_000) {
            throw new RuntimeException("Profil resmi en fazla 2MB olabilir.");
        }
    }
}

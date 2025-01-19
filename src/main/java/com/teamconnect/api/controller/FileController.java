package com.teamconnect.api.controller;

import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.service.FileService;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/v1/api/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService fileService){
        this.fileService = fileService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<Resource>> getFile(
        @PathVariable String fileId
    ) throws MalformedURLException, FileNotFoundException {
        return ResponseWrapper.ok(fileService.getFileAsResource(fileId));
    }
}

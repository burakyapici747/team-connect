package com.teamconnect.service;

import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.model.nosql.File;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

public interface FileService {
    File storeFile(MultipartFile file, FilePurposeType filePurposeType, String ownerId) throws IOException;
    Resource getFileAsResource(String fileName) throws MalformedURLException, FileNotFoundException;
}

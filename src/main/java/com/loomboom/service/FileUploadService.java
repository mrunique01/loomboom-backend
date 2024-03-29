package com.loomboom.service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String uploadResourse(MultipartFile file, String uploadDir) throws IOException;

    InputStream getResourse(String path, String fileName) throws FileNotFoundException;

    Boolean deleteResourse(String uploadDir,String fileName);
}

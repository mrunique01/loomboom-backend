package com.loomboom.service.impl;

import static com.loomboom.contants.ErrorMessage.SOMETHING_WENT_WRONG;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.loomboom.exceptions.ApiRequestException;
import com.loomboom.service.FileUploadService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FileUploadServiceImpl implements FileUploadService {

    @Override
    public String uploadResourse(MultipartFile file, String uploadDir) throws IOException {

        String fileName = generateUniqueFileName(file);
        Path targetDirectory = Paths.get(uploadDir);

        if (!Files.exists(targetDirectory)) {
            try {
                Files.createDirectories(targetDirectory);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Path targetPath = targetDirectory.resolve(fileName);

        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

        return fileName;
    }

    private String generateUniqueFileName(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        return UUID.randomUUID() + "_" + fileName;
    }

    @Override
    public InputStream getResourse(String path, String fileName) throws FileNotFoundException {

        String filePath = path + File.separator + fileName;
        return new FileInputStream(filePath);
    }

    @Override
    public Boolean deleteResourse(String uploadDir, String fileName) {
        Path path = Paths.get(uploadDir).resolve(fileName);
        try {
            Files.delete(path);
            return true;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new ApiRequestException(SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST);
        }
        // return false;
    }

}

package com.example.backend.db;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.http.HttpServletResponse;

public interface InterfaceFileRepo {

    // upload files function declaration
    ResponseEntity<String> uploadFiles(List<MultipartFile> files, Integer sessionID) throws IOException;

    // download folder function declaration
    public ResponseEntity<StreamingResponseBody> downloadLargeFolder(@PathVariable("foldername") String foldername,
            HttpServletResponse response) throws IOException;

}

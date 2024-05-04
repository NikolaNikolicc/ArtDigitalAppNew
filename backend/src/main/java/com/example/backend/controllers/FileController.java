package com.example.backend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.example.backend.db.RepoFile;
import com.example.backend.db.InterfaceFileRepo;

import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {
    
    // file interface
    InterfaceFileRepo fileRepoInterface = new RepoFile();

    // define a method to upload files
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFiles(@RequestParam("files") List<MultipartFile> files,
            @RequestParam("location") Integer sessionID) throws IOException {
        return this.fileRepoInterface.uploadFiles(files, sessionID);
    }

    @GetMapping("/download/{foldername}")
    public ResponseEntity<StreamingResponseBody> downloadLargeFolder(@PathVariable("foldername") String foldername,
            HttpServletResponse response) throws IOException {
        return this.fileRepoInterface.downloadLargeFolder(foldername, response);
    }

}

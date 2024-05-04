package com.example.backend.db;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import jakarta.servlet.http.HttpServletResponse;

public class RepoFile implements InterfaceFileRepo{
    
    // database interface
    InterfaceDatabaseRepo databaseRepoInterface = new RepoDatabase();
    // define a alocation
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/uploads/";

    // upload files function definition
    public ResponseEntity<String> uploadFiles(@RequestParam("files") List<MultipartFile> files,
            @RequestParam("location") Integer sessionID) throws IOException {
        StringBuilder result = new StringBuilder();

        for (MultipartFile file : files) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = Paths.get(DIRECTORY + sessionID + "/", filename).toAbsolutePath().normalize();
            // Ensure the directory exists
            Files.createDirectories(fileStorage.getParent());
            // Save the file
            Files.copy(file.getInputStream(), fileStorage, StandardCopyOption.REPLACE_EXISTING);
            result.append("File stored successfully: ").append(filename).append("\n");
        }

        // After looping through all files and attempting to save them, return the overall results
        return ResponseEntity.ok(result.toString());
    }

    // download folder function definition
    public ResponseEntity<StreamingResponseBody> downloadLargeFolder(@PathVariable("foldername") String foldername,
            HttpServletResponse response) throws IOException {
        Path folderPath = Paths.get(DIRECTORY).toAbsolutePath().normalize().resolve(foldername);
        if (!Files.exists(folderPath) || !Files.isDirectory(folderPath)) {
            throw new FileNotFoundException(foldername + " was not found on the server or is not a directory");
        }

        response.setContentType("application/zip");
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + foldername + ".zip\"");

        StreamingResponseBody stream = out -> {
            try (ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(out))) {
                // Manually add a text file with specific information
                ZipEntry infoEntry = new ZipEntry("detalji.txt");
                zipOut.putNextEntry(infoEntry);
                String infoContent = databaseRepoInterface.getOrderDetails(Integer.parseInt(foldername)).toString();
                byte[] infoBytes = infoContent.getBytes();
                zipOut.write(infoBytes);
                zipOut.closeEntry();

                // Walk the file tree and add files to the zip
                Files.walkFileTree(folderPath, new SimpleFileVisitor<Path>() {
                    @Override
                    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                        String fileName = file.getFileName().toString();
                        String[] parts = fileName.split("_");
                        if (parts.length < 4)
                            return FileVisitResult.CONTINUE; // Skip files not matching the naming convention

                        String number = parts[0].substring(3); // Assuming the name starts with img and then the number
                        String format = parts[1];
                        String quantity = parts[2];
                        String extension = parts[3];
                        String newFileName = "img" + number + "_" + format + "_" + quantity;
                        // zipOut.putNextEntry(new ZipEntry(folderPath.relativize(file).toString()));
                        ZipEntry zipEntry = new ZipEntry(newFileName + '.' + extension);
                        zipOut.putNextEntry(zipEntry);
                        Files.copy(file, zipOut);
                        zipOut.closeEntry();
                        return FileVisitResult.CONTINUE;
                    }
                });
            } catch (IOException e) {
                // Handle possible IO exceptions here
                e.printStackTrace();
            }
        };

        return ResponseEntity.ok().body(stream);
    }
    
}

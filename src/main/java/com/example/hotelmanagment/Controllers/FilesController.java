package com.example.hotelmanagment.Controllers;

import java.net.URLConnection;
import java.util.List;
import java.util.stream.Collectors;

import com.example.hotelmanagment.DTO.BasicResponseDTO;
import com.example.hotelmanagment.Models.FileInfo;
import com.example.hotelmanagment.Services.FilesStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

@RestController
@CrossOrigin
@RequestMapping("api/upload")
public class FilesController {
    @Autowired
    FilesStorageService storageService;

    @Operation(summary = "Upload file", security = @SecurityRequirement(name = "bearerAuth"))
    @RequestMapping(path = "/upload", method = RequestMethod.POST, consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<BasicResponseDTO<?>> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.save(file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new BasicResponseDTO(true, message, null));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new BasicResponseDTO(false, message, null));
        }
    }
    @Operation(summary = "All files", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles() {
        List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }
    @Operation(summary = "Upload file", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        String mimeType = URLConnection.guessContentTypeFromName(file.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION ).contentType(MediaType.parseMediaType(mimeType)).body(file);
    }
}
//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJzYXRpc2htQGdtYWlsLmNvbSIsImV4cCI6MTY2MjIwNzc4MiwiaWF0IjoxNjYyMTg5NzgyfQ.n13APAfmPcB9obgj-1BAH5HPtoYtx0iXDGEoiQQEUsvVtY-RLPR5gr_X_nOwID5BbIan7WqpI3A7YtlyhElv5w
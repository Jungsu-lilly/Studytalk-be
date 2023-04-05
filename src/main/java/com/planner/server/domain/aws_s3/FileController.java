package com.planner.server.domain.aws_s3;

import com.planner.server.domain.message.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/s3")
public class FileController {
    private final AwsS3Uploader awsS3Uploader;

    @PostMapping("/upload/one")
    public ResponseEntity<?> upload(@RequestParam("file") MultipartFile multipartFile) {
        try {
            String fileUrl = awsS3Uploader.upload(multipartFile, "image");
            Message message = Message.builder()
                    .data(fileUrl)
                    .status(HttpStatus.OK)
                    .message("success")
                    .build();
            return new ResponseEntity<>(message, message.getStatus());
        } catch (IOException e) {
            Message message = Message.builder()
                    .status(HttpStatus.BAD_REQUEST)
                    .message("error")
                    .memo(e.getMessage())
                    .build();
            return new ResponseEntity<>(message, message.getStatus());
        }
    }


}
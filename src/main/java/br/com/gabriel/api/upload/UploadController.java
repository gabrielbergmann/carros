package br.com.gabriel.api.upload;

import br.com.gabriel.domain.upload.FirebaseStorageService;
import com.google.firebase.internal.FirebaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/upload")
public class UploadController {

    @Autowired
    private FirebaseStorageService uploadService;

//    @PostMapping("/upload")
//    public ResponseEntity upload(@RequestParam String fileName, @RequestParam String base64) {
//
//        String s = "Filename: " + fileName + " >> base64 > " + base64;
//
//        return ResponseEntity.ok(s);
//    }

    @PostMapping
    public ResponseEntity upload(@RequestBody UploadInput uploadInput) throws IOException {

        String url = uploadService.upload(uploadInput);

        return ResponseEntity.ok(new UploadOutput(url));
    }
}

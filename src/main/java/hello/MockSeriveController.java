package hello;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class MockSeriveController {

    @RequestMapping("/mock")
    public ResponseEntity mock(@RequestParam(value = "name", defaultValue = "") String name) {

        ClassPathResource jsonFile = new ClassPathResource("test" + name + ".json");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        try {
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentLength(jsonFile.contentLength())
                    .contentType(MediaType.parseMediaType("application/json"))
                    .body(new InputStreamResource(jsonFile.getInputStream()));
        } catch (IOException e) {
            return ResponseEntity
                    .notFound().build();
        }

    }
}
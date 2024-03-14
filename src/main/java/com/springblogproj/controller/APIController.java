package com.springblogproj.controller;

import com.springblogproj.domain.Blog;
import com.springblogproj.external.APIClient;
import com.springblogproj.service.APIService;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class APIController {

    private final APIService apiService;

    @GetMapping("/api/client/test")
    public ResponseEntity<String> getDataFromApi() {
        List<Blog> result = APIClient.fetchDataFromApi();

        if (result == null) {
            return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("Error occurred in fetching data from api");
        }

        return ResponseEntity.status(HttpStatus.OK)
            .header("Content-Type", "application/json")
            .body(apiService.saveData(result));
    }
}

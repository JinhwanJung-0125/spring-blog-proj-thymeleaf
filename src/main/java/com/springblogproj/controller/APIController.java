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

    // 외부 API를 통해 가져온 내용을 DB에 등록하는 API
    @GetMapping("/api/client/test")
    public ResponseEntity<String> getDataFromApi() {
        List<Blog> result = APIClient.fetchDataFromApi();

        if (result == null) {
            // 조회 결과가 없으면 Data Fatch 실패 메시지를 Body에 담아 Response로 보내준다.
            // Status Code: 200, Content-Type: Json
            return ResponseEntity.status(HttpStatus.OK)
                .header("Content-Type", "application/json")
                .body("Error occurred in fetching data from api");
        }

        // 조회 결과가 있으면 이를 DB에 저장하고 그 결과를 Response로 보내준다.
        // Status Code: 200, Content-Type: Json
        return ResponseEntity.status(HttpStatus.OK)
            .header("Content-Type", "application/json")
            .body(apiService.saveData(result));
    }
}

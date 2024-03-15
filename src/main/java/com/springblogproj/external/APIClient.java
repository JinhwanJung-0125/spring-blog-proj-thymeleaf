package com.springblogproj.external;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springblogproj.domain.Blog;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// 외부 API를 사용해 정보를 받아오는 컴포넌트
@Slf4j
@Component
public class APIClient {

    private static final String API_URL = "https://jsonplaceholder.typicode.com/posts";

    // 외부 API에 정보를 받아 이를 Blog 객체 리스트로 Parsing해 반환한다.
    public static List<Blog> fetchDataFromApi() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.getForEntity(API_URL, String.class);
//        ResponseEntity<List> response  = restTemplate.getForEntity(API_URL, List.class);

        if (!response.getStatusCode().is2xxSuccessful()) {
            return null;
        }

        return parsingData(response.getBody());
    }

    // String 형의 데이터를 Blog 객체 리스트로 Parsing하는 메소드
    private static List<Blog> parsingData(String data) {
        ObjectMapper objectMapper = new ObjectMapper();
        List<Blog> parsedData = new ArrayList<>();

        try {
            JsonNode result = objectMapper.readTree(data);
            result.forEach(oneDate -> {
                parsedData.add(Blog.builder()
                    .title(oneDate.get("title").toString())
                    .content(oneDate.get("body").toString())
                    .build());
            });
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }

        return parsedData;
    }
}

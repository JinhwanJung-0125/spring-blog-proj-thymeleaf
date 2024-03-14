package com.springblogproj.service;

import com.springblogproj.domain.Blog;
import com.springblogproj.repository.APIRepository;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class APIService {

    private final APIRepository apiRepository;

    public String saveData(List<Blog> data) {
        List<Blog> result = apiRepository.saveAllAndFlush(data);

        if (result.isEmpty()) {
            return "DB 저장 중 에러가 발생했습니다.";
        }

        return "저장이 완료되었습니다.";
    }
}

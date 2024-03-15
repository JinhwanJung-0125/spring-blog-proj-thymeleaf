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

    // 외부 API로 받은 정보를 DB에 등록하는 Service 메소드
    public String saveData(List<Blog> data) {
        // 모든 내용을 DB에 등록한다.
        // (Bulk Insert)
        List<Blog> result = apiRepository.saveAllAndFlush(data);

        // 만약 하나도 저장되지 않았다면 에러 메시지를 반환한다.
        if (result.isEmpty()) {
            return "DB 저장 중 에러가 발생했습니다.";
        }

        // 저장이 완료되었다면 완료 메시지를 반환한다.
        return "저장이 완료되었습니다.";
    }
}

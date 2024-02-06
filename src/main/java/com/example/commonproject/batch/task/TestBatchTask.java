package com.example.commonproject.batch.task;

import com.example.commonproject.batch.service.BatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestBatchTask {

    private BatchService batchService;

    /**
     * 배치(테스트)
     */
    //@Scheduled(cron = "")
    public void batchWating() {
        log.info("### batch test start ###");
        try {
            //로직 처리
        } catch (Exception e) {
            e.printStackTrace();
            log.error("### batch error ###");
        } finally {
            log.info("### batch finish ###");
        }
    }
}
/**
 * 배치 구현 로직
 * 1. 배치용 테이블 생성 및 해당 테이블 상태값 수시로 업데이트
 * 2. 준비, 진행, 완료 상태값 업데이트
 * 3. 만약 배치가 많으면 서비스코드로 분류해서 배치용 테이블에서 따로 돌리기
 * 4. 배치는 무조건 따로 돌려야함. WEB이랑 같이 돌리면 안됨.
 */
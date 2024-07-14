package com.example.commonproject.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class SchedulingTask {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //현재시간 출력(지금은 스케줄을 켜놓을 이유가 없어서 주석처리)
    //@Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        log.info("Current Time: {}", dateTimeFormatter.format(LocalDateTime.now()));
    }
}
/**
 * 스케줄링 시간 설정 방법
 * fixedRate: 이전 작업이 시작된 시간으로부터 주기를 설정합니다. (예: @Scheduled(fixedRate = 5000)는 5초마다 작업을 실행)
 * fixedDelay: 이전 작업이 완료된 시간으로부터 주기를 설정합니다. (예: @Scheduled(fixedDelay = 5000)는 5초마다 작업을 실행)
 * cron: Cron 표현식을 사용하여 복잡한 스케줄을 설정합니다. (예: @Scheduled(cron = "0 0/1 * 1/1 * ?")는 매 분마다 작업을 실행)
 */

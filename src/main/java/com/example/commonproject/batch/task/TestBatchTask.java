package com.example.commonproject.batch.task;

import com.example.commonproject.batch.domain.BatchStatus;
import com.example.commonproject.batch.dto.BatchRequestDto;
import com.example.commonproject.batch.dto.BatchResponseDto;
import com.example.commonproject.batch.service.BatchService;
import com.example.commonproject.common.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestBatchTask {

    private final BatchService batchService;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    /**
     * 배치 적재(테스트)
     */
    public BatchResponseDto insertTestBatch(BatchRequestDto batchRequestDto) {
        return batchService.BatchInsert(batchRequestDto);
    }

    /**
     * 배치(테스트)
     */
    //@Scheduled(cron = "")
    public void batchTestRun() {
        log.info("### batch test start ###");
        log.info("Current Time: {}", dateTimeFormatter.format(LocalDateTime.now()));

        try {
            //1. 배치 시작
            BatchRequestDto processingDto = BatchRequestDto.batchStatusReq(
                    BatchStatus.PROCESSING, ResponseCode.CODE_0000.getCode(), ResponseCode.CODE_0000.getMsg());
            batchService.BatchUpdate(processingDto);

            //2. 배치 완료
            BatchRequestDto compleateDto = BatchRequestDto.batchStatusReq(
                    BatchStatus.PROCESSING, ResponseCode.CODE_0000.getCode(), ResponseCode.CODE_0000.getMsg());
            batchService.BatchUpdate(compleateDto);
        } catch (Exception e) {
            //3. 배치 에러
            e.printStackTrace();

            BatchRequestDto errorDto = BatchRequestDto.batchStatusReq(
                    BatchStatus.PROCESSING, ResponseCode.CODE_0000.getCode(), ResponseCode.CODE_0000.getMsg());
            batchService.BatchUpdate(errorDto);
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

/**
 * 현재 방식은 스케줄 방식.
 * 추후 Spring Batch도 생성할 예정.
 */
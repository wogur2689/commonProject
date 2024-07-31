package com.example.commonproject.chatting.task;

import com.example.commonproject.chatting.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
@RequiredArgsConstructor
public class ChatRoomTask {

    private final ChatRepository chatRepository;
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //매주 일요일 오후 6시마다 사람이 없는 채팅방 삭제
    //@Scheduled(cron = "0 18 * * 0")
    public void deleteChatRoomTask() {
            log.info("### batch start ###");
            log.info("Current Time: {}", dateTimeFormatter.format(LocalDateTime.now()));

            try {
                //1. 삭제 시작
                chatRepository.deleteChatRoom();
                //2. 삭제 성공
                log.info("chat Room delete Sucess");
            } catch (Exception e) {
                //3. 삭제 에러
                e.printStackTrace();
                log.error("### batch error ###");
            } finally {
                log.info("### batch finish ###");
            }
    }
}

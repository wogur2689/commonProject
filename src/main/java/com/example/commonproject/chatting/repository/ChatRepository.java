package com.example.commonproject.chatting.repository;

import com.example.commonproject.chatting.dto.ChatDto;
import com.example.commonproject.chatting.dto.ChatRoomDto;
import com.example.commonproject.chatting.util.RoomUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@RequiredArgsConstructor
@Repository
@Slf4j
public class ChatRepository {
    private static final String CHAT_ROOM_KEY = "chat:";
    private final RedisTemplate<String, Object> redisTemplate;

    // 채팅방 메세지 조회
    public List<ChatDto> findRoomChatting(String roomId){
        //1. 채팅방 가져오기
        ChatRoomDto chatRoom = findRoomById(roomId);

        //2. 채팅내역 반환
        if(chatRoom != null) {
            log.info("채팅방 정보 : {}", chatRoom);
            return chatRoom.getChatDtoList();
        }
        log.info("채팅방이 없습니다. : " + roomId);
        return null;
    }

    // roomID 기준으로 채팅방 찾기
    public ChatRoomDto findRoomById(String roomId){
        HashOperations<String, String, ChatRoomDto> hashOperations = redisTemplate.opsForHash();
        return hashOperations.get(CHAT_ROOM_KEY + roomId, roomId);
    }

    // roomName 로 채팅방 만들기
    public ChatRoomDto createChatRoom(String roomName){
        //1. roomId 생성
        String roomId = RoomUtil.randomRoomId();
        //2. Redis에 채팅방 정보를 저장하는 코드 추가
        ChatRoomDto chatRoom = ChatRoomDto.builder()
                .roomName(roomName)
                .roomId(roomId)
                .build();

        redisTemplate.opsForHash().put(CHAT_ROOM_KEY + roomId, roomId, chatRoom);

        return chatRoom;
    }

    // 채팅방 유저 리스트에 유저 추가
    public String addUser(String roomId, String userName){
        ChatRoomDto room = findRoomById(roomId);
        String userUUID = UUID.randomUUID().toString();

        //userList 에 추가
        if(room.getUserlist() == null) {
            room.setUserlist(new HashMap<>());
        }
        room.getUserlist().put(userUUID, userName);
        room.setUserCount(room.getUserCount()+1); //채팅방 유저 +1

        // Redis에 채팅방 정보를 업데이트
        redisTemplate.opsForHash().put(CHAT_ROOM_KEY + roomId, roomId, room);

        return userUUID;
    }

    // 채팅방 유저 리스트 삭제
    public void delUser(String roomId, String userUUID){
        ChatRoomDto room = findRoomById(roomId);
        room.getUserlist().remove(userUUID);
        room.setUserCount(room.getUserCount()-1); //채팅방 인원 -1

        // Redis에 채팅방 정보를 업데이트
        redisTemplate.opsForHash().put(CHAT_ROOM_KEY + roomId, roomId, room);
    }

    // 채팅방 userName 조회
    public String getUserName(String roomId, String userUUID){
        ChatRoomDto room = findRoomById(roomId);
        return room.getUserlist().get(userUUID);
    }

    // 채팅방 전체 user 조회
    public ArrayList<String> getUserList(String roomId){
        ArrayList<String> list = new ArrayList<>();

        ChatRoomDto room = findRoomById(roomId);
        log.info(room.toString());
        //유저가 없을 경우
        if (room.getUserCount() == 0L) {
            log.info("해당 채팅방은 유저가 없습니다.");
            return null;
        }
        log.info(String.valueOf(room.getUserlist().size()));

        // hashmap 을 for 문을 돌린 후
        // value 값만 뽑아내서 list 에 저장 후 reutrn
        room.getUserlist().forEach((key, value) -> list.add(value));
        return list;
    }

    //채팅방에 유저가 없을 경우 채팅방 삭제(cron)
    public void deleteChatRoom() {
        HashOperations<String, String, ChatRoomDto> hashOperations = redisTemplate.opsForHash();

        for(int i = 1000; i < 10000; i++) {
            //1. room 정보 추출
            ChatRoomDto room = hashOperations.get(CHAT_ROOM_KEY + i, i);

            //2. 채팅방이 존재하고 사람이 없으면 채팅방 삭제
            if (room != null && room.getUserCount() == 0L) {
                redisTemplate.opsForHash().delete(CHAT_ROOM_KEY + i);
                log.info("roomID : {}, 해당 채팅방은 삭제되었습니다.", i);
            }
        }
    }

    //메세지 저장
    public void saveMsg(ChatDto chatDTO) {
        ChatRoomDto room = findRoomById(chatDTO.getRoomId());

        //처음 채팅일 경우
        if(room.getChatDtoList() == null) {
            room.setChatDtoList(new ArrayList<>());
        }
        room.getChatDtoList().add(chatDTO);

        // Redis에 채팅방 정보를 업데이트
        redisTemplate.opsForHash().put(CHAT_ROOM_KEY + chatDTO.getRoomId(), chatDTO.getRoomId(), room);
    }
}
package com.example.commonproject.chatting.controller;
import com.example.commonproject.chatting.dto.ChatDto;
import com.example.commonproject.chatting.dto.ChatRoomDto;
import com.example.commonproject.chatting.dto.RoomRequestDto;
import com.example.commonproject.chatting.repository.ChatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
public class ChatRoomController {
    @Autowired
    private ChatRepository chatRepository;

    // 해당 채팅방 채팅내역 표시
    @PostMapping("/chat/chatlist")
    public ModelAndView goChatRoom(@RequestBody RoomRequestDto roomRequest, ModelAndView mav){
        //1. 조회
        List<ChatDto> list = chatRepository.findRoomChatting(roomRequest.getRoomId());

        //return
        mav.addObject("list", list);
        mav.setViewName("jsonView");
        return mav;
    }

    // 채팅방 생성
    @PostMapping("/chat/createroom")
    public ModelAndView createRoom(@RequestBody RoomRequestDto roomRequest, ModelAndView mav) {
        //1. 채팅방 생성
        ChatRoomDto room = chatRepository.createChatRoom(roomRequest.getRoomName());
        log.info("CREATE Chat Room {}", room);

        //return
        mav.addObject("roomId", room.getRoomId());
        mav.setViewName("jsonView");
        return mav;
    }

    // 채팅에 참여한 유저 리스트 반환
    @PostMapping("/chat/userlist")
    public ModelAndView userList(@RequestBody RoomRequestDto roomRequest, ModelAndView mav) {
        //1. 조회
        ArrayList<String> userList = chatRepository.getUserList(roomRequest.getRoomId());

        //return
        mav.addObject("userList", userList);
        mav.setViewName("jsonView");
        return mav;
    }
}

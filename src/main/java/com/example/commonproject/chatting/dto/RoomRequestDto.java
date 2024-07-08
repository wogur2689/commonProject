package com.example.commonproject.chatting.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoomRequestDto {
    private String roomId; //방 고유키
    private String roomName; //방이름
}
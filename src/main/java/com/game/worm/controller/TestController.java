package com.game.worm.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {
    @MessageMapping("/test")
    @SendTo("/sub/test")
    public String content(@RequestBody String message){
        return "echo:" + message;    
    }
}

package com.game.worm.controller;

import com.game.worm.service.UserService;
import com.game.worm.service.dto.UserDTO;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;
    @PostMapping(Urls.userSignup)
    public String signup(@ModelAttribute UserDTO userDTO){
        try{
            userService.signup(userDTO.getUserId(), userDTO.getUserPasswd());
        } catch (Exception e){
            log.error("회원가입 실패", e);
        }
        return Urls.index;
    }

}

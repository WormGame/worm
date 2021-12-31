package com.game.worm.controller;

import com.game.worm.service.UserDetailsServiceImpl;
import com.game.worm.service.dto.UserDTO;
import com.game.worm.utils.Messages;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping(Urls.signup)
    public String signup(@ModelAttribute UserDTO userDTO){
        try{
            userDetailsServiceImpl.signup(userDTO.getUserId(), userDTO.getUserPasswd());
        } catch (Exception e){
            log.error(Messages.SIGNUP_FAIL, e);
            return Messages.SIGNUP_FAIL;
        }
        return Urls.index;
    }
}

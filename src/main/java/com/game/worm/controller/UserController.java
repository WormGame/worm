package com.game.worm.controller;

import com.game.worm.service.user.UserDetailsServiceImpl;
import com.game.worm.service.user.vo.UserSignupVO;
import com.game.worm.utils.Messages;
import com.game.worm.utils.Urls;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @PostMapping(Urls.signup)
    public String signup(@Valid @RequestBody UserSignupVO userSignupVO){
        try{
            userDetailsServiceImpl.signup(userSignupVO);
        } catch (Exception e){
            log.error(Messages.SIGNUP_FAIL, e);
            return Messages.SIGNUP_FAIL;
        }
        return Urls.index;
    }
}

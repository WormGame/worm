package com.game.worm.controller;

import com.game.worm.service.exception.ApiException;
import com.game.worm.service.response.ResponseFormat;
import com.game.worm.service.user.UserDetailsServiceImpl;
import com.game.worm.service.user.vo.UserSignupVO;
import com.game.worm.utils.Urls;
import com.game.worm.utils.eErrorInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ResponseFormat> signup(@RequestBody @Valid UserSignupVO userSignupVO){
        try {
            userDetailsServiceImpl.signup(userSignupVO);
        } catch (Exception e){
            throw new ApiException(eErrorInfo.FAIL_SIGNUP, false);
        }
        return null;
    }
}

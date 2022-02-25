package com.game.worm.service.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.worm.etc.define.Messages;
import com.game.worm.etc.define.ParameterName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Getter
@RequiredArgsConstructor
@Valid
public class UserSignupVO {
    @JsonProperty(ParameterName.USER_ID)
    @NotEmpty(message = Messages.BAD_PARAM)
    private final String userId;
    @JsonProperty(ParameterName.USER_PASSWD)
    @NotEmpty(message = Messages.BAD_PARAM)
    private final String userPasswd;
}

package com.game.worm.service.user.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.game.worm.utils.FrontParamName;
import com.game.worm.utils.Messages;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;


@Getter
@RequiredArgsConstructor
@Valid
public class UserSignupVO {
    @JsonProperty(FrontParamName.USER_ID)
    @NotEmpty(message = Messages.BAD_PARAM)
    private final String userId;
    @JsonProperty(FrontParamName.USER_PASSWD)
    @NotEmpty(message = Messages.BAD_PARAM)
    private final String userPasswd;
}

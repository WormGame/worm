package com.game.worm.service.response;

import com.game.worm.service.exception.ApiException;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseFormat {
    private final String message;
    private final boolean success;
    private final JsonObject result;

    public ResponseFormat(ApiException e) {
        this.message = e.getMessage();
        this.result = null;
        this.success = e.isSuccess();
    }
}

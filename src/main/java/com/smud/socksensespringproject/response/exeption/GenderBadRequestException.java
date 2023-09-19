package com.smud.socksensespringproject.response.exeption;

import com.smud.socksensespringproject.response.ResponseCode;
import lombok.Getter;

@Getter
public class GenderBadRequestException extends RuntimeException {

    private ResponseCode responseCode;

    public GenderBadRequestException() {
        this.responseCode = ResponseCode.BAD_REQUEST_GENDER;
    }
}

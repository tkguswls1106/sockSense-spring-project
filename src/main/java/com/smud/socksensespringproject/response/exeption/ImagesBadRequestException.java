package com.smud.socksensespringproject.response.exeption;

import com.smud.socksensespringproject.response.ResponseCode;
import lombok.Getter;

@Getter
public class ImagesBadRequestException extends RuntimeException {

    private ResponseCode responseCode;

    public ImagesBadRequestException() {
        this.responseCode = ResponseCode.BAD_REQUEST_IMAGES;
    }
}

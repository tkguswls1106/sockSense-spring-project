package com.smud.socksensespringproject.response.exeption;

import com.smud.socksensespringproject.response.ResponseCode;
import lombok.Getter;

@Getter
public class ImageCantReadException extends RuntimeException {

    private ResponseCode responseCode;

    public ImageCantReadException() {
        this.responseCode = ResponseCode.CANT_READ_IMAGE;
    }
}

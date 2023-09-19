package com.smud.socksensespringproject.response;

import com.smud.socksensespringproject.response.responseitem.MessageItem;
import com.smud.socksensespringproject.response.responseitem.StatusItem;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseCode {

    POST_AND_GET_PAIR(StatusItem.OK, MessageItem.PAIR_SUCCESS),
    POST_AND_GET_STYLING(StatusItem.OK, MessageItem.STYLING_SUCCESS),

    BAD_REQUEST_GENDER(StatusItem.BAD_REQUEST, MessageItem.BAD_REQUEST_GENDER),
    BAD_REQUEST_IMAGES(StatusItem.BAD_REQUEST, MessageItem.BAD_REQUEST_IMAGES),
    CANT_READ_IMAGE(StatusItem.INTERNAL_SERVER_ERROR, MessageItem.CANT_READ_IMAGE),

    INTERNAL_SERVER_ERROR(StatusItem.INTERNAL_SERVER_ERROR, MessageItem.INTERNAL_SERVER_ERROR),
    ;

    private int httpStatus;
    private String message;
}

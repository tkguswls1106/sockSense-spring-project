package com.smud.socksensespringproject.response.responseitem;

public class MessageItem {

    public static final String PAIR_SUCCESS = "SUCCESS - 양말 짝구분 기능 성공";
    public static final String STYLING_SUCCESS = "SUCCESS - 양말에 어울리는 코디 추천 기능 성공";

    public static final String HEALTHY_SUCCESS = "SUCCESS - Health check 성공";

    public static final String BAD_REQUEST_GENDER = "ERROR - 잘못된 성별 전달 에러";
    public static final String BAD_REQUEST_IMAGES = "ERROR - 잘못된 이미지 전달 에러 (받은 이미지가 2장이 아님)";
    public static final String CANT_READ_IMAGE = "ERROR - 잘못된 이미지 읽기 에러";

    public static final String INTERNAL_SERVER_ERROR = "ERROR - 서버 내부 에러";
}

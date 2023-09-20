package com.smud.socksensespringproject.service;

import com.smud.socksensespringproject.dto.styling.StylingRequestDto;
import com.smud.socksensespringproject.dto.styling.StylingResponseDto;
import org.springframework.web.multipart.MultipartFile;

public interface StylingService {

    StylingResponseDto recommendStyling(MultipartFile imageFile, StylingRequestDto stylingRequestDto);  // 한쪽 양말 이미지 1장과 성별을 전송하면, 코디를 2가지 추천 기능.
}

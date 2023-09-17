package com.smud.socksensespringproject.service;

import com.smud.socksensespringproject.dto.pair.PairResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PairService {

    PairResponseDto compareSocks(List<MultipartFile> imageFiles);  // 양쪽 양말 이미지 2장 전송하면, 양말 짝이 올바른지 반환 기능.
}

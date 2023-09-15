package com.smud.socksensespringproject.service;

import com.smud.socksensespringproject.dto.pair.PairRequestDto;
import com.smud.socksensespringproject.dto.pair.PairResponseDto;

public interface PairService {

    PairResponseDto postTwoSocks(PairRequestDto pairRequestDto);  // 양쪽 양말 이미지 2장 전송하면, 양말 짝이 올바른지 반환 기능.
}

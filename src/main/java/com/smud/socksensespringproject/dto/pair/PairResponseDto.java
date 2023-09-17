package com.smud.socksensespringproject.dto.pair;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PairResponseDto {

    private Integer result;  // 양말 유사도 판정 (1 or 0)

    public PairResponseDto(Integer result) {
        this.result = result;
    }
}

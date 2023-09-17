package com.smud.socksensespringproject.dto.styling;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StylingResponseDto {

    private String top;  // 상의
    private String pants;  // 하의
    private String shoes;  // 신발

    @Builder
    public StylingResponseDto(String top, String pants, String shoes) {
        this.top = top;
        this.pants = pants;
        this.shoes = shoes;
    }
}

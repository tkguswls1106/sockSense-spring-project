package com.smud.socksensespringproject.dto.styling;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class StylingResponseDto {

    private String sockColor;  // 양말 색깔
    private List<Styling> stylings;  // 코디 2가지

    @Builder
    public StylingResponseDto(String sockColor, List<Styling> stylings) {
        this.sockColor = sockColor;
        this.stylings = stylings;
    }
}

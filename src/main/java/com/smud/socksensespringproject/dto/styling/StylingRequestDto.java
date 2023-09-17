package com.smud.socksensespringproject.dto.styling;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class StylingRequestDto {

    private String gender;  // 성별

    public StylingRequestDto(String gender) {
        this.gender = gender;
    }
}

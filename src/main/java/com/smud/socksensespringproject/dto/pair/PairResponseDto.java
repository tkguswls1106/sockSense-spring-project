package com.smud.socksensespringproject.dto.pair;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PairResponseDto {

    private Integer result;

    public PairResponseDto(Integer result) {
        this.result = result;
    }
}

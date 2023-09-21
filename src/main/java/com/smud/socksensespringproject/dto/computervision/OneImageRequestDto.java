package com.smud.socksensespringproject.dto.computervision;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OneImageRequestDto {

    private byte[] bytes;

    public OneImageRequestDto(byte[] bytes) {
        this.bytes = bytes;
    }
}

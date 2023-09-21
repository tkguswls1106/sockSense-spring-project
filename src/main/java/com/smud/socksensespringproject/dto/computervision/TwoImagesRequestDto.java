package com.smud.socksensespringproject.dto.computervision;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TwoImagesRequestDto {

    private byte[] bytes1;
    private byte[] bytes2;

    public TwoImagesRequestDto(byte[] bytes1, byte[] bytes2) {
        this.bytes1 = bytes1;
        this.bytes2 = bytes2;
    }
}

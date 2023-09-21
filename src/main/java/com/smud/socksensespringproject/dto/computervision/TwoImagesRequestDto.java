package com.smud.socksensespringproject.dto.computervision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class TwoImagesRequestDto {

    private MultipartFile image1;
    private MultipartFile image2;

    public TwoImagesRequestDto(MultipartFile image1, MultipartFile image2) {
        this.image1 = image1;
        this.image2 = image2;
    }
}

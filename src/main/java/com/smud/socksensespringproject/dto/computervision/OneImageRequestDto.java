package com.smud.socksensespringproject.dto.computervision;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor
public class OneImageRequestDto {

    private MultipartFile image;

    public OneImageRequestDto(MultipartFile image) {
        this.image = image;
    }
}

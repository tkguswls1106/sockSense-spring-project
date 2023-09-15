package com.smud.socksensespringproject.dto.pair;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@NoArgsConstructor
public class PairRequestDto {

    private List<MultipartFile> imageFiles;  // 이미지파일 리스트

    // 여기는 매개변수가 1개밖에 되지않아, 굳이 빌더를 사용하지않아도 된다.
    public PairRequestDto(List<MultipartFile> imageFiles) {
        this.imageFiles = imageFiles;
    }
}

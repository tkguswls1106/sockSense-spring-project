package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.computervision.SimilarityResponseDto;
import com.smud.socksensespringproject.dto.computervision.TwoImagesRequestDto;
import com.smud.socksensespringproject.dto.pair.PairResponseDto;
import com.smud.socksensespringproject.response.exeption.ImageCantReadException;
import com.smud.socksensespringproject.response.exeption.ImagesBadRequestException;
import com.smud.socksensespringproject.service.PairService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class PairServiceLogic implements PairService {

    private final ComputerVisionServiceLogic computerVisionServiceLogic;


    @Transactional
    @Override
    public PairResponseDto compareSocks(List<MultipartFile> imageFiles) {  // 양쪽 양말 이미지 2장 전송하면, 양말 짝이 올바른지 반환 기능.

        if (imageFiles.size() != 2) {
            throw new ImagesBadRequestException();
        }

        try {
            byte[] bytes1 = imageFiles.get(0).getBytes();
            byte[] bytes2 = imageFiles.get(1).getBytes();
            TwoImagesRequestDto twoImagesRequestDto = new TwoImagesRequestDto(bytes1, bytes2);
            // 여기는 컴퓨터 비전 api 파트 (두 이미지 유사도 측정)
            SimilarityResponseDto similarityResponseDto = computerVisionServiceLogic.similarity(twoImagesRequestDto);
            Double similarityScore = similarityResponseDto.getSimilarity();

            if (0.85 < similarityScore && similarityScore <= 1.0) {  // 유사 O
                return new PairResponseDto(1);
            }
            else {  // 유사 X
                return new PairResponseDto(0);
            }
        } catch (IOException e) {
            throw new ImageCantReadException();
        }
    }

}

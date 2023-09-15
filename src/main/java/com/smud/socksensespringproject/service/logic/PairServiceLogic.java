package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.pair.PairRequestDto;
import com.smud.socksensespringproject.dto.pair.PairResponseDto;
import com.smud.socksensespringproject.service.PairService;
import com.smud.socksensespringproject.util.ImageComparison;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class PairServiceLogic implements PairService {


    @Transactional
    @Override
    public PairResponseDto postTwoSocks(PairRequestDto pairRequestDto) {  // 양쪽 양말 이미지 2장 전송하면, 양말 짝이 올바른지 반환 기능.

        if (pairRequestDto.getImageFiles().size() != 2) {
            throw new RuntimeException("에러 - 받은 이미지가 2장이 아닙니다.");
        }

        try {
            byte[] bytes1 = pairRequestDto.getImageFiles().get(0).getBytes();
            byte[] bytes2 = pairRequestDto.getImageFiles().get(1).getBytes();

            ByteArrayInputStream byteArrayInputStream1 = new ByteArrayInputStream(bytes1);
            ByteArrayInputStream byteArrayInputStream2 = new ByteArrayInputStream(bytes2);

            BufferedImage image1 = ImageIO.read(byteArrayInputStream1);
            BufferedImage image2 = ImageIO.read(byteArrayInputStream2);

            // 여기는 컴퓨터 비전 파트
            Double similarity = ImageComparison.getSimilarity(image1, image2);

            if (50 < similarity && similarity <= 100) {  // 유사 O
                return new PairResponseDto(1);
            }
            else if (0 <= similarity && similarity <= 50) {  // 유사 X
                return new PairResponseDto(0);
            }
            else {  // 유사도 판정 에러
                throw new RuntimeException("에러 - 유사도의 범위가 0~100이 아닙니다");
            }
        } catch (IOException e) {
            throw new RuntimeException("에러 - 이미지 읽기 실패");
        }
    }

}

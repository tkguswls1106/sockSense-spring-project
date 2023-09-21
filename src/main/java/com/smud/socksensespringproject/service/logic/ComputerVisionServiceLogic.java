package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.computervision.OneImageRequestDto;
import com.smud.socksensespringproject.dto.computervision.SimilarityResponseDto;
import com.smud.socksensespringproject.dto.computervision.SockColorResponseDto;
import com.smud.socksensespringproject.dto.computervision.TwoImagesRequestDto;
import com.smud.socksensespringproject.externalapi.ComputerVisionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class ComputerVisionServiceLogic {

    private final ComputerVisionClient computerVisionClient;


    @Transactional
    public SimilarityResponseDto similarity(TwoImagesRequestDto twoImagesRequestDto) {
        return computerVisionClient.similarity(twoImagesRequestDto);
    }

    @Transactional
    public SockColorResponseDto sockColor(OneImageRequestDto oneImageRequestDto) {
        return computerVisionClient.sockColor(oneImageRequestDto);
    }
}

package com.smud.socksensespringproject.controller;

import com.smud.socksensespringproject.dto.pair.PairResponseDto;
import com.smud.socksensespringproject.service.PairService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*")
@Api(tags = {"Pair"})
@RestController
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class PairController {

    private final PairService pairService;


    @ApiOperation(value = "양말 짝 판별", notes = "두 양말의 짝이 올바른지 판별해줍니다.")
    @PostMapping("/pair")
    public PairResponseDto compareSocks(@RequestPart(value="imageFiles") List<MultipartFile> imageFiles) throws IOException {  // 양쪽 양말 이미지 2장 전송하면, 양말 짝이 올바른지 반환
        PairResponseDto pairResponseDto = pairService.compareSocks(imageFiles);
        return pairResponseDto;
    }

}

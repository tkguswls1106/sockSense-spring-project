package com.smud.socksensespringproject.controller;

import com.smud.socksensespringproject.dto.styling.StylingRequestDto;
import com.smud.socksensespringproject.dto.styling.StylingResponseDto;
import com.smud.socksensespringproject.service.StylingService;
import com.smud.socksensespringproject.service.logic.ChatCompletionServiceLogic;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000"}, allowedHeaders = "*")
@Api(tags = {"Styling"})
@RestController
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class StylingController {

    private final StylingService stylingService;
    private final ChatCompletionServiceLogic chatCompletionServiceLogic;


    @ApiOperation(value = "양말에 대한 코디 추천", notes = "양말에 어울리는 상의,하의,신발 세트의 코디 2가지를 추천해줍니다.")
    @PostMapping("/styling")
    public List<StylingResponseDto> recommendStyling(@RequestPart(value="imageFile") MultipartFile imageFile, @RequestBody StylingRequestDto stylingRequestDto) throws IOException {  // 한쪽 양말 이미지 1장과 성별을 전송하면, 코디를 2가지 추천
        List<StylingResponseDto> stylingResponseDtos = stylingService.recommendStyling(imageFile, stylingRequestDto);
        return stylingResponseDtos;
    }

}

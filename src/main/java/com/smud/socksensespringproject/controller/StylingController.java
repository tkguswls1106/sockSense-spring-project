package com.smud.socksensespringproject.controller;

import com.smud.socksensespringproject.dto.styling.StylingRequestDto;
import com.smud.socksensespringproject.dto.styling.StylingResponseDto;
import com.smud.socksensespringproject.response.ResponseCode;
import com.smud.socksensespringproject.response.ResponseData;
import com.smud.socksensespringproject.response.responseitem.MessageItem;
import com.smud.socksensespringproject.service.StylingService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = {"https://www.socksense.site", "http://localhost:3000"}, allowedHeaders = "*")
@Api(tags = {"Styling"})
@RestController
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class StylingController {

    private final StylingService stylingService;


    @ApiOperation(value = "양말에 대한 코디 추천", notes = "양말에 어울리는 상의,하의,신발 세트의 코디 2가지를 추천해줍니다.")
    @ApiResponses({@ApiResponse(code = 200, message = MessageItem.STYLING_SUCCESS, response = StylingResponseDto.class, responseContainer = "List")})
    @ApiImplicitParams({
            @ApiImplicitParam(name = "imageFile", value = "양말 한쪽 이미지 1장", dataType = "__file"),
            @ApiImplicitParam(name = "stylingRequestDto", value = "{\"gender\": \"남성 or 여성\"}")
    })
    @PostMapping(value = "/styling",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity recommendStyling(@RequestPart(value="imageFile") MultipartFile imageFile, @RequestPart(value="stylingRequestDto") StylingRequestDto stylingRequestDto) throws IOException {  // 한쪽 양말 이미지 1장과 성별을 전송하면, 코디를 2가지 추천
        StylingResponseDto stylingResponseDto = stylingService.recommendStyling(imageFile, stylingRequestDto);
        return ResponseData.toResponseEntity(ResponseCode.POST_AND_GET_STYLING, stylingResponseDto);
    }

}

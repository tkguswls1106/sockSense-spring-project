package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.styling.StylingRequestDto;
import com.smud.socksensespringproject.dto.styling.StylingResponseDto;
import com.smud.socksensespringproject.service.StylingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class StylingServiceLogic implements StylingService {

    private final ChatCompletionServiceLogic chatCompletionServiceLogic;


    @Transactional
    @Override
    public List<StylingResponseDto> recommendStyling(MultipartFile imageFile, StylingRequestDto stylingRequestDto) {

        //// 이미지 색상 추출하고
        //// "내 성별은 ??이야. 대부분이 ?? 색상인 양말이 있어. 이와 매치할만한 코디 상의 1벌,하의 1벌,신발 1켤레씩을 두 차례로 나누어 추천해줘. 대답형식은 '코디1-상의: %s, 하의: %s, 신발: %s\n코디2-상의: %s, 하의: %s, 신발: %s' 이렇게 대답해."
        String question = "질문할 양식 생성";  ////
        String answer = chatCompletionServiceLogic.chatCompletions(question);
        //// 여기다가 answer 텍스트 전처리하고


        return null;
    }

}

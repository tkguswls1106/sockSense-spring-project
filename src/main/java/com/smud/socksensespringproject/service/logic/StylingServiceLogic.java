package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.computervision.OneImageRequestDto;
import com.smud.socksensespringproject.dto.computervision.SockColorResponseDto;
import com.smud.socksensespringproject.dto.styling.StylingRequestDto;
import com.smud.socksensespringproject.dto.styling.Styling;
import com.smud.socksensespringproject.dto.styling.StylingResponseDto;
import com.smud.socksensespringproject.response.exeption.GenderBadRequestException;
import com.smud.socksensespringproject.service.StylingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class StylingServiceLogic implements StylingService {

    private final ChatCompletionServiceLogic chatCompletionServiceLogic;
    private final ComputerVisionServiceLogic computerVisionServiceLogic;


    @Transactional
    @Override
    public StylingResponseDto recommendStyling(MultipartFile imageFile, StylingRequestDto stylingRequestDto) {

        if (!(stylingRequestDto.getGender().equals("남성") || stylingRequestDto.getGender().equals("여성"))) {  // 성별이 '남성' 또는 '여성'으로 입력안될시 예외 처리.
            throw new GenderBadRequestException();
        }

        // 여기는 컴퓨터 비전 api 파트 (이미지 색상 추출)
        OneImageRequestDto oneImageRequestDto = new OneImageRequestDto(imageFile);
        SockColorResponseDto sockColorResponseDto = computerVisionServiceLogic.sockColor(oneImageRequestDto);
        String sockColor = sockColorResponseDto.getSockColor();  // 양말 색상 (빨강,주황,노랑,초록,파랑,보라,검정,흰색,회색)

        String question =
                "내 성별은 "
                + stylingRequestDto.getGender()
                + "이야. 대부분이 "
                + sockColor
                + " 색상인 양말이 있어. 이와 매치할만한 코디 상의 1벌,하의 1벌,신발 1켤레씩을 두 차례로 나누어 추천해줘. 대답형식은 '코디1-상의: %s, 하의: %s, 신발: %s\n코디2-상의: %s, 하의: %s, 신발: %s\n입니다.' 이렇게 대답해.";

        String answer = chatCompletionServiceLogic.chatCompletions(question);  // chatGPT api로 질문함.

        List<String> topList = extractStringsBetweenPatterns(answer, "상의: ", ", 하의:");
        List<String> pantsList = extractStringsBetweenPatterns(answer, "하의: ", ", 신발:");
        List<String> shoesList = extractStringsBetweenPatterns(answer, "신발: ", "\n");

        while(true) {  // chatGPT에게 받은 답변이 코디 문자열 추출에 맞는 형식이 아니어서 인덱스에러가 날 경우, 다시 질문하게함.
            if (topList.size() == 2 && pantsList.size() == 2 && shoesList.size() == 2) {
                break;  // 정상적으로 문자열 추출이 되었으니, 무한 루프를 빠져나감.
            }

            // 정상적으로 문자열 추출이 안되었으니, 다시 질문하여 결과 갱신.
            answer = chatCompletionServiceLogic.chatCompletions(question);
            topList = extractStringsBetweenPatterns(answer, "상의: ", ", 하의:");
            pantsList = extractStringsBetweenPatterns(answer, "하의: ", ", 신발:");
            shoesList = extractStringsBetweenPatterns(answer, "신발: ", "\n");
        }

        Styling styling1 = new Styling(topList.get(0), pantsList.get(0), shoesList.get(0));
        Styling styling2 = new Styling(topList.get(1), pantsList.get(1), shoesList.get(1));

        List<Styling> stylings = new ArrayList<>();
        stylings.add(styling1);
        stylings.add(styling2);

        StylingResponseDto stylingResponseDto = new StylingResponseDto(sockColor, stylings);

        return stylingResponseDto;
    }

    public static List<String> extractStringsBetweenPatterns(String input, String startPattern, String endPattern) {  // 코디 문자열 추출용 메소드
        List<String> resultList = new ArrayList<>();

        String pattern = startPattern + "(.*?)" + endPattern;  // 정규 표현식 패턴 pattern을 생성.
        Pattern r = Pattern.compile(pattern);  // 정규 표현식 패턴을 컴파일하여 Pattern 객체 r을 생성.
        Matcher matcher = r.matcher(input);  // 입력 문자열에 대해 정규 표현식을 적용할 Matcher 객체 matcher를 생성.

        while (matcher.find()) {  // 입력 문자열에서 정규 표현식에 매칭되는 부분을 찾음.
            String extractedString = matcher.group(1).trim();
            resultList.add(extractedString);
        }

        return resultList;
    }

}

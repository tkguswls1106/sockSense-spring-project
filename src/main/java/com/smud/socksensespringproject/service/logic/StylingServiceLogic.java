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


    @Transactional
    @Override
    public List<StylingResponseDto> recommendStyling(MultipartFile imageFile, StylingRequestDto stylingRequestDto) {
        return null;
    }
}

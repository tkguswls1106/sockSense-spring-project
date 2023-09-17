package com.smud.socksensespringproject.service.logic;

import com.smud.socksensespringproject.dto.chatgpt.ChatgptRequestDto;
import com.smud.socksensespringproject.dto.chatgpt.Message;
import com.smud.socksensespringproject.externalapi.ChatCompletionClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
@RequiredArgsConstructor  // 이걸로 private final 되어있는걸 자동으로 생성자 만들어줘서 @Autowired와 this 없이 의존관계 DI 주입시켜줌.
public class ChatCompletionServiceLogic {

    private final ChatCompletionClient chatCompletionClient;


    private final static String ROLE_USER = "user";
    private final static String MODEL = "gpt-3.5-turbo";

    @Value("${chatgptapikey}")
    private String apiKey;


    @Transactional
    public String chatCompletions(final String question) {

        Message message = Message.builder()
                .role(ROLE_USER)
                .content(question)
                .build();

        ChatgptRequestDto chatgptRequestDto = ChatgptRequestDto.builder()
                .model(MODEL)
                .messages(Collections.singletonList(message))
                .build();

        return chatCompletionClient
                .chatCompletions(apiKey, chatgptRequestDto)
                .getChoices()
                .stream()
                .findFirst()
                .orElseThrow()
                .getMessage()
                .getContent();
    }
}

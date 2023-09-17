package com.smud.socksensespringproject.externalapi;

import com.smud.socksensespringproject.dto.chatgpt.ChatgptRequestDto;
import com.smud.socksensespringproject.dto.chatgpt.ChatgptResponseDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "chat", url = "https://api.openai.com/v1/")
public interface ChatCompletionClient {

    @Headers("Content-Type: application/json")
    @PostMapping("/chat/completions")
    ChatgptResponseDto chatCompletions(@RequestHeader("Authorization") String apikey, ChatgptRequestDto chatgptRequestDto);
}

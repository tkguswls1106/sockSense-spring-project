package com.smud.socksensespringproject.dto.chatgpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usage implements Serializable {

    @JsonProperty("prompt_tokens")  // 실제 Json응답의 변수와 이름을 동일하게 맞춰줌.
    private String promptTokens;

    @JsonProperty("completion_tokens")  // 실제 Json응답의 변수와 이름을 동일하게 맞춰줌.
    private String completionTokens;

    @JsonProperty("total_tokens")  // 실제 Json응답의 변수와 이름을 동일하게 맞춰줌.
    private String totalTokens;
}

package com.smud.socksensespringproject.dto.chatgpt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {

    private String role;
    private String content;
}

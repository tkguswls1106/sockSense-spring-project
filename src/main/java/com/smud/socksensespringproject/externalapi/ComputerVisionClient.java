package com.smud.socksensespringproject.externalapi;

import com.smud.socksensespringproject.dto.computervision.OneImageRequestDto;
import com.smud.socksensespringproject.dto.computervision.SimilarityResponseDto;
import com.smud.socksensespringproject.dto.computervision.SockColorResponseDto;
import com.smud.socksensespringproject.dto.computervision.TwoImagesRequestDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "computervision", url = "")
public interface ComputerVisionClient {

    @Headers("Content-Type: application/json")
    @PostMapping("")
    SimilarityResponseDto similarity(@RequestBody TwoImagesRequestDto twoImagesRequestDto);

    @Headers("Content-Type: application/json")
    @PostMapping("")
    SockColorResponseDto sockColor(@RequestBody OneImageRequestDto oneImageRequestDto);
}

package com.smud.socksensespringproject.externalapi;

import com.smud.socksensespringproject.dto.computervision.OneImageRequestDto;
import com.smud.socksensespringproject.dto.computervision.SimilarityResponseDto;
import com.smud.socksensespringproject.dto.computervision.SockColorResponseDto;
import com.smud.socksensespringproject.dto.computervision.TwoImagesRequestDto;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "computervision", url = "https://socksense-e0ec7268c3d8.herokuapp.com/")
public interface ComputerVisionClient {

    @Headers("Content-Type: application/json")
    @PostMapping("/api/pair/")
    SimilarityResponseDto similarity(@RequestBody TwoImagesRequestDto twoImagesRequestDto);

    @Headers("Content-Type: application/json")
    @PostMapping("/api/styling/")
    SockColorResponseDto sockColor(@RequestBody OneImageRequestDto oneImageRequestDto);
}

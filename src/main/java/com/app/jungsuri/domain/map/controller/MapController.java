package com.app.jungsuri.domain.map.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@Tag(name = "지도 관련 API", description = "지도(map) 관련 API")
public class MapController {

    @GetMapping("/map")
    @Operation(summary = "kakao 지도 페이지 조회", description = "kakao 지도 페이지를 조회합니다.")
    public String map() {
        return "map/main";
    }
}

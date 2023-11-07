package com.app.jungsuri.domain.mountain.controller;

import com.app.jungsuri.domain.mountain.model.MountainEntity;
import com.app.jungsuri.domain.mountain.service.MountainService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mountain")
@RequiredArgsConstructor
@Tag(name = "산 관련 API", description = "산(mountain) 관련 API")
public class MountainController {

    private final MountainService mountainService;

    @GetMapping("/list")
    @Operation(summary = "등산 페이지 조회", description = "100대 명산 페이지를 조회합니다.")
    public String list(@RequestParam(required = false, defaultValue = "1") int currentPageNumber, Model model){
        List<MountainEntity> mountainEntities = mountainService.getMountainListByPagination(currentPageNumber);

        model.addAttribute("startPageNum", mountainService.getStartPageNum(currentPageNumber));
        model.addAttribute("nextPageNum", mountainService.getEndPageNum(currentPageNumber)+1);
        model.addAttribute("pagingCount", mountainService.getPagingNumber());

        model.addAttribute("mountainEntities", mountainEntities);
        return "mountain/list";
    }

}



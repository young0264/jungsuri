package com.app.jungsuri.domain.mountain.web;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
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
public class MountainController {

    private final MountainService mountainService;

    @GetMapping("/list")
    public String list(@RequestParam(required = false, defaultValue = "1") int currentPageNumber, Model model){
        List<MountainEntity> mountainEntities = mountainService.getMountainListByPagination(currentPageNumber);

        model.addAttribute("startPageNum", mountainService.getStartPageNum(currentPageNumber));
        model.addAttribute("nextPageNum", mountainService.getEndPageNum(currentPageNumber)+1);
        model.addAttribute("pagingCount", mountainService.getPagingNumber());

        model.addAttribute("mountainEntities", mountainEntities);
        return "mountain/list";
    }

}



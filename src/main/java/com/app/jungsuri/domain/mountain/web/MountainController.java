package com.app.jungsuri.domain.mountain.web;

import com.app.jungsuri.domain.mountain.persistence.MountainEntity;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/mountain")
@RequiredArgsConstructor
public class MountainController {

    private final MountainService mountainService;

    @GetMapping("/list")
    public String list(Model model) {
        List<MountainEntity> mountainEntities = mountainService.getMountainAllInfo();
        model.addAttribute("mountainEntities", mountainEntities);
        return "mountain/list";
    }
}

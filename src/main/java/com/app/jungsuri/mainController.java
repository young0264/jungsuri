package com.app.jungsuri;

import com.app.jungsuri.domain.account.persistence.AccountEntity;
import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.UserRole;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
import com.app.jungsuri.domain.post.persistence.PostEntity;
import com.app.jungsuri.domain.post.persistence.PostRepository;
import com.app.jungsuri.domain.post.persistence.PostService;
import com.app.jungsuri.domain.post.web.dto.PostCreateDto;
import com.app.jungsuri.domain.weather.persistence.WeatherEntity;
import com.app.jungsuri.domain.weather.persistence.WeatherService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class mainController {

    private final AccountService accountService;
    private final MountainService mountainService;
    private final PostService postService;
    private final PostRepository postRepository;
    private final WeatherService weatherService;


    /** 메인 화면 */
    @GetMapping("/")
    public String main(@RequestParam(value="city", defaultValue="seoul") String city, Principal principal, Model model) {
        boolean emailValid = accountService.isEmailValid(principal);
        List<PostEntity> postListByRecentTop3 = postService.getPostListByRecentTop3();
        WeatherEntity weatherEntity = weatherService.getWeatherData(city);

        if (!emailValid) {
            model.addAttribute("error", "이메일 인증이 되지않은 계정입니다.");
        }

        model.addAttribute("weatherData", weatherEntity);
        model.addAttribute("postListByRecentTop3", postListByRecentTop3);

        return "main";
    }

    @PostConstruct
    public void initAccount() {
        SignUpForm signUpForm = new SignUpForm("12", "12@naver.com", "12","남의영","","", UserRole.ADMIN);
        accountService.createNewAccount(signUpForm);
    }

    @PostConstruct
    public void initMountainInfo() {
        final String mountainInfoUrl = "https://www.forest.go.kr/kfsweb/kfi/kfs/foreston/main/contents/FmmntSrch/selectFmmntSrchList.do?mn=NKFS_03_01_12&orgId=&mntUnit=100&mntIndex=1&searchMnt=&searchCnd3=&mntnInfoGbn=&mntnInfoSsnCd=&mntnInfoThmCd=&mntnInfoTmCd=&mntnHaslvCd=&mntnInfoLvlCd=";
        Connection connect = Jsoup.connect(mountainInfoUrl);

        try {
            Document document = connect.get();
            mountainService.saveMountainInfo(document);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @PostConstruct
    public void postTestInit() {
        AccountEntity accountEntity = accountService.findByLoginId("12");

//        PostEntity postEntity = createPost(PostCreateDto postCreateDto, AccountEntity accountEntity)

        for (int i = 0; i < 7; i++) {
            PostCreateDto postCreateDto = new PostCreateDto("게시글 제목"+i, "내용"+i, "등록자이름"+i, "12"+i, 0, new ArrayList<>());
            PostEntity postEntity = new PostEntity(postCreateDto, accountEntity);
            postRepository.save(postEntity);
        }
    }

}

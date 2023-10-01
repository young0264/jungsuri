package com.app.jungsuri;

import com.app.jungsuri.domain.account.persistence.AccountService;
import com.app.jungsuri.domain.account.web.UserRole;
import com.app.jungsuri.domain.account.web.form.SignUpForm;
import com.app.jungsuri.domain.mountain.persistence.MountainLocationService;
import com.app.jungsuri.domain.mountain.persistence.MountainService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Controller;
import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class mainController {

    private final AccountService accountService;
    private final MountainService mountainService;
    private final MountainLocationService mountainLocationService;

    @PostConstruct
    public String init() {
        SignUpForm signUpForm = new SignUpForm("12", "12@naver.com", "12","남의영","","", UserRole.ADMIN);
        accountService.createNewAccount(signUpForm);
        return "main";
    }

//    @PostConstruct
//    public void initMountainInfo() {
//        final String mountainInfoUrl = "https://www.forest.go.kr/kfsweb/kfi/kfs/foreston/main/contents/FmmntSrch/selectFmmntSrchList.do?mn=NKFS_03_01_12&orgId=&mntUnit=100&mntIndex=1&searchMnt=&searchCnd3=&mntnInfoGbn=&mntnInfoSsnCd=&mntnInfoThmCd=&mntnInfoTmCd=&mntnHaslvCd=&mntnInfoLvlCd=";
//        Connection connect = Jsoup.connect(mountainInfoUrl);
//
//        try {
//            Document document = connect.get();
//            mountainService.saveMountainInfo(document);
//        } catch (IOException e) {
//            e.printStackTrace();
//            throw new RuntimeException(e);
//        }
//    }

}

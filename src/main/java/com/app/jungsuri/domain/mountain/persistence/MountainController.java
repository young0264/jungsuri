package com.app.jungsuri.domain.mountain.persistence;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Slf4j
@Controller
public class MountainController {

    @GetMapping("/mountain/crawling")
    public String mountainCrawling() {

        final String mountainInfoUrl = "https://www.forest.go.kr/kfsweb/kfi/kfs/foreston/main/contents/FmmntSrch/selectFmmntSrchList.do?mn=NKFS_03_01_12&orgId=&mntUnit=100&mntIndex=1&searchMnt=&searchCnd3=&mntnInfoGbn=&mntnInfoSsnCd=&mntnInfoThmCd=&mntnInfoTmCd=&mntnHaslvCd=&mntnInfoLvlCd=";

        Connection connect = Jsoup.connect(mountainInfoUrl);

        try {
            Document document = connect.get();
            Elements mountainElements = document.getElementsByClass("list_item");
            for (Element mountainElement : mountainElements) {
                Elements src = mountainElement.getElementsByAttribute("src");
                log.info("src :: " + src + "\n");
                log.info("mountainElement : " + mountainElement.text() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return "main";
    }
}

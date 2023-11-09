package com.app.jungsuri.domain.mountain.Service;

import com.app.jungsuri.domain.mountain.service.MountainService;
import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.Test;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import java.io.IOException;

@MockMvcTest
class MountainServiceTest {
    @Autowired
    MountainService mountainService;

    @Test
    @WithMockUser(username = "12" , password = "12")
    void convertMountain100ExpToMountain100ExpByPer() {
        
        Integer mountain100ExpByPer = mountainService.convertMountain100ExpToMountain100ExpByPer(9800);
        Assertions.assertThat(mountain100ExpByPer).isEqualTo(10);
    }

//    /** 100대 명산 정보 크롤링 테스트 */
//    @Test
//    @WithMockUser(username = "12" , password = "12")
//    void crawlMountainInfo() throws IOException {
//        final String mountainInfoUrl = "https://www.forest.go.kr/kfsweb/kfi/kfs/foreston/main/contents/FmmntSrch/selectFmmntSrchList.do?mn=NKFS_03_01_12&orgId=&mntUnit=100&mntIndex=1&searchMnt=&searchCnd3=&mntnInfoGbn=&mntnInfoSsnCd=&mntnInfoThmCd=&mntnInfoTmCd=&mntnHaslvCd=&mntnInfoLvlCd=";
//        Connection connect = Jsoup.connect(mountainInfoUrl);
//        Document document = connect.get();
//
//        mountainService.saveMountainInfo(document);
//    }
}
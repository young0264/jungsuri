package com.app.jungsuri;

import com.app.jungsuri.domain.mountain.service.MountainService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

@Slf4j
@Controller
@RequiredArgsConstructor
@Profile({"local","test"})
public class LocalInitController {

    private final MountainService mountainService;

    /** 등산 정보 크롤링 */
    @PostConstruct
    public void initMountainInfo() {
        log.info("initMountainInfo");
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

//    @PostConstruct
//    public void postTestInit() {
//        log.info("postTestInit");
//        AccountEntity accountEntity = accountService.findByLoginId("12");
//
////        PostEntity postEntity = createPost(PostCreateDto postCreateDto, AccountEntity accountEntity)
//
//        for (int i = 0; i < 7; i++) {
//            PostCreateDto postCreateDto = new PostCreateDto("게시글 제목"+i, "내용"+i, "등록자이름"+i, "12"+i, 0, new ArrayList<>());
//            PostEntity postEntity = new PostEntity(postCreateDto, accountEntity);
//            postRepository.save(postEntity);
//        }
//    }
}

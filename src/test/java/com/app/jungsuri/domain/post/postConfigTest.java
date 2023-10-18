package com.app.jungsuri.domain.post;

import com.app.jungsuri.infra.MockMvcTest;
import org.assertj.core.api.Assertions;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

public class postConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void p_img_태그분리() {
        String htmlContent = "<p>게시글내용<img src=\"data:~~~~~~\"></p>"; // 실제 HTML 내용을 여기에 넣으세요

        // HTML을 Jsoup Document로 파싱
        Document doc = Jsoup.parse(htmlContent);

        Elements pElements = doc.select("p");
        Elements imgElements = doc.select("img");

        Element clonedPElement = new Element("p");
        Element clonedImgElement = new Element("img");

        clonedPElement.append(pElements.text());
        clonedImgElement.append(imgElements.attr("src"));

        Assertions.assertThat(clonedPElement.toString()).isEqualTo("<p>게시글내용</p>");
        Assertions.assertThat(clonedImgElement.toString()).isEqualTo("<img>data:~~~~~~</img>");
    }
}

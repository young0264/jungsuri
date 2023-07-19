package com.app.jungsuri.domain.mountain.persistence;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MountainService {

    private final MountainLocationService mountainLocationService;
    private final MountainRepository mountainRepository;

    public void saveMountainInfo(Document document) {
        Elements mountainElements = document.getElementsByClass("list_item");
        for (Element mountainElement : mountainElements) {
            String[] split = mountainElement.text().split("소재지 : ");
            String mountainHeightStr = split[0].trim();
            String[] split1 = mountainHeightStr.split(" 높이 : ");

            /**
             * mountain information
             * 이미지경로, 산소재지, 산이름, 산높이
             */
            String image_url = "https://www.forest.go.kr/" + mountainElement.getElementsByAttribute("src").attr("src");
            String mountainAllLocation = split[1];
            String mountainName = split1[0];
            int mountainHeight = getMountainHeight(split1);

            MountainEntity mountainEntity= saveMountainEntity(new MountainEntity(mountainName, mountainHeight, image_url));
            mountainLocationService.saveMountainLocation(mountainAllLocation, mountainEntity);
        }
    }

    private MountainEntity saveMountainEntity(MountainEntity mountainEntity) {
        return mountainRepository.save(mountainEntity);
    }

    private int getMountainHeight(String[] split1) {
        int mountainHeight;
        boolean isContainDot = split1[1].contains(".");
        if (isContainDot) {
            mountainHeight = Integer.parseInt(split1[1].split("\\.")[0]);
        } else {
            mountainHeight = Integer.parseInt(split1[1].substring(0, split1[1].length() - 1));
        }
        return mountainHeight;
    }


    //TODO : 정규식? 완벽하게 도 군 면 읍 리 등으로 나누는 방법 없나..
    private void getMountainInfoAsMap(String mountainLocation) {
        //ex: {군=[홍천군], 도=[강원도], 면=[두촌면, 화촌면]} 의 Map형태로 가공
        String[] mountainLocationSplit = mountainLocation.split(", ");
        for (String splitedLocation : mountainLocationSplit) {
            Map<String, ArrayList<String>> locationMap = new HashMap<>();
            String[] splitedData = null;

            if (splitedLocation.matches(".*[ㆍ·].*")) {
                splitedData = splitedLocation.split("ㆍ|·| ");
            } else {
                splitedData = splitedLocation.split(" ") ;
            }

            for(String data : splitedData) {
                String lastChar = String.valueOf(data.charAt(data.length() - 1));
                if (locationMap.containsKey(lastChar)) {
                    locationMap.get(lastChar).add(data);
                } else {
                    locationMap.put(lastChar, new ArrayList<>(List.of(data)) );
                }
            }
        }
    }
}

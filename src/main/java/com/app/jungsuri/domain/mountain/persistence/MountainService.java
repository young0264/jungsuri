package com.app.jungsuri.domain.mountain.persistence;

import com.app.jungsuri.domain.account.model.AccountEntity;
import com.app.jungsuri.domain.account.repository.AccountRepository;
import com.app.jungsuri.domain.account.dto.MountainExpUpdateDto;
import com.app.jungsuri.domain.mountain.persistence.MountainExp.MountainExpEntity;
import com.app.jungsuri.domain.mountain.persistence.MountainExp.MountainExpRepository;
import com.app.jungsuri.domain.tag.persistence.MountainTag;
import com.app.jungsuri.domain.tag.persistence.repository.MountainTagRepository;
import com.app.jungsuri.common.pagination.MountainPage;
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

    private final AccountRepository accountRepository;
    private final MountainLocationService mountainLocationService;
    private final MountainTagRepository mountainTagRepository;
    private final MountainExpRepository mountainExpRepository;
    private final MountainRepository mountainRepository;

    /** 100대 명산 크롤링 - 저장 */
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
            String shortLocations = mountainAllLocation.substring(0, Math.min(mountainAllLocation.length(), 20))+"...";
            int mountainHeight = getMountainHeight(split1);
            /** mountain 크롤링시 tag 생성 */
            MountainEntity mountainEntity= saveMountainEntity(new MountainEntity(mountainName, mountainHeight, image_url, shortLocations));
            mountainTagRepository.save(new MountainTag(mountainEntity, mountainName));
            mountainLocationService.saveMountainLocation(mountainAllLocation, mountainEntity);
        }
    }

    /** mountain Entity 저장 */
    private MountainEntity saveMountainEntity(MountainEntity mountainEntity) {
        return mountainRepository.save(mountainEntity);
    }

    /** mountain 산높이 가져오기 */
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


    /** mountain 모든 list 정보 가져오기*/
    public List<MountainEntity> getMountainAllInfo() {
        return  mountainRepository.findAll();
    }

    /** 모든 산 이름 가져오기 */
    public List<String> findAllMountainsName() {
        return mountainRepository.findAllMountainsName();
    }

    /**
     * pagination 적용된 mountain list 가져오기
     */
    public List<MountainEntity> getMountainListByPagination(int currentPageNumber) {
        log.info("currentPageNumber : " + currentPageNumber);
        log.info("getStartRowNum(currentPageNumber) : " + getStartRowNum(currentPageNumber));
        return mountainRepository.findMountainListByPagination(getStartRowNum(currentPageNumber));
    }

    /** page number에 따른 list start row number 가져오기 */
    private int getStartRowNum(int pageNumber) {
        return MountainPage.PAGE_ROW_SIZE.getValue()*(pageNumber-1)+1;
    }

    /** pagination 시작버튼 button */
    public int getStartPageNum(int currentPageNumber) {
        int pageBtnSize = MountainPage.PAGE_BTN_SIZE.getValue();
        int quotient = (currentPageNumber/pageBtnSize);

        if(currentPageNumber < 5) {
            return 1;
        }
        if(currentPageNumber % pageBtnSize == 0) {
            return (quotient-1)*pageBtnSize+1;
        }return (quotient-1)*pageBtnSize;
    }

    /** pagination 끝버튼 button */
    public int getEndPageNum(int currentPageNumber) {
        int lastPageButtonNumber = getLastPageButtonNumber();
        int pageBtnSize = MountainPage.PAGE_BTN_SIZE.getValue();
        int quotient = (currentPageNumber / pageBtnSize);

        /** 마지막 last page button 숫자가 더 작으면 last page button숫자 반환  */
        if (lastPageButtonNumber < (quotient + 1) * pageBtnSize) {
            return lastPageButtonNumber;
        }
        if (currentPageNumber % pageBtnSize == 0) {
            return quotient * pageBtnSize;
        }
        return (quotient + 1) * pageBtnSize;

    }

    /** 가장 마지막 pagination button 가져오기 */
    private int getLastPageButtonNumber() {
        int mountainCount = getMountainCount();
        int pageBtnSize = MountainPage.PAGE_BTN_SIZE.getValue();

        if(mountainCount % pageBtnSize == 0) {
            return mountainCount/pageBtnSize;
        }return mountainCount/pageBtnSize + 1;
    }


    /** 페이징 버튼 갯수를 반환 */
    public int getPagingNumber() {
        int mountainCount = getMountainCount();

        if(mountainCount % MountainPage.PAGE_ROW_SIZE.getValue() == 0) {
            return mountainCount / MountainPage.PAGE_ROW_SIZE.getValue();
        }return mountainCount / MountainPage.PAGE_ROW_SIZE.getValue() + 1;
    }


    /** mountain list 갯수 가져오기 */
    public int getMountainCount() {
        return mountainRepository.getMountainCount();
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

    /** 등산 경험치 반영에 대한 로그 기록 */
    public void createLog(MountainExpUpdateDto mountainExpUpdateDto, AccountEntity accountEntity) {
        String mountainName = mountainExpUpdateDto.getMountainName();
        MountainEntity mountainEntity = mountainRepository.findByName(mountainName);

        for (int i = 0; i < mountainExpUpdateDto.getLoginIdArr().size(); i++) {
            String userId = mountainExpUpdateDto.getLoginIdArr().get(i);
            AccountEntity userEntity = accountRepository.findByLoginId(userId).orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다."));
            mountainExpRepository.save(new MountainExpEntity(userEntity, mountainEntity, mountainExpUpdateDto.getHikingDate(), accountEntity.getId()));
        }

    }

    /** 등산 경험치를 백분위 percent로 바꾸는 메서드 */
    public Integer convertMountain100ExpToMountain100ExpByPer(Integer mountain100Exp) {
        return (Integer) (mountain100Exp*100 / 97159) ;
    }
}

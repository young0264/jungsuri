package com.app.jungsuri.domain.mountain.persistence;

import java.util.EnumMap;
import java.util.Map;

public enum MountainLocation {
    DO, SI, GUN, GU, DONG, EUP, MYEON, RO, GIL;
//    도, 시, 군, 구, 동, 읍, 면, 로 ,길;

    private static final Map<String, MountainLocation> enumMap = new EnumMap(MountainLocation.class);

    static {
        enumMap.put("도",DO);
        enumMap.put("시",SI);
        enumMap.put("군",GUN);
        enumMap.put("구",GU);
        enumMap.put("동",DONG);
        enumMap.put("읍",EUP);
        enumMap.put("면",MYEON);
        enumMap.put("로",RO);
        enumMap.put("길",GIL);
    }

}
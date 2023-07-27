package com.app.jungsuri.domain.weather;

import com.app.jungsuri.infra.MockMvcTest;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.security.test.context.support.WithMockUser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;

@MockMvcTest
public class WeatherApiTest {

    @Test
    @WithMockUser(username = "12", password = "12")
    public void test() throws IOException, ParseException {
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/MidFcstInfoService/getMidFcst"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + System.getenv("weatherServiceKey")); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("stnId","UTF-8") + "=" + URLEncoder.encode("108", "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("202307250600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        HashMap<String, HashMap<String,String>> rst = new HashMap<>();
        HashMap<String, String> sky = new HashMap<>();
        HashMap<String, String> tpt = new HashMap<>();
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String result= sb.toString();
        System.out.println(result);
//
        // Json parser를 만들어 만들어진 문자열 데이터를 객체화
        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject) parser.parse(result);
        // response 키를 가지고 데이터를 파싱
        JSONObject parse_response = (JSONObject) obj.get("response");
        // response 로 부터 body 찾기
        JSONObject parse_body = (JSONObject) parse_response.get("body");
        // body 로 부터 items 찾기
        JSONObject parse_items = (JSONObject) parse_body.get("items");
        // items로 부터 itemlist 를 받기
        JSONObject parse_item = (JSONObject) parse_items.get("item");

        Object[] keyObj = parse_item.keySet().toArray();
        String day = null;
        for(int i = 0 ; i < keyObj.length; i++) {
            String key = keyObj[i].toString();
            String str="";		//오전인지 오후인지 저장하는 변수 변수

            if(key.substring(0,2).equals("wf")) {	//하늘 상태
                try {	//key에서 숫자만 추출
                    day = key.substring(2,key.lastIndexOf("m")-1);
                    str = key.substring(key.lastIndexOf("m")-1);
                }catch(Exception e){
                    day = key.substring(2);
                }

                sky.put(day+str, parse_item.get(key).toString());
            }
            else if(key.substring(0,4).equals("rnSt")) {	//강수확률
                try {	//key에서 숫자만 추출
                    day = key.substring(4,key.lastIndexOf("m")-1);
                    str = key.substring(key.lastIndexOf("m")-1);
                }catch(Exception e){
                    day = key.substring(4);
                }

                tpt.put(day+str, parse_item.get(key).toString());
            }

        }

        Iterator<String> skyKeys = sky.keySet().iterator();
        Iterator<String> tptKeys = tpt.keySet().iterator();
        while( skyKeys.hasNext() ){
            String skyKey = skyKeys.next();
            String tptKey = tptKeys.next();
            System.out.println(skyKey+"\t하늘 : "+sky.get(skyKey) +", 강수확률 : "+tpt.get(tptKey)+"%");
        }
    }
}

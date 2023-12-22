package com.example.gyeongjeking.gongmoju;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.json.JSONArray;
import org.json.JSONObject;



import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GongmojuService {

    SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");

    //크롤링
    public static List<GongmojuDto> Gongmo() {
        List<GongmojuDto> gongmojuDtoList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("http://www.38.co.kr/html/fund/index.htm?o=k").get();

            Elements contents = doc.select("body > table:nth-child(9) > tbody > tr > td > table:nth-child(2) > tbody > tr > td:nth-child(1) > table:nth-child(11) > tbody > tr:nth-child(2) > td > table > tbody > tr");
            for (Element content : contents) {
//                System.out.println(content.text());
//                GongmojuDto Gongmo = new GongmojuDto();
                Elements tdContents = content.select("td");
                if(tdContents.get(1).text().contains("2023.12")) {

                String company = tdContents.get(0).text();
                    System.out.println(company+"이거?");

//                    for (int i = 0; i < gongmojuDtoList.size(); i++) {
//                        message += "공모주일정: " + gongmojuDtoList.get(i).getDate() + "\n";
//                        message += "종목명: " + gongmojuDtoList.get(i).getCompany() + "\n";
//                        message += "확정공모가: " + gongmojuDtoList.get(i).getFinalPrice() + "\n";
//                        message += "희망공모가: " + gongmojuDtoList.get(i).getHopePrice() + "\n";
//                        message += "주간사: " + gongmojuDtoList.get(i).getStockCompanys() + "\n";
//                        message += "\n";
//                    }
//                    return message;
//                }
                GongmojuDto gongmojuDto = GongmojuDto.builder()
                                .company(company)
                                .build();
                    gongmojuDtoList.add(gongmojuDto);
                    System.out.println(gongmojuDto);




//                if (tdContents.get(5).text().contains("대신") || tdContents.get(5).text().contains("미래에셋")
//                        || tdContents.get(5).text().contains("한국투자") || tdContents.get(5).text().contains("NH") || tdContents.get(5).text().contains("삼성")
//                        || tdContents.get(5).text().contains("키움")) {

//
//                        JSONObject jsonObject = new JSONObject();
//                        JSONArray arr = new JSONArray();
//                        JSONObject attachments = new JSONObject();
//                        attachments.get("text", );

//                        gongmojuDtoList.tdContents.get(0).text();
//                    Gongmo.setCompany(tdContents.get(0).text());
//                    Gongmo.setDate(tdContents.get(1).text());
//                    Gongmo.setFinalPrice(tdContents.get(2).text());
//                    Gongmo.setHopePrice(tdContents.get(3).text());
//                    Gongmo.setCompetition(tdContents.get(4).text());
//                    Gongmo.setStockCompanys(tdContents.get(5).text());
//                    gongmojuDtoList.add(Gongmo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();

    }


    @Value("${slackBotToken}")
    private String slackToken;

    public static WebhookResponse sendSlack() {
        String slackUrl = "https://hooks.slack.com/services/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";

        try {
            WebhookResponse response = null;
            Slack slack = Slack.getInstance();

//            Payload payload = Payload.builder().
//                    text(text).build();
//            response = slack.send(slackUrl, payload, gongmojuDtoList);

            List<GongmojuDto> gongmojuDtoList = new ArrayList<>();

            Document doc = Jsoup.connect("http://www.38.co.kr/html/fund/index.htm?o=k").get();

            Elements contents = doc.select("body > table:nth-child(9) > tbody > tr > td > table:nth-child(2) > tbody > tr > td:nth-child(1) > table:nth-child(11) > tbody > tr:nth-child(2) > td > table > tbody > tr");
            for (Element content : contents) {
                GongmojuDto Gongmo = new GongmojuDto();
                Elements tdContents = content.select("td");

//                if (tdContents.get(5).text().contains("대신") || tdContents.get(5).text().contains("미래에셋")
//                        || tdContents.get(5).text().contains("한국투자") || tdContents.get(5).text().contains("NH") || tdContents.get(5).text().contains("삼성")
//                        || tdContents.get(5).text().contains("키움")) {
                if(tdContents.get(1).text().contains("2023.12")) {




                    Gongmo.setCompany(tdContents.get(0).text());
                    Gongmo.setDate(tdContents.get(1).text());
                    Gongmo.setFinalPrice(tdContents.get(2).text());
                    Gongmo.setHopePrice(tdContents.get(3).text());
                    Gongmo.setCompetition(tdContents.get(4).text());
                    Gongmo.setStockCompanys(tdContents.get(5).text());
                    gongmojuDtoList.add(Gongmo);

                }
            }
            System.out.println(gongmojuDtoList);
                        Payload payload = Payload.builder().
                    text(gongmojuDtoList.toString()).build();
            response = slack.send(slackUrl, payload);
            return response;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }






//
//        arr.put("text", "hello");
//        jsonObject.put("arr", arr);

//    public void slack(List<GongmojuDto> sendList, String today) {
//        String url = "/공모주";
//        RestTemplate restTemplate = new RestTemplate();
//        Map<String, Object> request = new HashMap<String, Object>();
//        String message = "";
//        message = getString(sendList, today, message);
//
//        request.put("username", "slackbot");
//        request.put("text", message);
//        HttpEntity<Map<String, Object>> entity = new HttpEntity<Map<String, Object>>(request);
//        restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
//    }
//
    public String getString(List<GongmojuDto> gongmojuDtoList, String today, String message) {

        if ("월요일".equals(today)) {
            message += "다음 주 공모주 일정.\n\n";
        } else {
            message += "오늘의 공모주 일정.\n\n";
        }
        for (int i = 0; i < gongmojuDtoList.size(); i++) {
            message += "공모주일정: " + gongmojuDtoList.get(i).getDate() + "\n";
            message += "종목명: " + gongmojuDtoList.get(i).getCompany() + "\n";
            message += "확정공모가: " + gongmojuDtoList.get(i).getFinalPrice() + "\n";
            message += "희망공모가: " + gongmojuDtoList.get(i).getHopePrice() + "\n";
            message += "주간사: " + gongmojuDtoList.get(i).getStockCompanys() + "\n";
            message += "\n";
        }
        return message;
    }
}

    //알림
//    public void alarm(){
//        Calendar cal = Calendar.getInstance();
//        List<GongmojuDto> sendList = new ArrayList<>();
//
//        // 더미데이터
//        List<GongmojuDto> crawlList = new ArrayList<>();
//        crawlList.add(new GongmojuDto("오늘", "2021.04.10~04.11", "-", "35,000~47,500", "NH투자증권,삼성증권,대신증권"));
//        crawlList.add(new GongmojuDto("어제", "2021.04.09~04.10", "-", "35,000~47,500", "NH투자증권,삼성증권,대신증권"));
//        crawlList.add(new GongmojuDto("씨앤시", "2021.04.11~05.07", "-", "35,000~47,500", "NH투자증권,삼성증권,대신증권"));
//        crawlList.add(new GongmojuDto("씨앤시", "2021.04.12~05.07", "-", "35,000~47,500", "NH투자증권,삼성증권,대신증권"));
//        crawlList.add(new GongmojuDto("씨앤시", "2021.04.13~05.07", "-", "35,000~47,500", "NH투자증권,삼성증권,대신증권"));
//        ///////////
//
//        String today = today();
//        String date = format.format(cal.getTime()); //2021.04.10
//
//        if ("일요일".equals(today)) {
//            cal.add(Calendar.DATE, 1);
//            String monday = format.format(cal.getTime()); //월
//            cal.add(Calendar.DATE, 1);
//            String tuesday = format.format(cal.getTime()); // 화
//            cal.add(Calendar.DATE, 1);
//            String wednesday = format.format(cal.getTime()); //수
//            cal.add(Calendar.DATE, 1);
//            String thursday = format.format(cal.getTime());//목
//            //날짜 ~기준으로 쪼갠다.
//            for (int i = 0; i < crawlList.size(); i++) {
//                String[] splitDate = crawlList.get(i).getDate().split("~");
//                if (monday.equals(splitDate[0]) || tuesday.equals(splitDate[0]) || wednesday.equals(splitDate[0]) || thursday.equals(splitDate[0])) {
//                    sendList.add(crawlList.get(i));
//                    System.out.println(sendList);
//                }
//
//            }
//
//
//        } else if ("평일".equals(today)) {
//            //날짜 ~기준으로 쪼갠다.
//            for (int i = 0; i < crawlList.size(); i++) {
//                String[] splitDate = crawlList.get(i).getDate().split("~");
//                if (date.equals(splitDate[0]) || date.equals(cal.get(Calendar.YEAR) + "." + splitDate[1])) {
//                    sendList.add(crawlList.get(i));
//                }
//            }
//        }
//
//        if (sendList.size() == 0) {
//            //없으면 종료
//            return;
//        } else {
//            //챗봇으로
//            slack(sendList, today);
//        }
//
//    }
//
//    private String today() {
//        Calendar cal = Calendar.getInstance();
//        int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
//        String today = "";
//        switch (dayOfWeek) {
//            case 1:
//                today = "일요일";
//                break;
//
//            default:
//                today = "평일";
//                break;
//        }
//        return today;
//    }
//
//}


package com.example.gyeongjeking.gongmoju2;


import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.lang.reflect.Field;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class NewService {


    public List<NewDto> getNewList() {
        final String stockList = "http://www.38.co.kr/html/fund/index.htm?o=k";
//        final String stockList = "https://finance.naver.com/sise/sise_market_sum.nhn?&page=1";
        Connection conn = Jsoup.connect(stockList);
        try {
            Document document = conn.get();
            return getNewList(document);
        } catch (IOException ignored) {
        }
        return null;
        }

    public List<NewDto> getNewList(Document document) {
        Elements kosPiTable = document.select("table.type_2 tbody tr");
        List<NewDto> list = new ArrayList<>();
        for (Element element : kosPiTable) {
            if (element.attr("onmouseover").isEmpty()) {
                continue;
            }
            list.add(createKosPiStockDto(element.select("td")));
        }
        return list;
    }

    public NewDto createKosPiStockDto(Elements td) {
        NewDto kospiStockDto = NewDto.builder().build();
        Class<?> clazz = kospiStockDto.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0; i < td.size(); i++) {
            String text;
            if(td.get(i).select(".center a").attr("href").isEmpty()){
                text = td.get(i).text();
            }else{
                text = "http://www.38.co.kr/" + td.get(i).select(".center a").attr("href");
            }
            fields[i].setAccessible(true);
            try{
                fields[i].set(kospiStockDto,text);
            }catch (Exception ignored){
            }
        }
        return kospiStockDto;
    }
}
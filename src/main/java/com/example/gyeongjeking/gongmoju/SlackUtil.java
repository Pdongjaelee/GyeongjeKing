//package com.example.gyeongjeking.gongmoju;
//
//
//import com.slack.api.Slack;
//import com.slack.api.methods.SlackApiException;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.configurationprocessor.json.JSONArray;
//import org.springframework.boot.configurationprocessor.json.JSONObject;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//
//
//
//@Service
//@RequiredArgsConstructor
//public class SlackUtil {
//
//
//    private static GongmojuService gongmojuService;
//
//    @Value(value = "${slackBotToken}")
//    private String slackToken;
//
//    @Value(value = "${slack.channel}")
//    String channel;
//
//    public void sendSlack() throws Exception {
//        JSONObject jsonObject = new JSONObject();
//        JSONArray arr = new JSONArray();
//        jsonObject.put("channel", slackId);
//
//
//
//        arr.put("text", "hello");
//        jsonObject.put("arr", arr);
//
//
//
//
//
//
//
////    try {
////        Slack slack = Slack.getInstance();
////        slack.methods(slackToken).chatPostMessage(req -> req.channel(channel).text(message));
////    } catch (SlackApiException | IOException e) {
////        log.error(e.getMessage());
////    }
//
//    }
//
//}

package com.example.gyeongjeking.gongmoju;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GongmojuController {
    private final GongmojuService gongmojuService;


//    @Scheduled(cron = "0 0 22 * * MON")
//    @Scheduled(cron = "0 0 9 * * MON-FRI")
//    @GetMapping("/send")
//    @ResponseBody
//    public void sendBot() {
//        gongmojuService.alarm();
//    }

    @GetMapping("/Gong")
    public List<GongmojuDto> send() {
        return gongmojuService.Gongmo();
    }
    @GetMapping("/Gong1")
    public void senda() {
        gongmojuService.sendSlack();
    }
}

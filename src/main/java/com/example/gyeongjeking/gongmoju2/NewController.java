package com.example.gyeongjeking.gongmoju2;


import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class NewController {

    private final NewService newService;


    @GetMapping("/all")
    public List<NewDto> getNewList(HttpServletRequest request) {
        return newService.getNewList();
    }

}

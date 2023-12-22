package com.example.gyeongjeking.gongmoju;


import jakarta.persistence.Column;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class GongmojuDto {
    private String company;
//    @Column(nullable = false)
    private String date;
    private String finalPrice;
    private String hopePrice;
    private String competition;
    private String stockCompanys;
}

package com.demo.comentoStatistic.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NonRestdayCountDto {
    private String fromDate;
    private String toDate;

    private int originCnt;      // 전체 로그인 수
    private int restCnt;       // 휴일/주말 로그인 수
    private int totCnt;  // 휴일 제외 총 로그인 수
}

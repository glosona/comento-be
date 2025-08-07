package com.demo.comentoStatistic.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MonthByDepartmentDto {
    private String department;
    private String yearMonth;
    private int totCnt;
}
